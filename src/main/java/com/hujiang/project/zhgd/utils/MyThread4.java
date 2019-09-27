package com.hujiang.project.zhgd.utils;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-06-21 11:55
 **/
public class MyThread4 extends Thread{

    @Override
    public void run() {
        super.run();
        try {

            System.out.println( "begin run" );
            Thread.sleep( 5000 );
            System.out.println( "begin end" );
        } catch (InterruptedException e) {
            System.out.println("在沉睡中终止");
            e.printStackTrace();
        }
    }



}
