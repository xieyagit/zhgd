package com.hujiang.project.zhgd.lyPersonnel.api;

import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecord;
import com.hujiang.project.zhgd.lyCompany.domain.LyCompany;
import com.hujiang.project.zhgd.lyCompany.service.ILyCompanyService;
import com.hujiang.project.zhgd.lyPersonnel.domain.LyCompanyPersonnel;
import com.hujiang.project.zhgd.lyPersonnel.domain.LyPersonnel;
import com.hujiang.project.zhgd.lyPersonnel.domain.LyPersonnelRecord;
import com.hujiang.project.zhgd.lyPersonnel.service.ILyPersonnelService;
import com.hujiang.project.zhgd.lyRegistrationRecord.domain.LyRegistrationRecord;
import com.hujiang.project.zhgd.lyRegistrationRecord.mapper.LyRegistrationRecordMapper;
import com.hujiang.project.zhgd.lyRegistrationRecord.service.ILyRegistrationRecordService;
import com.hujiang.project.zhgd.lyStatistics.domain.LyStatistics;
import com.hujiang.project.zhgd.lyStatistics.service.ILyStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/lyPersonnel",method = RequestMethod.POST)
public class PersonnelApi extends BaseController {
    @Autowired
    private ILyPersonnelService lyPersonnelService;
    @Autowired
    private ILyRegistrationRecordService lyRegistrationRecordService;
    @Autowired
    private IHjProjectService hjProjectService;
    @Autowired
    private ILyCompanyService lyCompanyService;
    @Autowired
    private ILyStatisticsService lyStatisticsService;

    @PostMapping("/insertPersonnel")
    public AjaxResult insertPersonnel( LyPersonnel lyPersonnel)throws Exception{
        lyPersonnel.setIspresent("0");
        LyPersonnel a=new LyPersonnel();
        a.setPid(lyPersonnel.getPid());
        a.setIdCode(lyPersonnel.getIdCode());
        List<LyPersonnel> lList=lyPersonnelService.selectLyPersonnelList(a);
        int i=0;
        if(lList.size()>0){
            if("0".equals(lList.get(0).getIsBlacklist())) {
                lyPersonnel.setId(lList.get(0).getId());
                i = lyPersonnelService.updateLyPersonnel(lyPersonnel);
            }else{
                return AjaxResult.error("此人员已添加进黑名单，无法登记");
            }
        }else{
           i= lyPersonnelService.insertLyPersonnel(lyPersonnel);
        }
        //登记记录
        LyRegistrationRecord lrr=new LyRegistrationRecord();
        lrr.setPwid(lyPersonnel.getId());
        lrr.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        lrr.setCompanyName(lyPersonnel.getCompanyName());
        lrr.setFloor(lyPersonnel.getFloor());
        lrr.setSubordinate(lyPersonnel.getSubordinate());
        lrr.setBz(lyPersonnel.getBz());
        lrr.setPid(lyPersonnel.getPid());
        lrr.setType(lyPersonnel.getType());
        lyRegistrationRecordService.insertLyRegistrationRecord(lrr);
        //添加人脸下发指令
        lyPersonnelService.personnelInOUt(lyPersonnel,"0");
        if(i>0){
            return AjaxResult.success("登记成功");
        }else{
            return AjaxResult.error("登记失败");
        }

    }

