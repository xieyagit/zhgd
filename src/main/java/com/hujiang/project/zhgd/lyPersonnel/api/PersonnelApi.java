package com.hujiang.project.zhgd.lyPersonnel.api;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.lyPersonnel.domain.LyPersonnel;
import com.hujiang.project.zhgd.lyPersonnel.service.ILyPersonnelService;
import com.hujiang.project.zhgd.lyRegistrationRecord.service.ILyRegistrationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/provider/lyPersonnel",method = RequestMethod.POST)
public class PersonnelApi {
    @Autowired
    private ILyPersonnelService lyPersonnelService;
    @Autowired
    private ILyRegistrationRecordService lyRegistrationRecordService;
    @PostMapping("/insertPersonnel")
    public AjaxResult insertPersonnel(@RequestBody LyPersonnel lyPersonnel){
        LyPersonnel a=new LyPersonnel();
        a.setPid(lyPersonnel.getPid());
        a.setIdCode(lyPersonnel.getIdCode());
        List<LyPersonnel> lList=lyPersonnelService.selectLyPersonnelList(a);
        int i=0;
        if(lList.size()>0){
            lyPersonnel.setId(lList.get(0).getId());
          i=  lyPersonnelService.updateLyPersonnel(lyPersonnel);
        }else{
           i= lyPersonnelService.insertLyPersonnel(lyPersonnel);
        }

        lyPersonnelService.personnelInOUt(lyPersonnel,"0");
        if(i>0){
            return AjaxResult.success("登记成功");
        }else{
            return AjaxResult.error("登记失败");
        }

    }
}
