package com.hujiang.project.zhgd.utils;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.exception.BusinessException;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.moredian.MoredianMethod;
import com.hujiang.project.zhgd.moredian.moredianGroup.domain.MoredianGroup;
import com.hujiang.project.zhgd.moredian.moredianGroup.service.IMoredianGroupService;
import com.hujiang.project.zhgd.moredian.moredianGroupPerson.domain.MoredianGroupPerson;
import com.hujiang.project.zhgd.moredian.moredianGroupPerson.service.IMoredianGroupPersonService;
import com.hujiang.project.zhgd.moredian.moredianOrg.domain.MoredianOrg;
import com.hujiang.project.zhgd.moredian.moredianOrg.service.IMoredianOrgService;
import com.hujiang.project.zhgd.moredian.moredianPerson.domain.MoredianPerson;
import com.hujiang.project.zhgd.moredian.moredianPerson.service.IMoredianPersonService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

import static com.hujiang.project.zhgd.utils.BASE64DecodedMultipartFile.Base64ToImage;
import static com.hujiang.project.zhgd.utils.BASE64DecodedMultipartFile.ImageToBase64ByOnline;
import static com.hujiang.project.zhgd.utils.Util.getPath;

/**
 * @program: Provider01
 * @description: 魔点考勤
 * @author: Mr.LiuYong
 * @create: 2019-06-14 11:26
 **/
@Component
public class MoredianClient {
    private Logger logger = Logger.getLogger(MoredianClient.class.getName());
    @Autowired
    private IMoredianPersonService moredianPersonService; //魔点人员
    @Autowired
    private IMoredianOrgService iMoredianOrgService; //魔点机构

    @Autowired
    private IMoredianGroupService moredianGroupService;//项目群组
    @Autowired
    private IMoredianGroupPersonService moredianGroupPersonService;//群组人员
    @Resource
    private HttpServletResponse response;


