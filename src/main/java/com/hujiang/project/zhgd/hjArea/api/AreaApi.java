package com.hujiang.project.zhgd.hjArea.api;

import com.hujiang.common.support.Convert;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjArea.domain.HjArea;
import com.hujiang.project.zhgd.hjArea.service.IHjAreaService;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.project.zhgd.hjCompanyHierarchy.domain.HjCompanyHierarchy;
import com.hujiang.project.zhgd.hjCompanyHierarchy.service.IHjCompanyHierarchyService;
import com.hujiang.project.zhgd.hjCompanyProject.domain.HjCompanyProject;
import com.hujiang.project.zhgd.hjCompanyProject.service.IHjCompanyProjectService;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkers;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.service.IHjDeviceProjectworkersService;
import com.hujiang.project.zhgd.hjProjectPersonnelSynchronization.domain.HjProjectPersonnelSynchronization;
import com.hujiang.project.zhgd.hjProjectPersonnelSynchronization.service.IHjProjectPersonnelSynchronizationService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjTeam.domain.HjTeam;
import com.hujiang.project.zhgd.hjTeam.service.IHjTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * @program: Provider01
 * @description: 城市接口
 * @author: Mr.LiuYong
 * @create: 2019-05-14 17:38
 **/
@RestController
@RequestMapping(value = "/provider/area",method = RequestMethod.POST)
public class AreaApi extends BaseController {
    @Autowired
    private IHjAreaService hjAreaService;


    /**
     * 功能描述 :根据id获取下级市区
     * @author Mr.LiuYong
     * @date  2019/5/14 17:46
     * @param id
     * @return java.lang.Object
     */
    @RequestMapping("getArea")
    public AjaxResult getArea(Long id){

        HjArea area = new HjArea();
        area.setParentId(id);
        System.out.println(id);
        return AjaxResult.success(hjAreaService.selectHjAreaList(area));
    }
    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService;
    @Autowired
    private IHjDeviceProjectworkersService hjDeviceProjectworkersService;
    @Autowired
    private IHjAttendanceDeviceService hjAttendanceDeviceService;
    @RequestMapping("getArea2")
    public void getArea2(){
        HjProjectWorkers hjProjectWorkers=new HjProjectWorkers();
        hjProjectWorkers.setProjectId(350);
        hjProjectWorkers.setEnterAndRetreatCondition(0);
      List<HjProjectWorkers> hList= hjProjectWorkersService.selectHjProjectWorkersList(hjProjectWorkers);
        HjAttendanceDevice ha=new HjAttendanceDevice();
        ha.setProjectId(350);
        List<HjAttendanceDevice> aList=hjAttendanceDeviceService.selectHjAttendanceDeviceList(ha);
        HjDeviceProjectworkers hp;
        for(int i=0;i<aList.size();i++){
            for(int j=0;j<hList.size();j++){
                hp=new HjDeviceProjectworkers();
                hp.setStatus("1");
                hp.setDeviceNo(aList.get(i).getDeviceNo());
                hp.setProjectWorkersId(hList.get(j).getId());
                hjDeviceProjectworkersService.insertHjDeviceProjectworkers(hp);
            }
        }
    }

    @Autowired
    private IHjCompanyProjectService hjCompanyProjectService;
    @Autowired
    private IHjCompanyHierarchyService hjCompanyHierarchyService;
    @PostMapping("/q")
    public void q(){
        List<HjCompanyProject> hpList=hjCompanyProjectService.selectHjCompanyProjectList(null);
        HjCompanyProject hp;

        for(int i=0;i<hpList.size();i++){
            hp=hpList.get(i);
           hp.setPath("0,"+hp.getPath());
            hjCompanyProjectService.updateHjCompanyProject(hp);
        }
    }
    @Autowired
    private IHjTeamService hjTeamService;
    @Autowired
    private IHjProjectPersonnelSynchronizationService hjProjectPersonnelSynchronizationService;
    @PostMapping("/aa")
    public void aa(){
        HjProjectWorkers hpw=new HjProjectWorkers();
        hpw.setProjectId(24);
        hpw.setEnterAndRetreatCondition(2);
        List<HjProjectWorkers> hpList=hjProjectWorkersService.selectHjProjectWorkersList(hpw);
        System.out.println(hpList.size());
        HjProjectWorkers hp;
        HjTeam ht;
        HjProjectPersonnelSynchronization hps=new HjProjectPersonnelSynchronization();
        hps.setProjectId(24);

        for(int i=0;i<hpList.size();i++){
            hp=hpList.get(i);
            Map<String, Object> map = hjProjectWorkersService.updateHjProjectWorkersOutOrIn(Convert.toStrArray(hp.getId().toString()), 0);


        }
    }
}
