package com.hujiang.project.zhgd.moredian.moredianPerson.controller;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.poi.ExcelUtil;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;

import com.hujiang.project.zhgd.moredian.MoredianMethod;
import com.hujiang.project.zhgd.moredian.moredianOrg.domain.MoredianOrg;
import com.hujiang.project.zhgd.moredian.moredianOrg.service.IMoredianOrgService;
import com.hujiang.project.zhgd.moredian.moredianPerson.domain.MoredianPerson;
import com.hujiang.project.zhgd.moredian.moredianPerson.service.IMoredianPersonService;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.HttpPost;
import com.hujiang.project.zhgd.utils.MoreDianAliyunOSSClientUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * 人员 信息操作处理
 *
 * @author hujiang
 * @date 2019-05-09
 */
@Controller
@RequestMapping("/moredian/moredianPerson")
public class MoredianPersonController extends BaseController {
    private String prefix = "moredian/moredianPerson";

    @Autowired
    private IMoredianPersonService moredianPersonService;
    @Autowired
    private IMoredianOrgService iMoredianOrgService;

    @RequiresPermissions("moredian:moredianPerson:view")
    @GetMapping()
    public String moredianPerson() {
        return prefix + "/moredianPerson";
    }

    /**
     * 查询人员列表
     */
    @RequiresPermissions("moredian:moredianPerson:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MoredianPerson moredianPerson) {
        startPage();
        List<MoredianPerson> list = moredianPersonService.selectMoredianPersonList(moredianPerson);
        return getDataTable(list);
    }


    /**
     * 导出人员列表
     */
    @RequiresPermissions("moredian:moredianPerson:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MoredianPerson moredianPerson) {
        List<MoredianPerson> list = moredianPersonService.selectMoredianPersonList(moredianPerson);
        ExcelUtil<MoredianPerson> util = new ExcelUtil<MoredianPerson>(MoredianPerson.class);
        return util.exportExcel(list, "moredianPerson");
    }

    /**
     * 新增人员
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put("org", iMoredianOrgService.selectMoredianOrgList(null));
        return prefix + "/add";
    }

    /**
     * 新增保存人员
     */
    @RequiresPermissions("moredian:moredianPerson:add")
    @Log(title = "人员", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HttpServletRequest request, MoredianPerson moredianPerson, MultipartFile image) throws Exception {

        //上传图片到oss服务器
        String url = MoreDianAliyunOSSClientUtil.uploadFileImg(image, "moredians", moredianPerson.getOrgId() + moredianPerson.getTpUserId() + System.currentTimeMillis() + ".jpg");
        System.out.println("url:"+url);
        //获取机构
        MoredianOrg moredianOrg = iMoredianOrgService.selectMoredianOrgById(Integer.parseInt(moredianPerson.getOrgId()));
        System.out.println(moredianOrg);
        //获取机构accessToken
        String orgAccessToken = MoredianMethod.getOrgAccessToken(moredianOrg.getOrgAuthKey(), moredianOrg.getOrgId());
        System.out.println("获取机构accessToken:" + orgAccessToken);
        //保存临时文件
        File localFile = new File(getPath(),"/"+image.getOriginalFilename());
        image.transferTo(localFile);
        System.out.println("getPath()+image.getOriginalFilename()"+getPath()+"/"+image.getOriginalFilename());

        //魔点添加人员
        HttpPost post = new HttpPost(Constants.MD + "/member/create?accessToken=" + orgAccessToken);
        post.addTextParams("tpUserId", moredianPerson.getTpUserId().toString());
        post.addTextParams("memberName", moredianPerson.getMemberName());
        post.addTextParams("mobile", moredianPerson.getMobile());
        post.addFileparams("verifyFace", new File(getPath()+"/"+image.getOriginalFilename()));
        JSONObject s = JSONObject.parseObject(post.send());
        //删除临时文件
        localFile.delete();
        System.out.println("魔点添加人员" + s);
        if(s.getString("result").equals("0")){
            moredianPerson.setMemberId(s.getString("data"));
            moredianPerson.setVerifyFace(url.substring(0, url.lastIndexOf("?")));
            moredianPerson.setOrgId(moredianOrg.getOrgId());
            return toAjax(moredianPersonService.insertMoredianPerson(moredianPerson));
        }
        return toAjax(0);
    }