    /**
     * 信息查询人员资料
     */
    @PostMapping("/selectPersonnelCompany")
    public AjaxResult selectPersonnelCompany( LyPersonnel lyPersonnel){
        lyPersonnel.setType("1");
        lyPersonnel.setIspresent("0");
        List<LyCompanyPersonnel> lcpList=lyPersonnelService.getLyCompanyPersonnel(lyPersonnel);
        LyCompanyPersonnel l;
        for(int i=0;i<lcpList.size();i++){
           l=lcpList.get(i);
           l.setSize(l.getpList().size());
        }
            return AjaxResult.success(lcpList);
    }
    /**
    * 人员动态
     */
    @PostMapping("/getPersonnelDT")
    public AjaxResult getPersonnelDT(@RequestParam Integer pid,@RequestParam String time){
        Map<String,Map> resultMap=new HashMap<String,Map>();
        Map<String,Integer> zzMap=new HashMap<String,Integer>();
        Map<String,Integer> fkMap=new HashMap<String,Integer>();
        LyAttendanceRecord la=new LyAttendanceRecord();
        la.setProjectId(pid);
        la.setPassedTime(time);
        //在职人员总数
        Integer zzryzs=lyPersonnelService.zzryzs(pid);
        //在职人员进入数
        Integer zzryin=lyPersonnelService.zzryin(la);
        //在职人员出去数
        Integer zzryout=lyPersonnelService.zzryout(la);
        zzMap.put("zzryzs",zzryzs);
        zzMap.put("zzryin",zzryin);
        zzMap.put("zzryout",zzryout);
        resultMap.put("zz",zzMap);
        //访客总人数
        Integer fkryzs=lyPersonnelService.fkryzs(la);
        //访客进入人数
        Integer fkryin=lyPersonnelService.fkryin(la);
        //访客出去人数
        Integer fkryout=lyPersonnelService.fkryout(la);
        fkMap.put("fkryzs",fkryzs);
        fkMap.put("fkryin",fkryin);
        fkMap.put("fkryout",fkryout);
        resultMap.put("fk",fkMap);
        return AjaxResult.success(resultMap);
    }
    /**
     * 人员进出记录
     */
    @PostMapping("/getPersonnelRecord")
    public AjaxResult getPersonnelRecord(@RequestParam Integer pid,@RequestParam String time){

        LyAttendanceRecord lar=new LyAttendanceRecord();
        lar.setProjectId(pid);
        lar.setPassedTime(time);
        Map<String,Map> resultMap=new HashMap<String,Map>();
        Map<String,Object> zzMap=new HashMap<String,Object>();
        Map<String,Object> fkMap=new HashMap<String,Object>();
        List<LyPersonnelRecord> zzList=lyPersonnelService.getLyPersonnelRecordZZ(lar);
        List<LyPersonnelRecord> fkList=lyPersonnelService.getLyPersonnelRecordFK(lar);
        zzMap.put("zzList",zzList);
        zzMap.put("zzSize",zzList.size());
        resultMap.put("zzMap",zzMap);
        fkMap.put("fkList",fkList);
        fkMap.put("fkSize",fkList.size());
        resultMap.put("fkMap",fkMap);
        return AjaxResult.success(resultMap);




    }
    /**
     * 查询人员信息
     */
    @PostMapping("/selectPersonnelById")
    public AjaxResult selectPersonnelById(@RequestParam Integer personnelId ){
        LyPersonnel lyPersonnel=lyPersonnelService.selectLyPersonnelById(personnelId);
        return AjaxResult.success(lyPersonnel);

    }
    //pc端接口
    /**
     * 人员分页列表
     */
    @PostMapping("/selectPersonnelPageList")
    public AjaxResult selectPersonnelPageList( LyPersonnel lyPersonnel){
        startPage();
        List<LyPersonnel> lpList=lyPersonnelService.selectLyPersonnelList(lyPersonnel);
        return AjaxResult.success(getDataTable(lpList));
    }
    /**
     * 人员修改保存
     */
    @PostMapping("/updatePersonnelById")
    public AjaxResult updatePersonnelById( LyPersonnel lyPersonnel){

       lyPersonnelService.updateLyPersonnel(lyPersonnel);
        return AjaxResult.success("修改成功");
    }
    /**
     * 在职人员离职
     */
    @PostMapping("/personnelQuit")
    public AjaxResult personnelQuit( LyPersonnel lyPersonnel)throws Exception{
        lyPersonnel.setIspresent("1");
        lyPersonnelService.updateLyPersonnel(lyPersonnel);
        //删除人脸
        lyPersonnelService.personnelInOUt(lyPersonnel,"2");
        return AjaxResult.success("离职成功");
    }
    /**
     * 添加黑名单
     */
    @PostMapping("/insertBlacklist")
    public AjaxResult insertBlacklist(String ids)throws Exception{
      int i=  lyPersonnelService.insertBlacklist(ids);
      String[] idss=ids.split(",");
      for(int j=0;j<idss.length;j++){
        LyPersonnel lyPersonnel=lyPersonnelService.selectLyPersonnelById(Integer.valueOf(idss[j]));
        lyPersonnel.setIspresent("1");
        lyPersonnelService.updateLyPersonnel(lyPersonnel);
          lyPersonnelService.personnelInOUt(lyPersonnel,"1");
      }
      if(i>0){
          return AjaxResult.success("添加黑名单成功！");
      }else{
          return AjaxResult.error("添加黑名单失败！");
      }
    }
    /**
     * 删除黑名单
     */
    @PostMapping("/deleteBlacklist")
    public AjaxResult deleteBlacklist(String ids){
        int i=  lyPersonnelService.deleteBlacklist(ids);
        if(i>0){
            return AjaxResult.success("删除黑名单成功！");
        }else{
            return AjaxResult.error("删除黑名单失败！");
        }
    }
    /**
     * 首页接口
     */
    @PostMapping("/personnelIndex")
    public AjaxResult personnelIndex( Integer pid,String time){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Map<String,String> projectMap=new HashMap<String,String>();

        //查询项目信息
        HjProject hjProject=hjProjectService.selectHjProjectById(pid);
        projectMap.put("projectName",hjProject.getProjectName());
        projectMap.put("floor",hjProject.getProjectNumber().toString());
        //查询公司总数
        LyCompany lc=new LyCompany();
        lc.setPid(pid);
        List<LyCompany> lcList=lyCompanyService.selectLyCompanyList(lc);
        projectMap.put("companyCount",String.valueOf(lcList.size()));
        Integer zzryzs=lyPersonnelService.zzryzs(pid);
        projectMap.put("zzryzs",zzryzs.toString());
        resultMap.put("projectMap",projectMap);

        LyStatistics ls=new LyStatistics();
        ls.setPid(pid);
        ls.setTime(time);
        List<LyStatistics> lsList=lyStatisticsService.selectLyTimeCount(ls);
        resultMap.put("lsList",lsList);
        return AjaxResult.success(resultMap);
    }
}
