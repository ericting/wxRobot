package com.example.demo.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.demo.config.PluginList;
import com.example.demo.domain.ApiEvent;
import com.example.demo.event.PrivateMessageEvent;
import com.example.demo.util.GetEncode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;


/**
 *  事件接收控制器
 * */
@RequestMapping("/api/v1/callback")
@RestController
public class EventController {
    @Autowired
    PluginList plugins;

    @PostMapping("/wechat/robot")
    @ResponseBody
    public void api(HttpServletRequest request, @RequestBody String body){
        ApiEvent apiEvent = new ApiEvent();

        JSONObject jsonObject =JSONUtil.parseObj(body);
        apiEvent.setSdkVer(Integer.parseInt(jsonObject.getStr("sdkVer")));//sdk版本
        apiEvent.setEvent(jsonObject.getStr("Event"));//事件名称

        JSONObject jsonObject1=JSONUtil.parseObj(jsonObject.getStr("content"));

        apiEvent.setContext(jsonObject1);//返回事件内容
        apiEvent.setRobot_wxid(jsonObject1.getStr("robot_wxid"));
        try {
            plugins.handle(apiEvent);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
