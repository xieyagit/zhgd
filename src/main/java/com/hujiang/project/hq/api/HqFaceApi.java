package com.hujiang.project.hq.api;

import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkers;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.service.IHjDeviceProjectworkersService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/provider/instructions")
public class HqFaceApi {
    @Autowired
    private IHjDeviceProjectworkersService hjDeviceProjectworkersService;

    /**
     * 获取没录进设备的人脸
     * @param deviceNo
     * @param pid
     * @return
     */
    @PostMapping("/getPWD")
    public List<HjProjectWorkers> getPWD(@RequestParam(value = "deviceNo")String deviceNo,@RequestParam(value = "pid") String pid ){
        Map<String,String> param=new HashMap<String,String>();
        param.put("deviceNo",deviceNo);
        param.put("projectId",pid);
        return hjDeviceProjectworkersService.selectHjProjectworkersList(param);
    }
    /**
     * 保存录进设备的人脸记录
     */
    @PostMapping("/setPWD")
    public  int setPWD(@RequestBody HjDeviceProjectworkers hjDeviceProjectworkers){
    return     hjDeviceProjectworkersService.insertHjDeviceProjectworkers(hjDeviceProjectworkers);
    }
    /**
     * 清除人脸
     */
    @PostMapping("/clean")
    public List<HjDeviceProjectworkers> clean(@RequestParam(value = "deviceNo")String deviceNo){
        HjDeviceProjectworkers h=new HjDeviceProjectworkers();
        h.setDeviceNo(deviceNo);
        return hjDeviceProjectworkersService.selectHjDeviceProjectworkersList(h);
    }
    /**
     * 删除
     */
    @PostMapping("/deleteId")
    public void deleteId(@RequestParam(value = "id")Integer id){
      hjDeviceProjectworkersService.deleteHjDeviceProjectworkersByIds(id.toString());

    }
}
