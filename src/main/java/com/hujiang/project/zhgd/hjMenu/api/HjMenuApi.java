package com.hujiang.project.zhgd.hjMenu.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.hjInformation.domain.HjInformation;
import com.hujiang.project.zhgd.hjInformation.service.IHjInformationService;
import com.hujiang.project.zhgd.hjMenu.domain.HjMenu;
import com.hujiang.project.zhgd.hjMenu.service.IHjMenuService;
import com.hujiang.project.zhgd.utils.ZCAPIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping( value = "/provider/menu")
public class HjMenuApi {

    @Autowired
    IHjMenuService menuService;
    @Autowired
    IHjInformationService hjInformationService;

    /**
     * 查询菜单
     * */
    @PostMapping(value = "/seleall")
    public JSONObject seleall(@RequestBody HjMenu menu) throws IOException, URISyntaxException {
        JSONObject object = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        List<HjMenu> results = menuService.selealls(menu.getTypeId());
        for (HjMenu hjMenu : results){
            JSONObject obj = new JSONObject();
            obj.put("typeId",hjMenu.getTypeId());
            obj.put("size",hjMenu.getId());
            obj.put("id",hjMenu.getId());
            obj.put("materialName",hjMenu.getMaterialName());
            menu.setId(hjMenu.getId());
            HjMenu result = menuService.seleall(menu);
            if (result != null){
                obj.put("uploadingDate",result.getUploadingDate());
                obj.put("uploadingname",result.getUploadingname());
                obj.put("uploadingType", "已上传");
            }else{
                obj.put("uploadingDate","");
                obj.put("uploadingname","");
                obj.put("uploadingType", "未上传");
            }
            jsonArray.add(obj);
        }
        object.put("list",jsonArray);
        return object;
    }
}