    /**
     * 魔点
     * 人员进退场操作，执行添加或删除魔点人员信息，并执行人员绑定魔点群组（群组对应项目）或人员解除魔点群组关系
     * @param projectWorkers 人员信息
     * @param tag 1：进场  2：退场 3：修改
     * @return
     */
    public boolean enteringMoredianPerson( HjProjectWorkers projectWorkers,Integer tag)throws Exception{
        boolean result=false;
        logger.info("执行魔点人员操作");
        //查询项目对应群组
        MoredianGroup moredianGroup = new MoredianGroup();
        moredianGroup.setProjectId(projectWorkers.getProjectId());
        //获取群组信息
        List<MoredianGroup> moredianGroups = moredianGroupService.selectMoredianGroupList(moredianGroup);
        for(MoredianGroup mg:moredianGroups){
            //获取机构
            MoredianOrg moredianOrg = iMoredianOrgService.selectMoredianOrgByGroupId(mg.getGroupId());
            //获取机构accessToken
            String orgAccessToken = MoredianMethod.getOrgAccessToken(moredianOrg.getOrgAuthKey(), moredianOrg.getOrgId());
            logger.info("获取机构accessToken:" + orgAccessToken);
            //------------------------------------------------------------------------------
            if(tag==1){//人员进场
                logger.info("执行魔点添加人员操作");
                boolean p=false;
                boolean g=false;
                //保存临时文件
                Base64ToImage(ImageToBase64ByOnline(projectWorkers.getFaceUrl()),getPath()+"/"+projectWorkers.getIdCode()+".jpg");
                logger.info("临时人脸图"+Base64ToImage(ImageToBase64ByOnline(projectWorkers.getFaceUrl()),getPath()+"/"+projectWorkers.getIdCode()+".jpg"));
                File localFile = new File(getPath() + "/" + projectWorkers.getIdCode() + ".jpg");

                //魔点添加人员
                HttpPost post = new HttpPost(Constants.MD + "/member/create?accessToken=" + orgAccessToken);
                post.addTextParams("tpUserId", projectWorkers.getId()+"");
                post.addTextParams("memberName",projectWorkers.getEmpName());
                post.addTextParams("mobile", "13111111111");
                post.addFileparams("verifyFace", localFile);
                JSONObject s = JSONObject.parseObject(post.send());
                //删除临时文件
                localFile.delete();
                logger.info("魔点添加人员" + s);
                MoredianPerson moredianPerson = new MoredianPerson();
                if(s.getString("result").equals("0")){
                    moredianPerson.setMemberId(s.getString("data"));
                    moredianPerson.setMemberName(projectWorkers.getEmpName());
                    moredianPerson.setVerifyFace(projectWorkers.getFaceUrl());
                    moredianPerson.setTpUserId(projectWorkers.getId());
                    moredianPerson.setOrgId(moredianOrg.getOrgId());
                    int i = moredianPersonService.insertMoredianPerson(moredianPerson);
                    logger.info("添加人员信息" + i);
                    p=true;

                }
                MoredianGroupPerson moredianGroupPerson = new MoredianGroupPerson();
                moredianGroupPerson.setGroupId(mg.getGroupId());
                moredianGroupPerson.setMemberId(s.getString("data"));
                //增加人员群组
                JSONObject o = new JSONObject();
                o.put("groupId",mg.getGroupId());//群组id
                o.put("memberId",s.getString("data"));
                JSONObject object = MoredianMethod.bindGroup(orgAccessToken, o);
                if(object.getString("result").equals("0")){
                    logger.info("添加群组人员 ："+object);
                    int i = moredianGroupPersonService.insertMoredianGroupPerson(moredianGroupPerson);

                    g=true;
                }

                if( p && g ){
                    result=true;
                    logger.info("群组人员信息true" );
                }

            }else if(tag==2) {//人员退场
                MoredianPerson moredianPerson = new MoredianPerson();
                moredianPerson.setTpUserId(projectWorkers.getId());
                //根据人员id查询魔点人员信息
                List<MoredianPerson> moredianPeople = moredianPersonService.selectMoredianPersonList(moredianPerson);
                for(MoredianPerson moredianPerson1:moredianPeople){
                    JSONObject o = new JSONObject();
                    o.put("groupId",mg.getGroupId());
                    o.put("memberId",moredianPerson1.getMemberId());
                    //移除魔点人员关系
                    JSONObject object = MoredianMethod.unbindGroup(orgAccessToken, o);
                    if(object.getString("result")!=null && object.getString("result").equals("0")){
                        //删除魔点人员关系数据
                        int i = moredianGroupPersonService.deleteMoredianGroupPersonBymemberId(moredianPerson1.getMemberId());
                        logger.info("删除魔点人员关系数据" + i);
                        if(i>0){
                            //删除魔点人员信息
                            JSONObject object1 = new JSONObject();
                            object1.put("memberId", moredianPerson1.getMemberId());
                            JSONObject jsonObject = MoredianMethod.deleteUser(orgAccessToken, object1);
                            System.out.println("删除魔点人员：" + jsonObject);
                            if(jsonObject.getString("result")!=null && jsonObject.getString("result").equals("0")){
                                int i1 = moredianPersonService.deleteMoredianPersonByIds(moredianPerson1.getId().toString());
                                if(i1>0){
                                    result=true;
                                }
                            }

                        }
                    }

                }

            }else if(tag==3){
                //修改魔点人员信息
                MoredianPerson moredianPerson = new MoredianPerson();
                moredianPerson.setTpUserId(projectWorkers.getId());
                //根据人员id查询魔点人员信息
                List<MoredianPerson> moredianPeople = moredianPersonService.selectMoredianPersonList(moredianPerson);

                for(MoredianPerson moredianPerson1:moredianPeople){
                    //保存临时文件
                    Base64ToImage(ImageToBase64ByOnline(projectWorkers.getFaceUrl()),getPath()+"/"+projectWorkers.getIdCode()+".jpg");
                    logger.info("临时人脸图"+Base64ToImage(ImageToBase64ByOnline(projectWorkers.getFaceUrl()),getPath()+"/"+projectWorkers.getIdCode()+".jpg"));
                    File localFile = new File(getPath() + "/" + projectWorkers.getIdCode() + ".jpg");

                    //魔点修改人员
                    HttpPost post = new HttpPost(Constants.MD + "/member/update?accessToken=" + orgAccessToken);
                    post.addTextParams("memberId", moredianPerson1.getMemberId());
                    post.addTextParams("memberName",projectWorkers.getEmpName());
                    post.addFileparams("verifyFace", localFile);
                    JSONObject s = JSONObject.parseObject(post.send());

                    //删除临时文件
                    localFile.delete();
                    if(s.getString("result").equals("0")){
                        moredianPerson1.setVerifyFace(projectWorkers.getFaceUrl());
                        moredianPerson1.setMemberName(projectWorkers.getEmpName());
                        moredianPerson1.setId(moredianPerson1.getId());
                        int i = moredianPersonService.updateMoredianPerson(moredianPerson1);
                        if(i>0){
                            result=true;
                        }
                    }
                }

            }

        }
        return result;
    }
}
