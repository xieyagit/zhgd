package com.hujiang.project.zhgd.hjUserFeedback.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.hjUserFeedback.domain.HjUserFeedback;
import com.hujiang.project.zhgd.hjUserFeedback.service.IHjUserFeedbackService;
import com.hujiang.project.zhgd.utils.AliyunOSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 用户反馈
 */
@RestController
@RequestMapping(value = "/provider/userFeedbackApp", method = RequestMethod.POST)
public class HjUserFeedbackApi {
    @Autowired
    private IHjUserFeedbackService  userFeedbackService;

    /**
     * 增加用户反馈
     * @param hjUserFeedback 必填 1.file 2.problem 3.phone 4.email 5.userId（根据用户ID增加反馈）
     * @return
     * @throws IOException
     */
    @PostMapping("/insertUserfeedback")
    public JSONObject insertUserFeedback(HjUserFeedback hjUserFeedback) throws IOException {
        JSONObject result = new JSONObject();
        HjUserFeedback userFeedback = new HjUserFeedback();
        for(int i=0;i<hjUserFeedback.getFile().length;i++){
            userFeedback.setFileName(hjUserFeedback.getFile()[i].getOriginalFilename());      //文件名称
            userFeedback.setProblem(hjUserFeedback.getProblem());   //问题
            userFeedback.setPhone(hjUserFeedback.getPhone());       //联系电话
            userFeedback.setEmail(hjUserFeedback.getEmail());       //邮箱
            userFeedback.setUserId(hjUserFeedback.getUserId());     //用户ID
            String url = AliyunOSSClientUtil.uploadFileImg(hjUserFeedback.getFile()[i],"zhgd_img",userFeedback.getFileName());
            String path =  url.substring(0,url.lastIndexOf("?"));   //截取"?" 后面字符- - 文件路径
            userFeedback.setFilePath(path);
            int temp = userFeedbackService.insertHjUserFeedback(userFeedback);
            result.put("msg",temp > 0 ? "反馈成功":"反馈失败");
            result.put("code",temp > 0 ? 0:-1);
        }
        return result;
    }
}
