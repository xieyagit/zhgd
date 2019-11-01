package com.hujiang.project.zhgd.sbCameraInformation.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.sbCameraBinding.domain.SbCameraBinding;
import com.hujiang.project.zhgd.sbCameraBinding.service.ISbCameraBindingService;
import com.hujiang.project.zhgd.sbCameraInformation.domain.SbCameraInformation;
import com.hujiang.project.zhgd.sbCameraInformation.service.ISbCameraInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/provider/cameraInformation")
public class ApiSbCameraInformation {
    @Autowired
    private ISbCameraBindingService sbCameraBindingService;
    @Autowired
    private ISbCameraInformationService sbCameraInformationService;

    @PostMapping(value = "/selectInformation")
    public JSONObject information(@RequestBody JSONObject json){
        JSONObject object = new JSONObject();
        SbCameraInformation information = new SbCameraInformation();
        information.setDeviceSerial(json.getString("deviceSerial"));
        List<SbCameraInformation> sbCameraInformations = sbCameraInformationService.selectSbCameraInformationLists(information);
        object.put("data",sbCameraInformations);
        SbCameraBinding sbCameraBinding = new SbCameraBinding();
        sbCameraBinding.setEquipmentSerialNumber(json.getString("deviceSerial"));
        List<SbCameraBinding> sbCameraBindingList = sbCameraBindingService.selectSbCameraBindingList(sbCameraBinding);
        if (sbCameraBindingList.size() >0){
            object.put("hls",sbCameraBindingList.get(0).getHls());
            object.put("hlsHd",sbCameraBindingList.get(0).getHlsHd());
            object.put("rtmp",sbCameraBindingList.get(0).getRtmp());
            object.put("rtmpHd",sbCameraBindingList.get(0).getRtmpHd());
        }else {
            object.put("hls","");
            object.put("hlsHd","");
            object.put("rtmp","");
            object.put("rtmpHd","");
        }
        return object;
    }
}
