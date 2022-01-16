package com.example.demo.event;

import lombok.Data;

@Data
public class DeviceCallbackEvent {
    //    "sdkVer": 4,  // SDK版本号
    //    "Event": "EventDeviceCallback", // 事件（易语言模板的子程序名）
    //    "content": {
    //        "robot_wxid": "",  // 机器人账号id
    //        "type": 1,  // 消息类型
    //        "msg": "",  // 消息内容
    //        "to_wxid": "",  // 接收用户ID
    //        "to_name": "",  // 接收用户昵称
    //        "clientid": 0,  // 企业微信可用
    //        "robot_type": 0  // 来源微信类型 0 正常微信 / 1 企业微信
    private int type;
    private String msg;
    private String userId;
    private String userName;
}