    /**
     * 修改人员
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        MoredianPerson moredianPerson = moredianPersonService.selectMoredianPersonById(id);
        mmap.put("org", iMoredianOrgService.selectMoredianOrgList(null));
        mmap.put("moredianPerson", moredianPerson);
        return prefix + "/edit";
    }

    /**
     * 修改保存人员
     */
    @RequiresPermissions("moredian:moredianPerson:edit")
    @Log(title = "人员", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MoredianPerson moredianPerson, MultipartFile image) throws Exception {
        System.out.println(moredianPerson);
        System.out.println(image);
        System.out.println(image.isEmpty());

        MoredianOrg m = new MoredianOrg();
        m.setOrgId(moredianPerson.getOrgId());
        //获取机构
        MoredianOrg moredianOrg = iMoredianOrgService.selectMoredianOrgList(m).get(0);
        System.out.println(moredianOrg);
        //获取机构accessToken
        String orgAccessToken = MoredianMethod.getOrgAccessToken(moredianOrg.getOrgAuthKey(), moredianOrg.getOrgId());
        System.out.println("获取机构accessToken:" + orgAccessToken);

        String url = null;
        //魔点修改人员
        HttpPost post = new HttpPost(Constants.MD + "/member/update?accessToken=" + orgAccessToken);
        File localFile = null;
        //更新图片
        if (image != null&&!image.isEmpty()) {
            //删除原图片
            MoreDianAliyunOSSClientUtil.deleteFile(MoreDianAliyunOSSClientUtil.getOSSClient(), "moredian", moredianPerson.getVerifyFace().substring(moredianPerson.getVerifyFace().lastIndexOf("/") + 1, moredianPerson.getVerifyFace().length()));
            //上传图片到oss服务器
            url = MoreDianAliyunOSSClientUtil.uploadFileImg(image, "moredian", moredianPerson.getOrgId() + moredianPerson.getTpUserId() + System.currentTimeMillis() + ".jpg");
            moredianPerson.setVerifyFace(url.substring(0, url.lastIndexOf("?")));
            localFile = new File(getPath(),"/"+image.getOriginalFilename());
            image.transferTo(localFile);
            System.out.println("getPath()+image.getOriginalFilename()"+getPath()+image.getOriginalFilename());
            post.addFileparams("verifyFace", new File(getPath()+"/"+image.getOriginalFilename()));

        }

        post.addTextParams("memberId", moredianPerson.getMemberId());
        post.addTextParams("memberName", moredianPerson.getMemberName());
        post.addTextParams("mobile", moredianPerson.getMobile());
        System.out.println(post);
        System.out.println(moredianPerson);
        JSONObject s = JSONObject.parseObject(post.send());
        if (image != null&&!image.isEmpty()) {
            localFile.delete();
        }


        System.out.println("魔点修改人员" + s);
        if(s.getString("result").equals("0")){
            moredianPerson.setMemberId(s.getString("data"));

            moredianPerson.setOrgId(moredianOrg.getOrgId());
            return toAjax(moredianPersonService.updateMoredianPerson(moredianPerson));
        }
        return toAjax(0);
    }

    /**
     * 删除人员
     */
    @RequiresPermissions("moredian:moredianPerson:remove")
    @Log(title = "人员", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        int result=0;
        String[] split = ids.split(",");
        for (int i = 0; i < split.length; i++) {//分割获取人员
            MoredianPerson moredianPerson = moredianPersonService.selectMoredianPersonById(Integer.parseInt(split[i]));
            System.out.println(moredianPerson);
            MoredianOrg m = new MoredianOrg();
            m.setOrgId(moredianPerson.getOrgId());
            //获取机构
            MoredianOrg moredianOrg = iMoredianOrgService.selectMoredianOrgList(m).get(0);
            System.out.println(moredianOrg);
            //获取机构accessToken
            String orgAccessToken = MoredianMethod.getOrgAccessToken(moredianOrg.getOrgAuthKey(), moredianOrg.getOrgId());
            System.out.println("获取机构accessToken:" + orgAccessToken);

            //删除原图片
            MoreDianAliyunOSSClientUtil.deleteFile(MoreDianAliyunOSSClientUtil.getOSSClient(), "moredian", moredianPerson.getVerifyFace().substring(moredianPerson.getVerifyFace().lastIndexOf("/") + 1, moredianPerson.getVerifyFace().length()));
            JSONObject o = new JSONObject();
            o.put("memberId", moredianPerson.getMemberId());
            JSONObject object = MoredianMethod.deleteUser(orgAccessToken, o);
            System.out.println("删除魔点人员：" + object);
            if(object.getString("result")!=null && object.getString("result").equals("0")){
                result+=moredianPersonService.deleteMoredianPersonByIds(ids);
            }
        }
        return toAjax(result);
    }


    //得到文件路径
    private String getPath() throws Exception {
        //路径
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) {
            path = new File("");
        }
//如果上传目录为/static/images/upload/,则可以如下获取
        File upload = new File(path.getAbsolutePath(), "static/upload/");

        if (!upload.exists()) {
            upload.mkdirs();
//            System.out.println(upload.getAbsolutePath());
            //在开发测试模式时，得到地址为：{项目跟目录}/target/static/images/upload/
            //在打成jar正式发布时，得到的地址为:{发布jar包目录}/static/images/upload/
        }
        return upload.getAbsolutePath();
    }


}
