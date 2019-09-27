package com.hujiang.project.zhgd.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: Provider01
 * @description: 获取当前时间
 * @author: Mr.LiuYong
 * @create: 2019-05-15 16:47
 **/
public class CurrentTime {

    public static String getCurrentTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
