package com.hujiang.project.client;

import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "user-web")
public interface SystemClient {

    @PostMapping("/api/instructions/oneLowerHair")
    public void oneLowerHair(@RequestParam(value = "deviceNo") String deviceNo, @RequestBody HjProjectWorkers hpw);
    @PostMapping("/api/instructions/oneClean")
    public int oneClean(@RequestParam(value = "deviceNo") String deviceNo,@RequestParam(value = "projectWorkersId") Integer projectWorkersId);
    @RequestMapping(value = "/api/lapiFace/insertPerson",method = RequestMethod.POST)
    public void insertPerson(@RequestBody String json,@RequestParam String sn);
    @RequestMapping(value = "/api/lapiFace/deletePerson",method = RequestMethod.POST)
    public void deletePerson(@RequestParam Integer personId,@RequestParam String sn);

}
