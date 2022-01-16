package com.example.demo.event;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import lombok.Data;

@Data
public class GroupIncreaseNoticeEvent {
    //    "content": {
    //        "robot_wxid": "",  // 机器人账号id
    //        "from_group": "",  // 群号
    //        "from_group_name": "",  // 群名
    //        "guest": [{
    //            "wxid": "wxid_e6shncy2hlzm32",
    //            "nickname": "测试"
    //        }],  // 新人
    //        "inviter": {
    //            "wxid": "wxid_6ungmd6wtdh521",
    //            "nickname": "??[奸笑]??"
    //        },  // 邀请者
    //        "clientid": 0,  // 企业微信可用
    //        "robot_type": 0  // 来源微信类型 0 正常微信 / 1 企业微信
    //    }  // 内容（易语言
    private String groupId;
    private String groupName;
    private JSONArray guest;
    private JSONObject inviter;
}
