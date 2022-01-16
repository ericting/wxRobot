package com.example.demo.event;

import lombok.Data;

import java.util.Date;

@Data
public class QRcodePaymentEvent {
    //"Event": "EventQRcodePayment", // 事件（易语言模板的子程序名）
    //    "content": {
    //        "robot_wxid": "",  // 机器人账号id
    //        "to_wxid": "",  // 收款者wxid
    //        "payer_wxid": "",  // 付款者wxid
    //        "payer_nickname": "",  // 付款者昵称
    //        "payer_pay_id": "",
    //        "received_money_index": "",
    //        "money": "",  // 金额
    //        "total_money": "",
    //        "remark": "",  // 备注
    //        "scene_desc": "",
    //        "scene": "",  // -1 扫码后退出 / 1 已扫码，未付款 / 2 付款完成 / 3 付款完成后的日志 / 4 付款完成后的日志（商家版），这里重点说明一下，如要做收款播报，只需要判断等于2或者等3的时候就可以了
    //        "time": ""
    //    }
    private String UserId;//收款者id
    private String payerUserId;
    private String payerUserName;
    private int payerPayId;//订单号
    private int money;
    private int totalMoney;
    private String remark;//备注
    private String screneDesc;
    private int scene;
    private String time;
}
