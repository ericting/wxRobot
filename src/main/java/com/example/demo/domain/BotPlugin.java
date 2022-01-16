package com.example.demo.domain;
import org.springframework.lang.Nullable;
import com.example.demo.event.*;
import com.sun.istack.internal.NotNull;

import static com.example.demo.config.MessageConfig.MESSAGE_IN;

public class BotPlugin {

    /**
     * 收到私聊消息
     * */
    public int onPrivateMessage(@NotNull Bot bot, PrivateMessageEvent event){
        return MESSAGE_IN;
    }

    /**
     * 收到群聊消息
     * */
    public int onGroupMessage(@NotNull Bot bot, GroupMessageEvent event){
        return MESSAGE_IN;
    }

    /**
     * 收到添加好友请
     * */
    public int onFriendAddEvent(@NotNull Bot bot, FriendAddEvent event){
        return MESSAGE_IN;
    }

    /**
     * 群成员增加
     * */
    public int onGroupIncreaseNotice(@NotNull Bot bot, GroupIncreaseNoticeEvent event){
        return MESSAGE_IN;
    }

    /**
     * 群成员减少
     * */
    public int onGroupDecreaseNotice(@NotNull Bot bot, GroupDecreaseNoticeEvent event){
        return MESSAGE_IN;
    }

    /**
     * 新的账号登录成功/下线
     */
    public int onLogin(@NotNull Bot bot, LoginEvent event){
        return MESSAGE_IN;
    }

    /**
     * 设备回调事件
     */
    public int onDeviceCallback(@NotNull Bot bot, DeviceCallbackEvent event){
        return MESSAGE_IN;
    }

    /**
     * 被邀请入群事件
     * 未完善
     */
    public int onInvitedInGroup(@NotNull Bot bot, InvitedInGroupEvent event){
        return MESSAGE_IN;
    }

    /**
     * 面对面收款事件
     */
    public int QRcodePaymentEvent(@NotNull Bot bot, QRcodePaymentEvent event){
        return MESSAGE_IN;
    }

    /**
     * 群名称变动事件
     */
    public int onGroupNameChange(@NotNull Bot bot, GroupNameChangeEvent event){
        return MESSAGE_IN;
    }
}
