package com.example.demo.event;

import lombok.Data;

@Data
public class LoginEvent {
    //    "content": {
    //        "type": 1,  // 0 登录成功 / 1 即将离线
    //        "Wxid": "",  // 登录/离线 的Wxid
    //        "robot_type": 0  // 来源微信类型 0 正常微信 / 1 企业微信
    //    }  // 内容（易语言模板的参数名）
    //}
    private int type;
    private String userId;
}
