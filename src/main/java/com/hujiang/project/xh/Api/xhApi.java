package com.hujiang.project.xh.Api;


import com.hujiang.project.xh.tokenApi.TokenApi;
import com.hujiang.project.xh.utils.HttpUtilsXh;
import com.hujiang.project.zhgd.hjCompanyLibrary.controller.HjCompanyLibraryController;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.utils.Constants;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.logging.Logger;

/**
 * 星河用户
 *
 * @author hujiang
 * @date 2019-05-19
 */
@Repository("xhHttpApi")
public class xhApi {
    private Logger logger = Logger.getLogger(xhApi.class.getName());
    @Resource
    private IHjSynchronizationInformationService hjSynchronizationInformationService;

    @Resource
    private TokenApi tokenApi;

    /**
     * 星河添加用户
     *
     * @param
     * @return
     */
    public void queryWorkType(JSONObject param,String pid) throws Exception{

        //通过项目ID获取秘钥
        HjSynchronizationInformation hj=new HjSynchronizationInformation();
        hj.setProjectId(Integer.valueOf(pid));
        hj.setApiType("keytype1");
        hj.setPlatformName("XINGHE");
        HjSynchronizationInformation key=hjSynchronizationInformationService.selectHjSynchronizationInformationList(hj).get(0);
        String token=tokenApi.getToken(key.getApiKey(),key.getApiSecret());
        String result= HttpUtilsXh.xhHttpPost(Constants.XH_MJ+"/api/v3/entrance-guard/regions/"+key.getProjectNumber()+"/users",param.toString(),token);
        System.out.println("添加星河用户"+result);
    }

    /**
     * 星河修改用户信息
     * @param param 需要的用户信息
     * @param pid 项目id
     * @param userId 用户id
     * @throws Exception
     */
    public void updateUsersLocal(JSONObject param,String pid,String userId)throws  Exception{
        //通过项目ID获取秘钥
        HjSynchronizationInformation hj=new HjSynchronizationInformation();
        hj.setProjectId(Integer.valueOf(pid));
        hj.setApiType("keytype1");
        hj.setPlatformName("XINGHE");
        HjSynchronizationInformation key=hjSynchronizationInformationService.selectHjSynchronizationInformationList(hj).get(0);
        String token=tokenApi.getToken(key.getApiKey(),key.getApiSecret());
        String result=HttpUtilsXh.xhHttpPatch(Constants.XH_MJ+"/api/v3/entrance-guard/regions/"+key.getProjectNumber()+"/users/"+userId,param.toString(),token);
        System.out.println("修改用户信息"+result);
    }

    /**
     * 删除星河用户
     * @param pid 项目id
     * @param userId 用户Id
     * @throws Exception
     */
    public void deleteUsers(String pid,String userId)throws  Exception{
        //通过项目ID获取秘钥
        HjSynchronizationInformation hj=new HjSynchronizationInformation();
        hj.setProjectId(Integer.valueOf(pid));
        hj.setApiType("keytype1");
        hj.setPlatformName("XINGHE");
        HjSynchronizationInformation key=hjSynchronizationInformationService.selectHjSynchronizationInformationList(hj).get(0);
        String token=tokenApi.getToken(key.getApiKey(),key.getApiSecret());
        String result=HttpUtilsXh.xhHttpDelete(Constants.XH_MJ+"/api/v3/entrance-guard/regions/"+key.getProjectNumber()+"/users/"+userId,token);
        System.out.println("删除用户信息"+result);
    }

    /**
     * 推送门禁记录
     * @param param 门禁记录列表
     * @param pid 项目id
     * @throws Exception
     */
    public void setRecord(JSONArray param,String pid) throws  Exception{
        //通过项目ID获取秘钥
        HjSynchronizationInformation hj=new HjSynchronizationInformation();
        hj.setProjectId(Integer.valueOf(pid));
        hj.setApiType("keytype1");
        hj.setPlatformName("XINGHE");
        HjSynchronizationInformation key=hjSynchronizationInformationService.selectHjSynchronizationInformationList(hj).get(0);
        String token=tokenApi.getToken(key.getApiKey(),key.getApiSecret());
        String result=HttpUtilsXh.xhHttpPost(Constants.XH_MJ+"/api/v3/entrance-guard/regions/"+key.getProjectNumber()+"/push",param.toString(),token);
        System.out.println("门禁记录"+result);
        logger.info("星河门禁记录"+result);
    }
}
