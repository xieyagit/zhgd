package com.hujiang.common.utils;

import com.aliyun.oss.OSSClient;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import com.hujiang.project.zhgd.utils.Constants;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FaceMatchUtil {

    private static Logger logger = LoggerFactory.getLogger(FaceMatchUtil.class);
    /**
     * 百度人脸对比
     *
     * @param url1 照片url
     * @param url2 照片url
     * @return
     */
    public static Integer contrast(String url1, String url2) {

        Integer number = 1;
        Integer num = 0;
        AipFace aipFace = new AipFace(Constants.BD_APP_ID, Constants.BD_API_KEY, Constants.BD_SECRET_KEY);
        List<MatchRequest> input = new ArrayList<>();
        input.add(new MatchRequest(url1, "URL"));
        input.add(new MatchRequest(url2, "URL"));
        logger.error("\r\nIOS FACE========>" + url2);
        JSONObject result = aipFace.match(input);
        if (result.getString("error_msg").equals("SUCCESS")) {
            Double score = result.getJSONObject("result").getDouble("score");
            if (score >= Constants.FACESCORE) {
                return number;
            }
        }
        return num;
    }

    public static void deleteUrl(String url) {
        OSSClient ossClient = AliyunOSSClientUtil.getOSSClient();
        String[] id = url.split("/");
        String substring = url.substring(url.lastIndexOf(".com/") + 5);
        String folder = substring.substring(0, substring.indexOf("/"));
        String file = null;
        for (int i = 0; i < id.length; i++) {
            file = id[i];
        }
        AliyunOSSClientUtil.deleteFile(ossClient, "hujiang", folder, file);
    }

}
