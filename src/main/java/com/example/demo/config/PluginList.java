package com.example.demo.config;


import cn.hutool.json.JSONUtil;
import com.example.demo.domain.ApiEvent;
import com.example.demo.domain.Bot;
import com.example.demo.event.*;
import com.example.demo.util.SpringUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static com.example.demo.config.MessageConfig.MESSAGE_SHARELINK_TYPE;

@Component
@ConfigurationProperties(prefix = "plugin")
public class PluginList {
    private List<Class<Object>> list;

    public void setList(List<Class<Object>> list) {
        this.list = list;
    }

    public List<Class<Object>> getList() {
        return list;
    }

    public void handle(ApiEvent event) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (event != null){
            switch (event.getEvent()){
                case "EventPrivateChat": //私聊事件
                    onPrivateMessage(event);
                    break;
                case "EventGroupChat": // 群聊事件
                    onGroupMessageEvent(event);
                    break;
                case "EventFrieneVerify": //收到好友请求事件
                    onFriendAddEvent(event);
                    break;
                case "EventGroupMemberAdd"://群人数增加
                    onGroupIncreaseNotice(event);
                    break;
                case "EventGroupMemberDecrease"://群人数减少
                    onGroupDecreaseNotice(event);
                    break;
                case "Login"://新的账号登录成功/下线
                    onLogin(event);
                    break;
                case "EventDeviceCallback"://设备回调事件
                    onDeviceCallback(event);
                    break;
                case "EventInvitedInGroup"://被邀请入群事件
                    onInvitedInGroup(event);
                    break;
                case "EventQRcodePayment"://面对面收款事件
                    onQRcodePayment(event);
                    break;
                case "EventGroupNameChange"://群名称变动事件
                    onGroupNameChange(event);
                    break;
            }
        }
    }

    /**
     * 私聊事件
     * */
    public PrivateMessageEvent onPrivateMessage(ApiEvent apiEvent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        PrivateMessageEvent event = new PrivateMessageEvent();
        //json解码
        event.setType(Integer.parseInt(JSONUtil.parseObj(apiEvent.getContext()).getStr("type")));
        //todo:解决xml代码的问题
//        if (event.getType()==MESSAGE_SHARELINK_TYPE){
//            event.setMessage(JSONUtil.parseObj(apiEvent.getContext()).getStr());
//        }else {
            event.setMessage(JSONUtil.parseObj(apiEvent.getContext()).getStr("msg"));

        event.setUserId(JSONUtil.parseObj(apiEvent.getContext()).getStr("from_wxid"));
        event.setUsername(JSONUtil.parseObj(apiEvent.getContext()).getStr("from_name"));
        Bot bot = new Bot(apiEvent.getRobot_wxid());
        for (Class o : list) {
            Object bean = SpringUtil.getBean(o);
            Method method = o.getMethod("onPrivateMessage", Bot.class, PrivateMessageEvent.class);
            int invoke = (int) method.invoke(bean, bot,event);
            if (invoke != 0){
                break;
            }
        }
        return event;
    }

    /**
     * 群聊事件
     * */
    public GroupMessageEvent onGroupMessageEvent(ApiEvent apiEvent) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        GroupMessageEvent event = new GroupMessageEvent();
        event.setType(Integer.parseInt(JSONUtil.parseObj(apiEvent.getContext()).getStr("type")));
        event.setMsg(JSONUtil.parseObj(apiEvent.getContext()).getStr("msg"));
        //msg_source
        event.setUserId(JSONUtil.parseObj(apiEvent.getContext()).getStr("from_wxid"));
        event.setUsername(JSONUtil.parseObj(apiEvent.getContext()).getStr("from_name"));
        event.setGroupId(JSONUtil.parseObj(apiEvent.getContext()).getStr("from_group"));
        event.setGroupName(JSONUtil.parseObj(apiEvent.getContext()).getStr("from_group_name"));
        Bot bot = new Bot(apiEvent.getRobot_wxid());
        for (Class o : list) {
            Object bean = SpringUtil.getBean(o);
            Method method = o.getMethod("onGroupMessage", Bot.class, GroupMessageEvent.class);
            int invoke = (int) method.invoke(bean, bot, event);
            if (invoke != 0){
                break;
            }
        }
        return event;
    }

    /**
     * 收到好友请求事件
     * */
    public FriendAddEvent onFriendAddEvent(ApiEvent apiEvent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        FriendAddEvent event = new FriendAddEvent();
        event.setAddType(Integer.parseInt(JSONUtil.parseObj(apiEvent.getContext()).getStr("type")));
        event.setUserId(JSONUtil.parseObj(apiEvent.getContext()).getStr("from_wxid"));
        event.setUsername(JSONUtil.parseObj(apiEvent.getContext()).getStr("from_name"));
        event.setV1(JSONUtil.parseObj(apiEvent.getContext()).getStr("v1"));
        event.setV2(JSONUtil.parseObj(apiEvent.getContext()).getStr("v2"));
        event.setEventMsg(JSONUtil.parseObj(apiEvent.getContext().getJSONObject("json_msg")));
        Bot bot = new Bot(apiEvent.getRobot_wxid());
        for (Class o : list) {
            Object bean = SpringUtil.getBean(o);
            Method method = o.getMethod("onFriendAddEvent", Bot.class, FriendAddEvent.class);
            int invoke = (int) method.invoke(bean, bot, event);
            if (invoke != 0){
                break;
            }
        }
        return event;
    }


    /**
     * 群成增加
     * */
    public GroupIncreaseNoticeEvent onGroupIncreaseNotice(ApiEvent apiEvent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        GroupIncreaseNoticeEvent event = new GroupIncreaseNoticeEvent();
        event.setGroupId(JSONUtil.parseObj(apiEvent.getContext()).getStr("from_group"));
        event.setGroupName(JSONUtil.parseObj(apiEvent.getContext()).getStr("from_group_name"));
        event.setGuest(JSONUtil.parseArray(apiEvent.getContext().getJSONArray("guest")));
        event.setInviter(JSONUtil.parseObj(apiEvent.getContext().getJSONObject("inviter")));
        Bot bot = new Bot(apiEvent.getRobot_wxid());
        for (Class o : list) {
            Object bean = SpringUtil.getBean(o);
            Method method = o.getMethod("onGroupIncreaseNotice", Bot.class, GroupIncreaseNoticeEvent.class);
            int invoke = (int) method.invoke(bean, bot, event);
            if (invoke != 0){
                break;
            }
        }
        return event;
    }

    /**
     * 群成员减少
     * */
    public GroupDecreaseNoticeEvent onGroupDecreaseNotice(ApiEvent apiEvent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        GroupDecreaseNoticeEvent event = new GroupDecreaseNoticeEvent();
        event.setGroupId(JSONUtil.parseObj(apiEvent.getContext()).getStr("from_group"));
        event.setGroupName(JSONUtil.parseObj(apiEvent.getContext()).getStr("from_group_name"));
        event.setUserId(JSONUtil.parseObj(apiEvent.getContext()).getStr("to_wxid"));
        event.setUserName(JSONUtil.parseObj(apiEvent.getContext()).getStr("to_name"));
        //time
        Bot bot = new Bot(apiEvent.getRobot_wxid());
        for (Class o : list) {
            Object bean = SpringUtil.getBean(o);
            Method method = o.getMethod("onGroupDecreaseNotice", Bot.class, GroupDecreaseNoticeEvent.class);
            int invoke = (int) method.invoke(bean, bot, event);
            if (invoke != 0){
                break;
            }
        }
        return event;
    }

    /**
     * 新的账号登录成功/下线
     */
    public LoginEvent onLogin(ApiEvent apiEvent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        LoginEvent event = new LoginEvent();
        //json解码
        event.setUserId(JSONUtil.parseObj(apiEvent.getContext()).getStr("Wxid"));
        event.setType(Integer.parseInt(JSONUtil.parseObj(apiEvent.getContext()).getStr("type")));
        Bot bot = new Bot(apiEvent.getRobot_wxid());
        for (Class o : list) {
            Object bean = SpringUtil.getBean(o);
            Method method = o.getMethod("onLogin", Bot.class, LoginEvent.class);
            int invoke = (int) method.invoke(bean, bot,event);
            if (invoke != 0){
                break;
            }
        }
        return event;
    }
    /**
     * 设备回调事件
     */
    public DeviceCallbackEvent onDeviceCallback(ApiEvent apiEvent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        DeviceCallbackEvent event = new DeviceCallbackEvent();
        //json解码
        event.setUserId(JSONUtil.parseObj(apiEvent.getContext()).getStr("to_wxid"));
        event.setUserName(JSONUtil.parseObj(apiEvent.getContext()).getStr("to_name"));
        event.setMsg(JSONUtil.parseObj(apiEvent.getContext()).getStr("msg"));
        event.setType(Integer.parseInt(JSONUtil.parseObj(apiEvent.getContext()).getStr("type")));
        Bot bot = new Bot(apiEvent.getRobot_wxid());
        for (Class o : list) {
            Object bean = SpringUtil.getBean(o);
            Method method = o.getMethod("onDeviceCallback", Bot.class, DeviceCallbackEvent.class);
            int invoke = (int) method.invoke(bean, bot,event);
            if (invoke != 0){
                break;
            }
        }
        return event;
    }

    /**
     * 被邀请入群事件
     * 插件未完善
     */
    public InvitedInGroupEvent onInvitedInGroup(ApiEvent apiEvent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        InvitedInGroupEvent event = new InvitedInGroupEvent();
        //json解码
        event.setGroupId(JSONUtil.parseObj(apiEvent.getContext()).getStr("from_wxid"));
        event.setInviterId(JSONUtil.parseObj(apiEvent.getContext().getJSONObject("msg")).getStr("inviter_wxid"));
        event.setInviterNickname(JSONUtil.parseObj(apiEvent.getContext().getJSONObject("msg")).getStr("inviter_nickname"));
        event.setGroupHeadimgurl(JSONUtil.parseObj(apiEvent.getContext().getJSONObject("msg")).getStr("group_headimgurl"));
        event.setGroupName(JSONUtil.parseObj(apiEvent.getContext().getJSONObject("msg")).getStr("group_name"));
        event.setInviterUrl(JSONUtil.parseObj(apiEvent.getContext().getJSONObject("msg")).getStr("invite_url"));
        Bot bot = new Bot(apiEvent.getRobot_wxid());
        for (Class o : list) {
            Object bean = SpringUtil.getBean(o);
            Method method = o.getMethod("onInvitedInGroup", Bot.class, InvitedInGroupEvent.class);
            int invoke = (int) method.invoke(bean, bot,event);
            if (invoke != 0){
                break;
            }
        }
        return event;
    }

    /**
     * 面对面收款事件
     */
    public QRcodePaymentEvent onQRcodePayment(ApiEvent apiEvent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        QRcodePaymentEvent event = new QRcodePaymentEvent();
        //json解码
        event.setUserId(JSONUtil.parseObj(apiEvent.getContext()).getStr("to_wxid"));
        event.setPayerUserId(JSONUtil.parseObj(apiEvent.getContext()).getStr("payer_wxid"));
        event.setPayerUserName(JSONUtil.parseObj(apiEvent.getContext()).getStr("payer_nickname"));
        event.setPayerPayId(Integer.parseInt(JSONUtil.parseObj(apiEvent.getContext()).getStr("payer_pay_id")));
        event.setMoney(Integer.parseInt(JSONUtil.parseObj(apiEvent.getContext()).getStr("money")));
        event.setTotalMoney(Integer.parseInt(JSONUtil.parseObj(apiEvent.getContext()).getStr("total_money")));
        event.setRemark(JSONUtil.parseObj(apiEvent.getContext()).getStr("remark"));
        event.setScreneDesc(JSONUtil.parseObj(apiEvent.getContext()).getStr("scene_desc"));
        event.setScene(Integer.parseInt(JSONUtil.parseObj(apiEvent.getContext()).getStr("scene")));
        event.setTime(JSONUtil.parseObj(apiEvent.getContext()).getStr("time"));

        Bot bot = new Bot(apiEvent.getRobot_wxid());

        for (Class o : list) {
            Object bean = SpringUtil.getBean(o);
            Method method = o.getMethod("onQRcodePayment", Bot.class, QRcodePaymentEvent.class);
            int invoke = (int) method.invoke(bean, bot,event);
            if (invoke != 0){
                break;
            }
        }
        return event;
    }

    /**
     * 群名称变动事件
     */
    public GroupNameChangeEvent onGroupNameChange(ApiEvent apiEvent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        GroupNameChangeEvent event = new GroupNameChangeEvent();
        //json解码
        event.setGroupId(JSONUtil.parseObj(apiEvent.getContext()).getStr("from_group"));
        event.setNewName(JSONUtil.parseObj(apiEvent.getContext()).getStr("new_name"));
        event.setOldName(JSONUtil.parseObj(apiEvent.getContext()).getStr("old_name"));
        event.setOperater(JSONUtil.parseObj(apiEvent.getContext()).getStr("from_name"));
        Bot bot = new Bot(apiEvent.getRobot_wxid());
        for (Class o : list) {
            Object bean = SpringUtil.getBean(o);
            Method method = o.getMethod("onGroupNameChange", Bot.class, GroupNameChangeEvent.class);
            int invoke = (int) method.invoke(bean, bot,event);
            if (invoke != 0){
                break;
            }
        }
        return event;
    }

}
