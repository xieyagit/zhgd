package com.hujiang.project.zhgd.sbCameraBinding.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.sbCameraBinding.domain.SbCameraBinding;
import com.hujiang.project.zhgd.sbCameraBinding.service.ISbCameraBindingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/provider/camera")
public class ApiSbCameraBinding {
    @Autowired
    private ISbCameraBindingService iSbCameraBindingService;

    @PostMapping(value = "/selectBinding")
    public JSONObject cameraBinding(@RequestBody JSONObject json) {
        JSONArray array = new JSONArray();
        if (json.size() == 0) {
            return json;
        }
        if (json.get("projectId") == null || json.get("typeId") == null) {
            System.out.println("缺失必传参数！！！");
        }
        SbCameraBinding sbCameraBinding = new SbCameraBinding();
        sbCameraBinding.setProjectId(json.getInteger("projectId"));
        sbCameraBinding.setTypeId(json.getInteger("typeId"));
        List<SbCameraBinding> sbCameraBindingList = iSbCameraBindingService.selectSbCameraBindingList(sbCameraBinding);
        for (SbCameraBinding sbCameraBinding1 : sbCameraBindingList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",sbCameraBinding1.getId());
            jsonObject.put("equipmentSerialNumber", sbCameraBinding1.getEquipmentSerialNumber());//设备序列号
            jsonObject.put("equipmentName", sbCameraBinding1.getEquipmentName());//设备名称
            jsonObject.put("typeId", sbCameraBinding1.getTypeId());//摄像头类型（1危区2安全帽3摄像头）
            jsonObject.put("hls", sbCameraBinding1.getHls());
            jsonObject.put("hlsHd", sbCameraBinding1.getHlsHd());
            jsonObject.put("rtmp", sbCameraBinding1.getRtmp());
            jsonObject.put("rtmpHd", sbCameraBinding1.getRtmpHd());
            array.add(jsonObject);
        }
        JSONObject object = new JSONObject();
        object.put("data", array);
        return object;
    }
}
