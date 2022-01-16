package com.example.demo.domain;


import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.*;
import com.example.demo.event.FriendAddEvent;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class Bot {
    private String robotId;
    private String domain = "http://39.106.93.222:64801/";
    private String token="Tr4ZBJCwTDYNGVHGrt";
    private  Map<String,Object> params = null;

    public Bot(String robotId){
        this.robotId = robotId;
    }

    /**
     * 发送私聊消息
     * */
    public void sendPrivateMessage(String userId, String message) {
        //null]SendTextMsg  // 发送文字消息
        //robot_wxid (string)  // 机器人ID
        //to_wxid (string)  // 对方的ID（支持好友/群ID/公众号ID）
        //msg (string)  // 消息内容
        params = new HashMap<>();
        params.put("token",token);
        params.put("api","SendTextMsg");
        params.put("to_wxid",userId);
        params.put("robot_wxid",robotId);
        //用StringEscapeUtils解决了编码问题
        params.put("msg",UnicodeUtil.toUnicode(message));
        JSONObject jsonObject = JSONUtil.parseObj(params);
        HttpUtil.post(domain,StringEscapeUtils.unescapeJson(jsonObject.toString()));
    }
    /**
     * 发送群聊消息
     * */
    public void sendGroupMessage(String groupId, String message, String userId, String username, boolean isAt){
        //[null]SendGroupMsgAndAt  // 发送群消息并艾特成员
        //robot_wxid (string)  // 机器人ID
        //group_wxid (string)  // 群ID
        //member_wxid (string)  // 要艾特的群成员ID，艾特多人用英文逗号“,”分开
        //member_name (string)  // 要艾特的群成员昵称，可空 会自动读取
        //msg (string)  // 消息内容
        params = new HashMap<>();

        if (isAt){
            params.put("token",token);
            params.put("api","SendGroupMsgAndAt");
            params.put("robot_wxid",robotId);
            params.put("group_wxid",groupId);
            params.put("member_wxid",userId);
            params.put("member_name",username);
            params.put("msg",UnicodeUtil.toUnicode(message));
            JSONObject obj = JSONUtil.parseObj(params);
            HttpUtil.post(domain,StringEscapeUtils.unescapeJson(obj.toString()));
        }else {
            sendPrivateMessage(groupId,message);
        }
    }

    /**
     * 发送图片消息
     * */
    public void sendImageMessage(String toWxId, String imgPath){
        //[null]SendImageMsg  // 发送图片消息
        //robot_wxid (string)  // 机器人ID
        //to_wxid (string)  // 对方的ID（支持好友/群ID/公众号ID）
        //path (string)  // 本地图片文件的绝对路径 或 网络图片url 或 图片base64编码
        params = new HashMap<>();
        params.put("token",token);
        params.put("api","SendImageMsg");
        params.put("to_wxid",toWxId);
        params.put("robot_wxid",robotId);
        params.put("path",imgPath);
        JSONObject obj = JSONUtil.parseObj(params);
        HttpUtil.post(domain,StringEscapeUtils.unescapeJson(obj.toString()));
    }

    /**
     * 发送视频消息
     * */
    public void sendVideoMessage(String toWxId, String videoPath){
        //[null]SendVideoMsg  // 发送视频消息
        //robot_wxid (string)  // 机器人ID
        //to_wxid (string)  // 对方的ID（支持好友/群ID/公众号ID）
        //path (string)  // 机器人本地视频文件（xxxxx.mp4）的绝对路径
        params = new HashMap<>();
        params.put("token",token);
        params.put("api","SendVideoMsg");
        params.put("to_wxid",toWxId);
        params.put("robot_wxid",robotId);
        params.put("path",videoPath);
        JSONObject obj = JSONUtil.parseObj(params);
        HttpUtil.post(domain,StringEscapeUtils.unescapeJson(obj.toString()));
    }

    /**
     * 发送文件消息
     * */
    public void sendFileMessage(String toWxId, String filePath){
        //[null]SendFileMsg  // 发送文件消息
        //robot_wxid (string)  // 机器人ID
        //to_wxid (string)  // 对方的ID（支持好友/群ID/公众号ID）
        //path (string)  // 机器人本地文件的绝对路径
        params = new HashMap<>();
        params.put("token",token);
        params.put("api","SendFileMsg");
        params.put("to_wxid",toWxId);
        params.put("robot_wxid",robotId);
        params.put("path",filePath);
        JSONObject obj = JSONUtil.parseObj(params);
        HttpUtil.post(domain,StringEscapeUtils.unescapeJson(obj.toString()));
    }

    /**
     * 发送动态表情消息
     * */
    public void sendEmojiMessage(String toWxId, String emojiPath){
        //[null]SendEmojiMsg  // 发送动态表情消息
        //robot_wxid (string)  // 机器人ID
        //to_wxid (string)  // 对方的ID（支持好友/群ID/公众号ID）
        //path (string)  // 机器人本地动态表情文件（xxxxx.gif）的绝对路径
        params = new HashMap<>();
        params.put("token",token);
        params.put("api","SendEmojiMsg");
        params.put("to_wxid",toWxId);
        params.put("robot_wxid",robotId);
        params.put("path",emojiPath);
        JSONObject obj = JSONUtil.parseObj(params);
        HttpUtil.post(domain,StringEscapeUtils.unescapeJson(obj.toString()));
    }

    /**
     * 分享链接
     * */
    public void sendLinkMessage(String toWxId, String title, String text, String imageUrl, String url){
        //[null]SendShareLinkMsg  // 发送普通分享链接
        //robot_wxid (string)  // 机器人ID
        //to_wxid (string)  // 对方的ID（支持好友/群ID/公众号ID）
        //title (string)  // 链接标题
        //desc (string)  // 链接内容
        //image_url (string)  // 图片地址
        //url (string)  // 跳转地址
        params = new HashMap<>();
        params.put("token",token);
        params.put("api","SendShareLinkMsg");
        params.put("to_wxid",toWxId);
        params.put("robot_wxid",robotId);
        params.put("title",title);
        params.put("desc",text);
        params.put("image_url",imageUrl);
        //
        params.put("url",url);
        JSONObject obj = JSONUtil.parseObj(params);
        HttpUtil.post(domain,StringEscapeUtils.unescapeJson(obj.toString()));
    }

    /**
     * 发送一条可播放的歌曲链接
     * todo:
     * */
    public void sendMusicMessage(String toWxId, String musicName){
        //[null]SendMusicLinkMsg  // 发送一条可播放的歌曲链接
        //robot_wxid (string)  // 机器人ID
        //to_wxid (string)  // 对方的ID（支持好友/群ID/公众号ID）
        //title (string)  // 标题
        //desc (string)  // 内容
        //url (string)  // 链接地址
        //dataurl (string)  // mp3地址
        //thumburl (string)  // http图片地址
        params = new HashMap<>();
        params.put("token",token);
        params.put("api","SendMusicLinkMsg");
        params.put("to_wxid",toWxId);
        params.put("robot_wxid",robotId);
        try {
            params.put("msg", URLEncoder.encode(musicName,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        JSONObject obj = JSONUtil.parseObj(params);
        HttpUtil.post(domain,StringEscapeUtils.unescapeJson(obj.toString()));
    }

    /**
     * 获取登录账号信息
     * */
    public JSONObject getRobotInfo(){
        //[json]GetRobotInfo  // 获取登录账号信息（该命令供免费版调用）
        //// 成功 返回json示例
        //{
        //    "Code": 0,
        //    "Result": "OK",
        //    "ReturnJson": {
        //        "account_wxid": "wxid_eu05e13ld28822", // 微信ID
        //        "wxid": "wxid_eu05e13ld28822", // 微信ID
        //        "wx_num": "xyo7789", // 个人微信号
        //        "nickname": "譬如朝露", // 昵称
        //        "headimgurl": "http://wx.qlogo.cn/xxxxxx", // 头像
        //        "country": "", // 国家
        //        "province": "", // 省份
        //        "city": "", // 城市
        //        "sex": 0, // 性别，0 无 / 1 男 / 2 女
        //        "scene": 0, // 添加方式（估计没用）
        //        "signature": "", // 个签
        //        "backgroundimgurl": "", // 背景地址
        //        "wx_wind_handle": 2820848, // 微信窗口句柄ID
        //        "wx_pid": 5444 // 微信进程ID
        //    }
        params = new HashMap<>();
        params.put("token",token);
        params.put("api","GetRobotInfo");
        params.put("robot_wxid",robotId);
        JSONObject obj = JSONUtil.parseObj(params);
        JSONObject info = JSONUtil.parseObj(HttpUtil.post(domain, StringEscapeUtils.unescapeJson(obj.toString())));
        return info.getJSONObject("ReturnJson");
    }


    /**
     * 获取登录账号列表
     * */
    public JSONArray getBotList(){
        params = new HashMap<>();
        JSONObject obj = JSONUtil.parseObj(params);
        obj = JSONUtil.parseObj(HttpUtil.post(domain, obj.toString()));
        JSONArray bots = null;
        try {
            bots = JSONUtil.parseArray(URLDecoder.decode(obj.getStr("data"), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bots;
    }

    /**
     * 获取好友列表
     * @param robotId 机器人账户id 不指定则取所有机器人好友列表<br>
     * @param isRefresh 是否刷新缓存
     * */
    public JSONArray getFriendList(String robotId, boolean isRefresh){
        //[json]GetFriendlist  // 获取好友列表
        //robot_wxid (string)  // 机器人ID
        //is_refresh (int)  // 1为重刷列表再获取，0为取缓存，默认为0
        //// 成功 返回json示例
        //{
        //    "Code": 0,
        //    "Result": "OK",
        //    "ReturnJson": [{
        //        "wxid": "", // 微信ID
        //        "wx_num": "", // 微信账号
        //        "nickname": "", // 昵称
        //        "note": "" // 备注
        //    }]
        params = new HashMap<>();

        params.put("token",token);
        params.put("api","GetFriendlist");
        params.put("robot_wxid",robotId==null ? "" : robotId);
        params.put("is_refresh",isRefresh?0:1);
        JSONObject obj = JSONUtil.parseObj(params);
        JSONArray json = JSONUtil.parseObj(HttpUtil.post(domain, StringEscapeUtils.unescapeJson(obj.toString()))).getJSONArray("ReturnJson");
        return json;
    }

    /**
     * 获取群聊列表
     * @param robotId 机器人账户id 不指定则取所有机器人群聊列表<br>
     * @param isRefresh 是否刷新缓存
     * */
    public JSONArray getGroupList(String robotId, boolean isRefresh){
        //[json]GetGrouplist  // 获取群列表
        //robot_wxid (string)  // 机器人ID
        //is_refresh (int)  // 1为重刷列表再获取，0为取缓存，默认为0
        //// 成功 返回json示例
        //{
        //    "Code": 0,
        //    "Result": "OK",
        //    "ReturnJson": [{
        //        "wxid": "", // 微信ID
        //        "nickname": "" // 昵称
        //    }]
        params = new HashMap<>();
        params.put("token",token);
        params.put("api","GetGrouplist");
        params.put("robot_wxid",robotId==null?"":robotId);
        params.put("is_refresh",isRefresh?0:1);
        JSONObject obj = JSONUtil.parseObj(params);
        JSONArray json = JSONUtil.parseObj(HttpUtil.post(domain, StringEscapeUtils.unescapeJson(obj.toString()))).getJSONArray("ReturnJson");
        return json;
    }

    /**
     * 获取群成员列表
     * @param groupId 群聊id<br>
     * @param isRefresh 是否刷新缓存
     * */
    public JSONArray getGroupMemberList(String groupId, boolean isRefresh){
        //[json]GetGroupMember  // 获取群成员列表
        //robot_wxid (string)  // 机器人ID
        //group_wxid (string)  // 群ID
        //is_refresh (int)  // 1为重刷列表再获取，0为取缓存，默认为0
        //// 成功 返回json示例
        //{
        //    "Code": 0,
        //    "Result": "OK",
        //    "ReturnJson": {
        //        "group_wxid": "", // 群ID
        //        "group_name": "", // 群昵称
        //        "count": 2, // 成员数量
        //        "owner_wxid": "", // 群主微信ID
        //        "owner_nickname": "", // 群主微信昵称
        //        "member_list": [{
        //            "wxid": "", // 微信ID
        //            "wx_num": "", // 微信号
        //            "group_nickname": "", // 群内昵称
        //            "nickname": "" // 微信昵称
        //        }, {
        //            "wxid": "", // 微信ID
        //            "wx_num": "", // 微信号
        //            "group_nickname": "", // 群内昵称
        //            "nickname": "" // 微信昵称
        //        }]
        //    }
        params = new HashMap<>();
        params.put("token",token);
        params.put("api","GetGroupMember");
        params.put("robot_wxid",robotId);
        params.put("group_wxid",groupId);
        params.put("is_refresh",isRefresh?0:1);
        JSONObject obj = JSONUtil.parseObj(params);
        JSONArray json = JSONUtil.parseObj(HttpUtil.post(domain,
                StringEscapeUtils.unescapeJson(obj.toString()))
                ).getJSONObject("ReturnJson").getJSONArray("member_list");
        return json;
    }

    /**
     * 取群成员信息
     * */
    public String getGroupMember(String groupId,String userId){
        //[string]GetGroupcardByWxid  // 获取某个群成员详细
        //robot_wxid (string)  // 机器人ID
        //group_wxid (string)  // 群ID
        //to_wxid (string)  // 群成员ID
        params = new HashMap<>();
        params.put("token",token);
        params.put("api","GetGroupcardByWxid");
        params.put("robot_wxid",robotId);
        params.put("group_wxid",groupId);
        params.put("to_wxid",userId);
        JSONObject obj = JSONUtil.parseObj(params);;
        return HttpUtil.post(domain,StringEscapeUtils.unescapeJson(obj.toString()));
    }

    /**
     * 修改好友备注
     * */
    public void modifyFriendNote(String userId,String note){
        ///[null]ModifyFriendNote  // 修改好友备注
        //robot_wxid (string)  // 机器人ID
        //to_wxid (string)  // 好友ID
        //note (string)  // 新备注
        params = new HashMap<>();
        params.put("token",token);
        params.put("api","ModifyFriendNote");
        params.put("robot_wxid",robotId);
        params.put("to_wxid",userId);
        params.put("note",note);
        JSONObject obj = JSONUtil.parseObj(params);
        HttpUtil.post(domain,StringEscapeUtils.unescapeJson(obj.toString()));
    }

    /**
     * 删除好友
     * */
    public void deleteFriend(String userId){
        //[null]DeleteFriend  // 删除好友
        //robot_wxid (string)  // 机器人ID
        //to_wxid (string)  // 好友ID
        params = new HashMap<>();
        params.put("token",token);
        params.put("api","DeleteFriend");
        params.put("robot_wxid",robotId);
        params.put("to_wxid",userId);
        JSONObject obj = JSONUtil.parseObj(params);
        HttpUtil.post(domain, StringEscapeUtils.unescapeJson(obj.toString()));
    }

    /**
     * 移出群成员
     * */
    public void  removeGroupMember(String groupId,String userId){
        //[null]RemoveGroupMember  // 踢出一位群成员
        //robot_wxid (string)  // 机器人ID
        //group_wxid (string)  // 群ID
        //member_wxid (string)  // 要踢出的群成员ID
        params = new HashMap<>();
        params.put("token",token);
        params.put("api","RemoveGroupMember");
        params.put("robot_wxid",robotId);
        params.put("group_wxid",groupId);
        params.put("member_wxid",userId);
        JSONObject obj = JSONUtil.parseObj(params);
        HttpUtil.post(domain, StringEscapeUtils.unescapeJson(obj.toString()));
    }

    /**
     * 修改群名称
     * */
    public void  modifyGroupName(String groupId,String groupName){
        //[null]ModifyGroupName  // 修改群名称
        //robot_wxid (string)  // 机器人ID
        //group_wxid (string)  // 群ID
        //group_name (string)  // 新的群名称
        params = new HashMap<>();
        params.put("token",token);
        params.put("api","ModifyGroupName");
        params.put("robot_wxid",robotId);
        params.put("group_wxid",groupId);
        params.put("group_name",groupName);
        JSONObject obj = JSONUtil.parseObj(params);
        HttpUtil.post(domain,  StringEscapeUtils.unescapeJson(obj.toString()));
    }

    /**
     * 修改群公告
     * */
    public void modifyGroupNotice(String groupId, String notice){
        //[null]ModifyGroupNotice  // 修改群公告
        //robot_wxid (string)  // 机器人ID
        //group_wxid (string)  // 群ID
        //Notice (string)  // 新的群公告
        params = new HashMap<>();
        params.put("token",token);
        params.put("api","ModifyGroupNotice");
        params.put("robot_wxid",robotId);
        params.put("group_wxid",groupId);
        params.put("Notice",UnicodeUtil.toUnicode(notice));
        JSONObject obj = JSONUtil.parseObj(params);
        HttpUtil.post(domain,  StringEscapeUtils.unescapeJson(obj.toString()));
    }

    /**
     * 退出群聊
     * */
    public void quitGroup(String groupId){
        //[null]QuitGroup  // 退出群聊
        //robot_wxid (string)  // 机器人ID
        //group_wxid (string)  // 群ID
        params = new HashMap<>();
        params.put("token",token);
        params.put("api","QuitGroup");
        params.put("robot_wxid",robotId);
        params.put("group_wxid",groupId);
        JSONObject obj = JSONUtil.parseObj(params);
        HttpUtil.post(domain, StringEscapeUtils.unescapeJson(obj.toString()));
    }

    /**
     * 邀请好友入群_发链接
     * */
    public void inviteInGroupByLink(String groupId, String userId){
        //[int]InviteInGroupByLink  // 邀请好友入群_发链接 0 邀请成功 -1 邀请失败 -2 已经存在 -3 对方账号异常 -4 需要群主验证 -5 需要手机确认
        //robot_wxid (string)  // 机器人ID
        //group_wxid (string)  // 群ID
        //friend_wxid (string)  // 好友ID
        params = new HashMap<>();
        params.put("token",token);
        params.put("api","InviteInGroupByLink");
        params.put("robot_wxid",robotId);
        params.put("friend_wxid",userId);
        params.put("group_wxid",groupId);
        JSONObject obj = JSONUtil.parseObj(params);
        HttpUtil.post(domain, StringEscapeUtils.unescapeJson(obj.toString()));
    }
    /**
     * 邀请好友入群_直接拉
     */
    public void InviteInGroup(String groupId, String userId){
        //[int]InviteInGroup  // 邀请好友入群_直接拉 0 邀请成功 -1 邀请失败 -2 已经存在 -3 对方账号异常 -4 需要群主验证 -5 需要手机确认
        //robot_wxid (string)  // 机器人ID
        //group_wxid (string)  // 群ID
        //friend_wxid (string)  // 好友ID
        params = new HashMap<>();
        params.put("token",token);
        params.put("api","InviteInGroup");
        params.put("robot_wxid",robotId);
        params.put("friend_wxid",userId);
        params.put("group_wxid",groupId);
        JSONObject obj = JSONUtil.parseObj(params);
        HttpUtil.post(domain, StringEscapeUtils.unescapeJson(obj.toString()));
    }
    /**
     * 同意加好友请求
     * */
    public void agreeFriendVerify(FriendAddEvent event){
        //[null]AgreeFriendVerify  // 同意好友请求
        //robot_wxid (string)  // 机器人ID
        //v1 (string)  // 收到好友验证消息中（json）的v1属性
        //v2 (string)  // 收到好友验证消息中（json）的v2属性
        //type (int)  // 收到好友验证消息中（json）的type属性
        params = new HashMap<>();
        params.put("token",token);
        params.put("api","AgreeFriendVerify");
        params.put("robot_wxid",robotId);
        params.put("v1",event.getV1());
        params.put("v2",event.getV2());
        params.put("type",event.getAddType());
        JSONObject obj = JSONUtil.parseObj(params);
        HttpUtil.post(domain, StringEscapeUtils.unescapeJson(obj.toString()));
    }
}
