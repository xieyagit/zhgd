package com.hujiang.project.zhgd.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.hq.hqController.HqController;
import com.hujiang.project.hq.xm.HqXm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/DevService",method = RequestMethod.POST)
public class HqApi {
    @Autowired
    private HqController hqController;
    @Autowired
    private HqXm hqXm;
    @RequestMapping(value = "/GetRequest.ashx")
    public JSONObject getRequest(@RequestBody String json)throws  Exception{
        System.out.println(json);

        return hqXm.xm(JSONObject.parseObject(json).getString("sn"));
    }
      @PostMapping(value = "/FeedBack.ashx")
    public void feedBack(@RequestBody String json){
        System.out.println("返回"+json);

          hqXm.feedBack(json);
    }
    @PostMapping(value = "/UploadAttendance.ashx")
    public void uploadAttendance(@RequestBody String json)throws Exception{
//        System.out.println("考勤"+json);
        hqController.zp(json);
//        client.feedBack(json);
    }

}
