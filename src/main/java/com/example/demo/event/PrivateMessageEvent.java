package com.example.demo.event;

import lombok.Data;

@Data
public class PrivateMessageEvent {
    //        "robot_wxid": "",  // 机器人账号id
    //        "type": 1,  // 1/文本消息 3/图片消息 34/语音消息  42/名片消息  43/视频 47/动态表情 48/地理位置  49/分享链接  2001/红包  2002/小程序  2003/群邀请  更多请参考常量表
    //        "from_wxid": "",  // 来源用户ID
    //        "from_name": "",  // 来源用户昵称
    //        "msg": "",  // 消息内容
    //        "clientid": 0,  // 企业微信可用
    //        "robot_type": 0  // 来源微信类型 0 正常微信 / 1 企业微信
    private String userId;
    private String username;
    private String message;
    private int type;
}
