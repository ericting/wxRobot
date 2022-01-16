package com.example.demo.domain;

import cn.hutool.json.JSONObject;
import lombok.Data;

import java.util.Date;

@Data
public class ApiEvent {
    private int sdkVer;
    private String Event;
    private String Robot_wxid;
    private JSONObject context;
}
