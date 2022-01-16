package com.example.demo.event;

import lombok.Data;

@Data
public class GroupNameChangeEvent {
    //// HTTP(GET/POST)示例
    //{
    //    "sdkVer": 4,  // SDK版本号
    //    "Event": "EventGroupNameChange", // 事件（易语言模板的子程序名）
    //    "content": {
    //        "robot_wxid": "",  // 机器人账号id
    //        "from_group": "",  // 群号
    //        "new_name": "",  // 新群名
    //        "old_name": "",  // 旧群名
    //        "from_name": "",  // 操作者昵称
    //        "clientid": 0,  // 企业微信可用
    //        "robot_type": 0  // 来源微信类型 0 正常微信 / 1 企业微信
    //    }  // 内容（易语言模板的参数名）
    private String groupId;//群id
    private String newName;
    private String oldName;
    private String Operater;
}
