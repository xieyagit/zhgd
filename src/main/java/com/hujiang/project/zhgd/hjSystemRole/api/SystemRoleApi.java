package com.hujiang.project.zhgd.hjSystemRole.api;

import com.hujiang.project.zhgd.hjSystemRole.service.IHjSystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-05-20 19:15
 **/
@RestController
@RequestMapping(value = "/provider/systemrole",method = RequestMethod.POST)
public class SystemRoleApi {
    @Autowired
    private IHjSystemRoleService hjSystemRoleService;

}
