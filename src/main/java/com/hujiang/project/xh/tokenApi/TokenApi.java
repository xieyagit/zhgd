package com.hujiang.project.xh.tokenApi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.http.HttpUtils;
import com.hujiang.project.xh.utils.HttpUtilsXh;
import com.hujiang.project.zhgd.hjDictionaries.domain.HjDictionaries;
import com.hujiang.project.zhgd.hjDictionaries.service.IHjDictionariesService;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 星河授权
 */
@Repository("tokenApi")
public class TokenApi {

    @Resource
    private IHjDictionariesService dictionariesService;
    @Resource
    private IHjSynchronizationInformationService hjSynchronizationInformationService;
        /**
         * 获取授权码
         */
    public  String getToken(String appId,String secret) throws Exception{
        String param="grant_type=client_credential&appid="+appId+"&secret="+secret;
        String url=Constants.XH_MJ+"/api/v3/token?"+param;
        String string= HttpUtilsXh.xhHttpGet(url,"");
        JSONObject jo=JSONObject.parseObject(string);
        return jo.getString("access_token");
    }
    /**
     * 验证授权码
     */
    public  void tokenCheck(String token) throws  Exception{
        String string= HttpUtilsXh.xhHttpGet(Constants.XH_MJ+"/api/v3/token/check",token);
    }
    /**
     * 获取更新星河工种
     */
    public  void getEntranceGuardProfession(String token) throws  Exception{
        String string=HttpUtilsXh.xhHttpGet(Constants.XH_MJ+"/api/v3/entrance-guard/profession",token);
        JSONArray workList=JSONArray.parseArray(string);

        //先删除库里旧的工种
        dictionariesService.deleteHjDictionariesByTwo();
        HjDictionaries dict;
        JSONObject jo;
        for(Object object: workList){
            jo=JSONObject.parseObject(object.toString());
            System.out.println(jo.getString("id"));
                dict=new HjDictionaries();
                dict.setTag(jo.getString("id"));
                dict.setTitle(jo.getString("name"));
                dict.setCategory("WORK_TYPE_XH");
                dict.setGroupTitle("星河工种");
//            System.out.println(dict);
            dictionariesService.insertHjDictionaries(dict);
        }
    }

    /**
     * 生成一指通令牌 token  同步星河的
     */
    public String yiZhiTongToken(Integer pid) throws Exception{
        String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        //通过项目ID获取秘钥
        HjSynchronizationInformation hj=new HjSynchronizationInformation();
        hj.setProjectId(pid);
        hj.setApiType("keytype1");
        hj.setPlatformName("YIZHITONG");
        HjSynchronizationInformation key=hjSynchronizationInformationService.selectHjSynchronizationInformationList(hj).get(0);
        StringBuilder str4MD5 = new StringBuilder();
        str4MD5.append("xmcode=").append(key.getProjectNumber())
        .append("&xmjc=").append(key.getClientSerial())
        .append("&api_key_szzjt=").append(key.getApiKey())
        .append("&api_secret_szzjt=").append(key.getApiSecret())
        .append("&timestamp=").append(simpleDateFormat);
        String token=getMD5(str4MD5.toString());
        return token;
    }
    /**
     * 生成一指通令牌 token
     */
    public String yiZhiTongTokenTwo(Integer pid) throws Exception{
        String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        //通过项目ID获取秘钥
        HjSynchronizationInformation hj=new HjSynchronizationInformation();
        hj.setProjectId(pid);
        hj.setApiType("keytype1");
        hj.setPlatformName("YIZHITONG2");
        HjSynchronizationInformation key=hjSynchronizationInformationService.selectHjSynchronizationInformationList(hj).get(0);
        StringBuilder str4MD5 = new StringBuilder();
        str4MD5.append("xmcode=").append(key.getProjectNumber())
                .append("&xmjc=").append(key.getClientSerial())
                .append("&api_key_szzjt=").append(key.getApiKey())
                .append("&api_secret_szzjt=").append(key.getApiSecret())
                .append("&timestamp=").append(simpleDateFormat);
        String token=getMD5(str4MD5.toString());
        return token;
    }
    public String yiZhiTongTokenThree(Integer pid) throws Exception{
        String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        //通过项目ID获取秘钥
        HjSynchronizationInformation hj=new HjSynchronizationInformation();
        hj.setProjectId(pid);
        hj.setApiType("keytype1");
        hj.setPlatformName("YIZHITONG4");
        HjSynchronizationInformation key=hjSynchronizationInformationService.selectHjSynchronizationInformationList(hj).get(0);
        StringBuilder str4MD5 = new StringBuilder();
        str4MD5.append("xmcode=").append(key.getProjectNumber())
                .append("&xmjc=").append(key.getClientSerial())
                .append("&api_key_szzjt=").append(key.getApiKey())
                .append("&api_secret_szzjt=").append(key.getApiSecret())
                .append("&timestamp=").append(simpleDateFormat);
        String token=getMD5(str4MD5.toString());
        return token;
    }
    /**
     * MD5加密32位小写
     * @return
     */
    public String getMD5(String str)throws  Exception{

        String result = "";

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update((str).getBytes("UTF-8"));
        byte b[] = md5.digest();

        int i;
        StringBuffer buf = new StringBuffer("");

        for(int offset=0; offset<b.length; offset++){
            i = b[offset];
            if(i<0){
                i+=256;
            }
            if(i<16){
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }

        result = buf.toString();
        return result;
    }
}
