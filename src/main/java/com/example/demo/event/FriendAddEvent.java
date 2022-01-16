package com.example.demo.event;

import cn.hutool.json.JSONObject;
import lombok.Data;

@Data
public class FriendAddEvent {
    //    "content": {
    //        "robot_wxid": "",  // 机器人账号id
    //        "type": 1,  // 添加方式 请参考常量表
    //        "from_wxid": "",  // 请求者wxid
    //        "from_name": "",  // 请求者昵称
    //        "v1": "",
    //        "v2": "",
    //        "json_msg": {
    //            "to_wxid": "wxid_eu05e13ld28822",
    //            "to_name": "譬如朝露",
    //            "msgid": 1111250493,
    //            "from_wxid": "wxid_6ungmd6wtdh521",
    //            "from_nickname": "??[奸笑]??",
    //            "v1": "xxxxx",
    //            "v2": "xxxxx",
    //            "sex": 1,
    //            "from_content": "我是??[奸笑]??",
    //            "headimgurl": "http://wx.qlogo.cn/xxxxx",
    //            "type": 3
    //        },  // 友验证信息JSON(群内添加时，包含群id) (名片推荐添加时，包含推荐人id及昵称) (微信号、手机号搜索等添加时,具体JSON结构请查看日志）
    private String userId;
    private String username;
    private int addType;//添加方式
    private String v1,v2;
    private JSONObject eventMsg;
}
