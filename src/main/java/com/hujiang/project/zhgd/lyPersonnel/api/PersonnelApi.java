package com.hujiang.project.zhgd.lyPersonnel.api;

import com.hujiang.common.utils.StringUtils;
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
import com.hujiang.project.zhgd.utils.AliyunOSSClientUtil;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
        lyPersonnel=lyPersonnelService.selectLyPersonnelById(lyPersonnel.getId());
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
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
           boolean a=     file.delete();
                System.out.println(a);
            }
        }
    }
    /**
     * 导入人员(不包括照片)
     */
    @PostMapping("/importPerson")
    public AjaxResult importPerson( MultipartFile multfile,Integer pid)throws Exception{
        //把MultipartFile转化为File

        // 获取文件名
        String fileName = multfile.getOriginalFilename();
        // 获取文件后缀
        String prefix=fileName.substring(fileName.lastIndexOf("."));
        // 用uuid作为文件名，防止生成的临时文件重复
        final File fo = File.createTempFile(String.valueOf(System.currentTimeMillis()), prefix);
        // MultipartFile to File
        multfile.transferTo(fo);

        //你的业务逻辑



        FileInputStream fis =null;
        Workbook wookbook = null;
        Sheet sheet =null;
        try
        {
            //获取一个绝对地址的流
            fis = new FileInputStream(fo);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            //2003版本的excel，用.xls结尾
            wookbook = new HSSFWorkbook(fis);//得到工作簿

        }
        catch (Exception ex)
        {
            //ex.printStackTrace();
            try
            {
                //2007版本的excel，用.xlsx结尾
                fis = new FileInputStream(fo);
                wookbook = new XSSFWorkbook(fis);//得到工作簿
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        Map<String, PictureData>  maplist=null;

        sheet = wookbook.getSheetAt(0);

        //得到一个工作表



        //获得表头
        Row rowHead = sheet.getRow(0);


        //获得数据的总行数
        int totalRowNum = sheet.getLastRowNum();
        System.out.println("总行数："+totalRowNum);
        //要获得属性
        String studentid;
        String studentname="";
        String grade="";
        String idCard="";
        LyPersonnel lyPersonnel;
        LyCompany lyCompany;
        //获得所有数据
        for(int i = 1 ; i <= totalRowNum ; i++)
        {
            //获得第i行对象
            Row row = sheet.getRow(i);
            if(row==null){
                continue;
            }
//            HSSFRow xssfRow = sheet.getRow(i);

            //获得获得第i行第0列的 String类型对象
            Cell cell = row.getCell((short)0);
            if(cell==null){
                continue;
            }
            studentid = cell.getStringCellValue();

            //获得一个数字类型的数据
            //studentname = (int) cell.getNumericCellValue();
            cell = row.getCell((short)1);
            studentname =cell.getStringCellValue();

            cell = row.getCell((short)2);
            grade =cell.getStringCellValue();

            cell = row.getCell((short)3);
            idCard =cell.getStringCellValue();
            lyPersonnel=new LyPersonnel();
            lyPersonnel.setIdCode(idCard);
            lyPersonnel.setEmpNumber(grade);
            lyPersonnel.setEmpName(studentid);
            lyPersonnel.setPid(pid);
            lyPersonnel.setCompanyName(studentname);
            lyCompany=new LyCompany();
            lyCompany.setPid(pid);
            lyCompany.setCompanyName(studentname);
            List<LyCompany> lcList=lyCompanyService.selectLyCompanyList(lyCompany);
            if(lcList.size()>0){
                lyPersonnel.setCompanyId(lcList.get(0).getId());
                lyPersonnel.setFloor(lcList.get(0).getFloor());
            }
            lyPersonnel.setIspresent("0");
            lyPersonnel.setType("1");
            LyPersonnel a=new LyPersonnel();
            a.setPid(lyPersonnel.getPid());
            a.setIdCode(lyPersonnel.getIdCode());
            List<LyPersonnel> lList=lyPersonnelService.selectLyPersonnelList(a);
            if(lList.size()>0){
                if("0".equals(lList.get(0).getIsBlacklist())) {
                    lyPersonnel.setId(lList.get(0).getId());
                     lyPersonnelService.updateLyPersonnel(lyPersonnel);
                }
            }else{
                 lyPersonnelService.insertLyPersonnel(lyPersonnel);
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
            System.out.println("姓名："+studentid+"\t年级班级："+studentname+"\t学号："+grade+"\t身份证号："+idCard);
        }

        //程序结束时，删除临时文件
        deleteFile(fo);
        return AjaxResult.success("导入成功");
    }
    /**
     * 上传人员照片
     */
    @PostMapping("/uploadFace")
    public AjaxResult uploadFace( MultipartFile file,Integer id)throws Exception{
        String FileImgurl = AliyunOSSClientUtil.uploadFileImg(file, "hujiang", id+"" + System.currentTimeMillis() + ".jpg");
        String name = FileImgurl.substring(0, FileImgurl.lastIndexOf("?"));
        LyPersonnel lyPersonnel=new LyPersonnel();
        lyPersonnel.setId(id);
        lyPersonnel.setFaceUrl(name);
        lyPersonnelService.updateLyPersonnel(lyPersonnel);
        return  AjaxResult.success("上传成功");
    }
    @PostMapping("/in")
    public AjaxResult in(String ids)throws Exception{
        String[] id=ids.split(",");
        LyPersonnel lyPersonnel;
        for(int i=0;i<id.length;i++){
            lyPersonnel=lyPersonnelService.selectLyPersonnelById(Integer.valueOf(id[i])) ;
            if(StringUtils.isBlank(lyPersonnel.getFaceUrl())){
                return AjaxResult.error(lyPersonnel.getEmpName()+"未上传照片，无法下发");
            }
            lyPersonnelService.personnelInOUt(lyPersonnel,"0");
        }
return AjaxResult.success("下发成功");
    }
}
