package com.hujiang.project.zhgd.kqbb;

import com.hujiang.project.zhgd.kqbb.domain.BG;
import com.hujiang.project.zhgd.kqbb.domain.Kqbb;
import com.hujiang.project.zhgd.kqbb.service.IKqbbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-07-05 15:06
 **/
@RestController
@RequestMapping(value = "/provider/kqbb")
public class KqbbAPI {
    @Autowired
    private IKqbbService service;


    @PostMapping("getKqbbList")
    public List<Kqbb> getKqbbList(Integer projectId,String time){
        Kqbb kqbb = new Kqbb();
        kqbb.setProjectId(projectId);
        kqbb.setATime(time);
        return service.selectKqbbList(kqbb);

    }

    @PostMapping("getKqbbListBb")
    public List<BG> getKqbbListBb(Integer projectId){
        return service.selectKqbbListBb(projectId);
    }

}
