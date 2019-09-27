package com.hujiang.project.zhgd.moredian;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.Util;


/***
 * @program: LZ-2019-5-6
 * @description: 魔点api
 * @author: Mr.LiuYong
 * @create: 2019-05-08 09:15
 **/
public class MoredianMethod {
    private static final String appId = "1632860700986572800";
    private static final String appKey = "wWqowhlSVxwMxjBgoFv6SJGNQ1zSo5u0";




//    public static void main(String[] args) {
//        System.out.println(MoredianMethod.getAppToken());
//        System.out.println(MoredianMethod.getOrgAccessToken("9hRmn6pjkBoLOc5o7ro0Z2dZ4zdgImij","1633034020096835584"));
//    }


    /**
     * 功能描述 :获取AppToken
     *
     * @param
     * @return java.lang.String
     * @author Mr.LiuYong
     * @date 2019/5/8 15:47
     */
    public static String getAppToken() {
        String sendGet = Util.sendGet(Constants.MD + "/app/getAppToken", "appId=" + appId + "&appKey=" + appKey);
        JSONObject object = JSONObject.parseObject(sendGet);
        JSONObject data = JSONObject.parseObject(object.getString("data"));
        return data.getString("appToken");
    }

    /**
     * 功能描述 :创建机构
     *
     * @param * @param jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @author Mr.LiuYong
     * @date 2019/5/8 15:36
     */
    public static JSONObject createOrg(JSONObject jsonObject) {
        String createOrg = null;
        try {
            createOrg = Util.httpPostWithJSON(Constants.MD + "/app/createOrg?appToken=" + getAppToken(), jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(createOrg);
    }

    /**
     * 功能描述 :获取机构accessToken
     *
     * @param orgAuthKey orgId
     * @return java.lang.String
     * @author Mr.LiuYong
     * @date 2019/5/8 15:52
     */
    public static String getOrgAccessToken(String orgAuthKey, String orgId) {
        String sendGet = Util.sendGet(Constants.MD + "/app/getOrgAccessToken", "appToken=" + getAppToken() + "&orgAuthKey=" + orgAuthKey + "&orgId=" + orgId);
        return JSONObject.parseObject(JSONObject.parseObject(sendGet).getString("data")).getString("accessToken");
    }

    /**
     * 功能描述 :修改机构信息
     *
     * @param accessToken jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @author Mr.LiuYong
     * @date 2019/5/8 15:55
     */
    public static JSONObject update(String accessToken, JSONObject jsonObject) {

        String update = null;
        try {
            update = Util.httpPostWithJSON(Constants.MD + "/org/update?accessToken=" + accessToken, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(update);
    }

    /**
     * 功能描述 :设备激活
     *
     * @param accessToken jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @author Mr.LiuYong
     * @date 2019/5/8 15:55
     */
    public static JSONObject equipmentActivation(String accessToken, JSONObject jsonObject) {

        String activation = null;
        try {
            activation = Util.httpPostWithJSON(Constants.MD + "/device/activation?accessToken=" + accessToken, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(activation);
    }

    /**
     * 功能描述 :根据设备SN获取Id
     *
     * @param accessToken, deviceSn
     * @return java.lang.String
     * @author Mr.LiuYong
     * @date 2019/5/8 16:50
     */
    public static String getDeviceId(String accessToken, String deviceSn) {

        String sendGet = Util.sendGet(Constants.MD + "/device/deviceId", "accessToken=" + accessToken + "&deviceSn=" + deviceSn);
        return JSONObject.parseObject(sendGet).getString("data");
    }

    /**
     * 功能描述 :更新设备控制信息
     *
     * @param accessToken jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @author Mr.LiuYong
     * @date 2019/5/8 16:52
     */
    public static JSONObject updateDevice(String accessToken, JSONObject jsonObject) {

        String updateDevice = null;
        try {
            updateDevice = Util.httpPostWithJSON(Constants.MD + "/device/deviceId?accessToken=" + accessToken, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(updateDevice);
    }

    /**
     * 功能描述 :设备解绑
     *
     * @param accessToken jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @author Mr.LiuYong
     * @date 2019/5/8 16:55
     */
    public static JSONObject deviceUnbound(String accessToken, JSONObject jsonObject) {
        String deviceUnbound = null;
        try {
            deviceUnbound = Util.httpPostWithJSON(Constants.MD + "/device/unbind?accessToken=" + accessToken, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(deviceUnbound);
    }

    /**
     * 功能描述 :设备后台管理密码获取
     *
     * @param accessToken jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @author Mr.LiuYong
     * @date 2019/5/8 16:55
     */
    public static JSONObject getDynamicPwd(String accessToken, JSONObject jsonObject) {
        String getDynamicPwd = null;
        try {
            getDynamicPwd = Util.httpPostWithJSON(Constants.MD + "/device/getDynamicPwd?accessToken=" + accessToken, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(getDynamicPwd);
    }

    /**
     * 功能描述 :创建人员
     *
     * @param accessToken jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @author Mr.LiuYong
     * @date 2019/5/8 16:55
     */
    public static JSONObject createUser(String accessToken, JSONObject jsonObject) {
        String createUser = null;
        try {
            createUser = Util.httpPostWithForm(Constants.MD + "/member/create?accessToken=" + accessToken, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(createUser);
    }


    /**
     * 功能描述 :修改人员
     *
     * @param accessToken jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @author Mr.LiuYong
     * @date 2019/5/8 16:55
     */
    public static JSONObject updateUser(String accessToken, JSONObject jsonObject) {
        String updateUser = null;
        try {
            updateUser = Util.httpPostWithForm(Constants.MD + "/member/update?accessToken=" + accessToken, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(updateUser);
    }

    /**
     * 功能描述 :删除人员
     *
     * @param accessToken jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @author Mr.LiuYong
     * @date 2019/5/8 16:55
     */
    public static JSONObject deleteUser(String accessToken, JSONObject jsonObject) {
        String deleteUser = null;
        try {
            deleteUser = Util.httpPostWithJSON(Constants.MD + "/member/delete?accessToken=" + accessToken, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(deleteUser);
    }

    /**
     * 功能描述 :修改识别照片
     *
     * @param accessToken jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @author Mr.LiuYong
     * @date 2019/5/8 16:55
     */
    public static JSONObject updateVerifyImg(String accessToken, JSONObject jsonObject) {
        String updateVerifyImg = null;
        try {
            updateVerifyImg = Util.httpPostWithForm(Constants.MD + "/member/updateVerifyImg?accessToken=" + accessToken, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(updateVerifyImg);
    }

    /**
     * 功能描述 :修改个性头像
     *
     * @param accessToken jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @author Mr.LiuYong
     * @date 2019/5/8 16:55
     */
    public static JSONObject updateShowImg(String accessToken, JSONObject jsonObject) {
        String updateShowImg = null;
        try {
            updateShowImg = Util.httpPostWithForm(Constants.MD + "/member/updateShowImg?accessToken=" + accessToken, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(updateShowImg);
    }

    /**
     * 功能描述 :创建群组
     *
     * @param accessToken jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @author Mr.LiuYong
     * @date 2019/5/8 16:55
     */
    public static JSONObject createGroup(String accessToken, JSONObject jsonObject) {
        String createGroup = null;
        try {
            createGroup = Util.httpPostWithJSON(Constants.MD + "/group/create?accessToken=" + accessToken, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(createGroup);
    }

    /**
     * 功能描述 :修改群组
     *
     * @param accessToken jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @author Mr.LiuYong
     * @date 2019/5/8 16:55
     */
    public static JSONObject updateGroup(String accessToken, JSONObject jsonObject) {
        String updateGroup = null;
        try {
            updateGroup = Util.httpPostWithJSON(Constants.MD + "/group/update?accessToken=" + accessToken, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(updateGroup);
    }

    /**
     * 功能描述 :增加人员与群组关系
     *
     * @param accessToken jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @author Mr.LiuYong
     * @date 2019/5/8 16:55
     */
    public static JSONObject bindGroup(String accessToken, JSONObject jsonObject) {
        String bindGroup = null;
        try {
            bindGroup = Util.httpPostWithJSON(Constants.MD + "/member/bindGroup?accessToken=" + accessToken, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(bindGroup);
    }

    /**
     * 功能描述 :移除人员群组关系
     *
     * @param accessToken jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @author Mr.LiuYong
     * @date 2019/5/8 16:55
     */
    public static JSONObject unbindGroup(String accessToken, JSONObject jsonObject) {
        String unbindGroup = null;
        try {
            unbindGroup = Util.httpPostWithJSON(Constants.MD + "/member/unbindGroup?accessToken=" + accessToken, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(unbindGroup);
    }

    /**
     * 功能描述 :设备与群组绑定
     *
     * @param accessToken jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @author Mr.LiuYong
     * @date 2019/5/8 16:55
     */
    public static JSONObject deviceGroupUnbind(String accessToken, JSONObject jsonObject) {
        String deviceGroupUnbind = null;
        try {
            deviceGroupUnbind = Util.httpPostWithJSON(Constants.MD + "/device/group/binding?accessToken=" + accessToken, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(deviceGroupUnbind);
    }

    /**
     * 功能描述 :设备群组配置
     *
     * @param accessToken jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @author Mr.LiuYong
     * @date 2019/5/8 16:55
     */
    public static JSONObject deviceGroupConfiguration(String accessToken, JSONObject jsonObject) {
        String deviceUnbindGroup = null;
        try {
            deviceUnbindGroup = Util.httpPostWithJSON(Constants.MD + "/device/group/configuration?accessToken=" + accessToken, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(deviceUnbindGroup);
    }


    /**
     * 功能描述 :注册回调
     *
     * @param accessToken jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @author Mr.LiuYong
     * @date 2019/5/8 16:55
     */
    public static JSONObject callbackAddOrgCallback(String accessToken, JSONObject jsonObject) {
        String callbackAddOrgCallback = null;
        try {
            callbackAddOrgCallback = Util.httpPostWithJSON(Constants.MD + "/callback/addOrgCallback?accessToken=" + accessToken, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(callbackAddOrgCallback);
    }

    /**
     * 功能描述 :修改回调
     *
     * @param accessToken jsonObject
     * @return com.alibaba.fastjson.JSONObject
     * @author Mr.LiuYong
     * @date 2019/5/8 16:55
     */
    public static JSONObject callbackUpdateOrgCallback(String accessToken, JSONObject jsonObject) {
        String callbackUpdateOrgCallback = null;
        try {
            callbackUpdateOrgCallback = Util.httpPostWithJSON(Constants.MD + "/callback/updateOrgCallback?accessToken=" + accessToken, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(callbackUpdateOrgCallback);
    }


}
