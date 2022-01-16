package com.example.demo.event;

import lombok.Data;

@Data
public class InvitedInGroupEvent {
//        "robot_wxid": "",  // 机器人账号id
//        "from_wxid": "",  // 邀请机器人入群的id
//        "msg":  {
//            "inviter_wxid": "wxid_e6shncy2hlzm32",
//            "inviter_nickname": "测试",
//            "group_headimgurl": "https://u.weixin.qq.com/xxxxx",
//            "group_name": "测试群",
//            "invite_url": "https://support.weixin.qq.com/xxxxx"
//        },  // 内容
//        "robot_type": 0  // 来源微信类型 0 正常微信 / 1 企业微信  后面可能会传递，预留参数
    private String GroupId;
    private String inviterId;
    private String inviterNickname;
    private String groupHeadimgurl;
    private String groupName;
    private String inviterUrl;

}
