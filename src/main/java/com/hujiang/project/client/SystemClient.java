package com.hujiang.project.client;

import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-web")
public interface SystemClient {

    @PostMapping("/api/instructions/oneLowerHair")
    public void oneLowerHair(@RequestParam(value = "deviceNo") String deviceNo, @RequestBody HjProjectWorkers hpw);
    @PostMapping("/api/instructions/oneClean")
    public int oneClean(@RequestParam(value = "deviceNo") String deviceNo,@RequestParam(value = "projectWorkersId") Integer projectWorkersId);
}
