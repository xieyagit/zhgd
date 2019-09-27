package com.hujiang.project.zhgd.hjTeam.api;


import com.hujiang.project.zhgd.hjTeam.domain.HjTeam;
import com.hujiang.project.zhgd.hjTeam.service.IHjTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: Provider01
 * @description: 班组接口
 * @author: Mr.LiuYong
 * @create: 2019-05-15 18:30
 **/
@RestController
@RequestMapping(value = "/provider/teamApi/app", method = RequestMethod.POST)
public class APP_TeamApi {

    @Autowired
    private IHjTeamService hjTeamService;



    /**
     * 查参建单位下的班组
     * @param hjTeam 参建单位id
     * @return
     */
    @RequestMapping(value = "selectTeam")
    public Map<String, Object> selectTeam(@RequestBody HjTeam hjTeam) {
        return hjTeamService.selectTeam(hjTeam);
    }






}
