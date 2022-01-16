package com.example.demo.plugin;

import com.example.demo.domain.Bot;
import com.example.demo.domain.BotPlugin;
import com.example.demo.event.*;
import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Component;

import static com.example.demo.config.MessageConfig.*;

//todo: 学习通IM协议

@Component
public class TestPlugin extends BotPlugin {
    @Override
    public int onPrivateMessage(Bot bot, PrivateMessageEvent event) {
        if (event.getMessage().equals("hi")){
            bot.sendPrivateMessage(event.getUserId(),"你好呀～");
            return MESSAGE_TEXT_TYPE;
        }
        if(event.getMessage().equals("测试消息")){
            bot.sendPrivateMessage(event.getUserId(),"hello");
            return MESSAGE_TEXT_TYPE;
        }
        if (event.getMessage().startsWith("我想听")){
            String musicName = event.getMessage().replace("我想听","").trim();
            bot.sendMusicMessage(event.getUserId(),musicName);
        }


        if (event.getMessage().equals("机器人列表")){
            bot.getBotList();
        }
        if (event.getMessage().equals("好友列表")){
            bot.getFriendList(null,true);
        }
        if (event.getMessage().equals("群聊列表")){
            bot.getGroupList(null,true);
        }
        return MESSAGE_IN;
    }

    @Override
    public int onGroupMessage(Bot bot, GroupMessageEvent event) {
        if (event.getMsg().equals("hi")){
            bot.sendGroupMessage(event.getGroupId(),"你好~",event.getUserId(),event.getUsername(),false);
        }

        if (event.getMsg().equals("获取成员列表")){
            bot.getGroupMemberList(event.getGroupId(),true);
        }
        if (event.getMsg().equals("我的微信信息")){
            bot.sendGroupMessage(event.getGroupId(),
                    bot.getGroupMember(event.getGroupId(), event.getUserId()).toString(),
                    event.getUserId(),event.getUsername(),false);
        }
        if (event.getMsg().startsWith("修改群名")){
            bot.modifyGroupName(event.getGroupId(),
                    event.getMsg().replace("修改群名","")
            .trim());
        }
        return MESSAGE_IN;
    }

    @Override
    public int onFriendAddEvent(@NotNull Bot bot, FriendAddEvent event) {
        bot.agreeFriendVerify(event);

        return MESSAGE_IN;
    }

    @Override
    public int onGroupIncreaseNotice(Bot bot, GroupIncreaseNoticeEvent event) {
        bot.sendGroupMessage(event.getGroupId(),"群成员增加","","",false);
        return MESSAGE_IN;
    }

    @Override
    public int onGroupDecreaseNotice(@NotNull Bot bot, GroupDecreaseNoticeEvent event) {
        bot.sendGroupMessage(event.getGroupId(),"群成员减少","","",false);
        return MESSAGE_IN;
    }

    @Override
    public int onGroupNameChange(Bot bot, GroupNameChangeEvent event) {
        return MESSAGE_IN;
    }
}
