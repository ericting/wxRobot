package com.example.demo.plugin;

import cn.hutool.core.util.XmlUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.demo.domain.Bot;
import com.example.demo.domain.BotPlugin;
import com.example.demo.event.PrivateMessageEvent;
import okhttp3.*;
import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.example.demo.config.MessageConfig.*;

@Component
public class StatusPlugin extends BotPlugin {
    private String domain="http://120.26.185.233:12345/status";
    @Override
    public int onPrivateMessage(Bot bot, @NotNull PrivateMessageEvent event) {
        if(event.getType()==MESSAGE_TEXT_TYPE){
            /**
             * 学习通相关
             */
            if (event.getMessage().equals("开启学习通签到")){
                String str = JSONUtil.parseObj(HttpUtil.get(domain + "/enable")).getStr("msg");
                String resp = str.equals("success") ? "成功." : "失败.";
                bot.sendPrivateMessage(event.getUserId(),"学习通签到开启"+resp);
            }else if (event.getMessage().equals("关闭学习通签到")){
                String str = JSONUtil.parseObj(HttpUtil.get(domain + "/disable")).getStr("msg");
                String resp = str.equals("success") ? "成功." : "失败.";
                bot.sendPrivateMessage(event.getUserId(),"学习通签到关闭"+resp);
            }else if (event.getMessage().equals("查询学习通状态")){
                JSONObject jsonObject =JSONUtil.parseObj(HttpUtil.get(domain+"/enable"));
                bot.sendPrivateMessage(event.getUserId(),"学习通账号："+
                        jsonObject.getStr("username")+"\n签到状态："+
                        jsonObject.get("cxAutoSignEnabled")
                );
            }
            /**
             * 运行状态
             */
            if (event.getMessage().equals("运行状态") || event.getMessage().equals("status") ){
                Runtime rt=Runtime.getRuntime();
                long freememory = rt.freeMemory() / 1024;
                long totalmenmory=rt.totalMemory()/1024;
               String str= "操作系统的版本："+System.getProperty("os.name")+"\n"+
                       "系统内存的空闲空间为："+freememory+"MB\n"+
                       "系统总内存空间为："+totalmenmory+"MB\n";
            }

        }
        /**
         * 酷安发打卡贴
         */
        if (event.getType()==MESSAGE_SHARELINK_TYPE ){
            Map<String, Object> xmlMap = new HashMap<>();
            xmlMap=XmlUtil.xmlToMap(JSONUtil.toXmlStr(JSONUtil.parseObj(event.getMessage())));
            //cookapk请求体
            String str=xmlMap.get("title").toString()+"\n"+xmlMap.get("url");
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("multipart/form-data; boundary=cd39952a-dd7e-4428-95fc-39fb77b19827");
            RequestBody body = RequestBody.create(mediaType, "--cd39952a-dd7e-4428-95fc-39fb77b19827\nContent-Disposition: form-data; name=\"message\"\nContent-Length:" +
                   str.length()+ " --cd39952a-dd7e-4428-95fc-39fb77b19827--" +
                    str);
            Request request = new Request.Builder()
                    .url("https://api.coolapk.com/v6/feed/reply?id=25469248&type=feed")
                    .method("POST", body)
                    .addHeader("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 10; Redmi K20 Pro Premium Edition MIUI/20.12.9) (#Build; Xiaomi; Redmi K20 Pro Premium Edition; QKQ1.190825.002 test-keys; 10) +CoolMarket/final-99999999-universal")
                    .addHeader("X-Requested-With", "XMLHttpRequest")
                    .addHeader("X-Sdk-Int", "29")
                    .addHeader("X-Sdk-Locale", "zh-CN")
                    .addHeader("X-App-Id", "com.coolapk.market")
                    .addHeader("X-App-Token", "08024f0c2f81b3f7f888edfefcb94a4fefe7852f-ffdc-3886-884f-6ea92adfabf80x614efb87")
                    .addHeader("X-App-Version", "final")
                    .addHeader("X-App-Code", "99999999")
                    .addHeader("X-Api-Version", "11")
                    .addHeader("X-App-Device", "42bpRXakVEItVXatVmcQBybyBFIwIzSgkWbkVmUgsTat9WYphFI7kWbvFWaYByOzcjO1cjOFBjO4cjO0YkOwIDI7AyOgszMzUmN1cTZ1MjNyUGZ2IDN")
                    .addHeader("X-Dark-Mode", "0")
                    .addHeader("X-App-Channel", "yyb")
                    .addHeader("X-App-Mode", "universal")
                    .addHeader("Content-Type", "multipart/form-data; boundary=cd39952a-dd7e-4428-95fc-39fb77b19827")
                    .addHeader("Host", "api.coolapk.com")
                    .addHeader("Cookie", "uid=1372768; username=%E8%A1%A5%E8%80%83%E5%8F%88%E4%B8%8D%E5%8F%8A%E6%A0%BC; token=7d7b8da7lf26ai1lciLzR4IwNPAaAsnsGP4BkaTF4VmDGpHL_8N_rDlV3v_tEITYFM8DUifdoAO7RF-UyTueFErbsWQVCf_RYq-wotVM2zmYmvS-BAlPfu7lAlMS8X72dhkMz0Z1YWV6FD65E5bUS7l9uafGL72f-sElpRbkwe0UI1qtq68_-zTFiEUzvZvFYfdNtoif1If82sto6-HIPJCEf_3EPIEOMjR28z3YR3KHN7P04Mk; SESSID=080efe357c04a58f20dfa063aa4c21eb952c0089")
                    .addHeader("Accept-Encoding", "gzip")
                    .build();
            try {
                Response response = client.newCall(request).execute();
                if(response.code()==200){
                    bot.sendPrivateMessage(event.getUserId(),xmlMap.get("title").toString()+"\n发帖成功");
                    return MESSAGE_IN;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            bot.sendPrivateMessage(event.getUserId(),"发送失败");
        }
        return MESSAGE_IN;
    }
}
