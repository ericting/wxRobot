package com.example.demo.event;

import cn.hutool.json.JSONObject;
import lombok.Data;

import java.util.Date;

@Data
public class GroupDecreaseNoticeEvent {
    //    "content": {
    //        "robot_wxid": "",  // 机器人账号id
    //        "from_group": "",  // 群号
    //        "from_group_name": "",  // 群名
    //        "to_wxid": "",  // 被操作者wxid
    //        "to_name": "",  // 被操作者昵称
    //        "time": "",  // 操作时间
    //        "clientid": 0,  // 企业微信可用
    //        "robot_type": 0  // 来源微信类型 0 正常微信 / 1 企业微信
    private String groupId;
    private String groupName;
    private String userId;
    private String userName;
    private Date time;
}
