package com.siti.wisdomhydrologic.util;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.siti.wisdomhydrologic.config.MsgConfig;

import java.util.Map;

public class PushMsg {

    /**
     * SDK
     *
     * @param phoneNumber 要发送的手机号，逗号分隔
     * @return 状态码（000000 成功，160040 发送次数超过最大限制，160038 操作频繁，160032 短信模板无效）
     */
    public static Map<String, Object> pushMsgToClient(String phoneNumber, String stationName,String time,String brokenAccording,String reprotId) {
        // 初始化SDK
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
        // 初始化配置
        restAPI.init(MsgConfig.SERVERIP, MsgConfig.SERVERPORT);
        restAPI.setAccount(MsgConfig.ACCOUNT_SID, MsgConfig.AUTH_TOKEN);
        restAPI.setAppId(MsgConfig.APPID);
        // 发送短信
        return restAPI.sendTemplateSMS(phoneNumber, MsgConfig.TEMPLATEID, new String[]{stationName,time,brokenAccording,reprotId});
    }


    public static Map<String, Object> pushBreakMsg(String phoneNumber) {
        // 初始化SDK
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
        // 初始化配置
        restAPI.init(MsgConfig.SERVERIP, MsgConfig.SERVERPORT);
        restAPI.setAccount(MsgConfig.ACCOUNT_SID, MsgConfig.AUTH_TOKEN);
        restAPI.setAppId(MsgConfig.APPID);
        // 发送短信
        return restAPI.sendTemplateSMS(phoneNumber, MsgConfig.TEMPLATEID2, new String[]{"水文数据疑似全部中断"});
    }


}