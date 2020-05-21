package com.hujiang.project.zhgd.utils;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-05-24 10:07
 **/
public class Constants {
    //惠州两制对接住建局
    public static final String XIETWOSYSTEMS = "http://";

    public static final String Version = "http://szwb.sz.gov.cn:2018/openapi/";
    //塔吊
    public static final String Tower_crane = " http://szwb.sz.gov.cn:2019//CWRService/";
    //扬尘接口
    public static final String DUSTEMISSION = "http://www.osen-cloud.net:11112/aosien/real-time/data";
    //漏电流、温度数据接口
    public static final String TempEelcHistory = "http://123.206.45.243:8080/pdx_sz/temp/getTempEelcHistory.do";
    //漏电流、温度数据接口(改造电箱)
    public static final String TempEelcHistoryRemould = "http://123.206.45.243:8080/pdx/info/getnow.do";

    //门锁数据接口
    public static final String LockDoorHistoryInfo = "http://123.206.45.243:8080/pdx_sz/temp/getLockDoorHistoryInfo.do";
    //百度人脸
    public static final String BD_APP_ID = "15167429";
    public static final String BD_API_KEY = "t4HYulry79qFo6URTVuVUoX8";
    public static final String BD_SECRET_KEY = "U5wc8yv3TMctcYhQuG84MfGIxRPukCEL";

    //魔点
    public static final String MD = "https://oapi.moredian.com";
    //住建厅
    public static final String HJ_FORMALHOST = "http://ticwrapi.thit.com.cn/CWRService/";
    //东莞住建局
    public static final String DG_HOUS="http://157.122.146.230:9103/UploadService";
    //市政总
    public static final String ZHGD_FORMALHOST = "http://113.105.121.93:9090/CWRService/";

    //设备图片上传获取路径
    public static final String SB = "http://szwb.sz.gov.cn:2018/uploadfile-openpai/";

    //中车
    public static final String ZC_FORMALHOST = "http://szwb.sz.gov.cn:2018/CWRService/";
    //星河门禁
    public static final String XH_MJ = "http://enm.chngalaxy.com:9091";
    //厦门一指通地址
    public static final String YIZHITONG = "http://gd.17hr.net:8016";
    //厦门一指通图片地址
    public static final String YIZHITONGIMG = "http://gd.17hr.net:8018";
    //中车智慧工地
    public static final String ZCAPI = "http://szwb.sz.gov.cn:2018/openapi";

    public static final Integer ATTENDANCESCORE = 80;//考勤识别分数
    public static final Integer FACESCORE = 35;//人脸对比分数


    //public static final String ZCAPI = "http://szwb.sz.gov.cn:2018/openapi";
    //定位接口
    public static final String LOCALTION = "http://101.37.34.43:8080/datainterface/data/getinfos.ll";
    public static final String LOCALTIONDATA = "http://home.welleplus.com.cn:58000/Location/data/getinfos.do";
    public static final String LOCALTIONDATAWARNING = "http://home.welleplus.com.cn:58000/Location/data/getwarns.do";
    //对接城安院地址 市管项目(生产环境)
    public static final String CAY_CS = "http://139.159.186.240/misInter/";
    //对接城安院地址 (城安院测试环境地址)
//    public static final String CAY_CS = "http://139.159.251.33/misInter/";

    /**对接城安院使用的token+市管*/
    public static final String TOKEN_CS = "?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5";
    //对接城安院使用的token+区管
    public static final String TOKEN_SCHJ = "?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5";
    //区管项目测试token
    public static final String TOKEN_CS_QG = "?token=EBDECFCC5DE096892050A8E6913168A7A0A41963";

    //城安院(区管项目)生产环境
//    public static final String CAY_QGXM = "http://139.159.197.174/misInter/";
    /**城安院(区管项目)测试环境地址*/
    public static final String CAY_QGXM = "http://yqce.szsti.org/misInter/";

    //人才安居
    public static final String RCAJ="http://218.17.11.171:7010";
    //萤石组件
    public static final String OPEN_YS="https://open.ys7.com/api/component/saas/";
    //萤石开放接口
    public static final String OPEN_YS_LAPP="https://open.ys7.com/api/";
    //中山大学甲方
    public static final String ZSJF="http://120.79.18.214/sysu-web/";
    //海康人脸机秘钥ID
    public static final Integer ACCESSTOKEN_ID=80;
    //江西省吉安市实名制
    public static final String JIANSHI_SHIMINGZHI="http://220.177.172.55:8004/3HDataSmart";
    //乐橙云接口
    public static final String OPEN_LC="https://openapi.lechange.cn:443/openapi/";
    //海康云眸
    public static final String YUNMOU="https://api2.hik-cloud.com";
    //云眸密钥ID
    public static final Integer YUNMOUTOKEN_ID=121;

    /**福建两制对接地址*/
    public static String HUJIAN_TWO_SYSTEMS = "http://47.106.167.195:9096/open.api/v1";

    /**对接升机坑  水位  沉降  测点*/
    public static String LIFTING_PIT = "https://api.zhiwucloud.com/api/v1";
}