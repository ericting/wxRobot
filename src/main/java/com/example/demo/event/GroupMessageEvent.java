package com.example.demo.event;

import cn.hutool.json.JSONObject;
import lombok.Data;

@Data
public class GroupMessageEvent {
    //        "robot_wxid": "",  // 机器人账号id
    //        "type": 1,  // 1/文本消息 3/图片消息 34/语音消息  42/名片消息  43/视频 47/动态表情 48/地理位置  49/分享链接  2001/红包  2002/小程序  2003/群邀请 更多请参考常量表
    //        "from_group": "",  // 来源群号
    //        "from_group_name": "",  // 来源群名称
    //        "from_wxid": "",  // 具体发消息的群成员id
    //        "from_name": "",  // 具体发消息的群成员昵称
    //        "msg": "",  // 消息内容
    //        "msg_source": {
    //            "atuserlist": [{
    //                "wxid": "wxid_6ungmd6wtdh521",
    //                "nickname": "@测试",
    //                "position_from": 1,
    //                "position_to": 7
    //            }]
    //        },  // 附带JSON属性（群消息有艾特人员时，返回被艾特信息）
    //        "clientid": 0,  // 企业微信可用
    //        "robot_type": 0  // 来源微信类型 0 正常微信 / 1 企业微信
    private String userId;
    private String username;
    private String groupId;
    private String groupName;
    private String msg;
    private JSONObject msg_source;
    private int type;
}
