package com.hujiang.project.zhgd.vehicleEquipment.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.vehicleEquipment.domain.VehicleEquipment;
import com.hujiang.project.zhgd.vehicleEquipment.service.IVehicleEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/provider/vehicleEquipment",method = RequestMethod.POST)
public class VehicleEquipmentApi {

    @Autowired
    private IVehicleEquipmentService vehicleEquipmentService;

    /**
     * 查询剩余车位数
     *
     * @return*/
    @PostMapping(value = "/residue")
    public JSONObject selectresidue(Integer deptID){
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("msg","查询成功");
        jsonObject.put("code",0);
        VehicleEquipment result = vehicleEquipmentService.selectresidue(deptID);
        jsonObject.put("data",result.getPkCount());
        return jsonObject;
    }

}
