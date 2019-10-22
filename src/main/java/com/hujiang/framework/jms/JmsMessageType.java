package com.hujiang.framework.jms;

public enum JmsMessageType {
    Machine, //代表机器、设备
    Data, //代表数据
    PARAMETERS,//代表参数
    WARNING,//代表预警
    Electrify,//代表通电
    WorkCycle,//代表工作循环
    PunchTheClock,//代表司机打卡

}
