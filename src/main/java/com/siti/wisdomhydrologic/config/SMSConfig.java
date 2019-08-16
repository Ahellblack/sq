package com.siti.wisdomhydrologic.config;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

/**
 * 短信推送---云通讯【http://www.yuntongxun.com/】
 * @data ${DATA}-12:42
 *
 * 3414874988@qq.com
    cyy54325166
 */
@Configuration
public class SMSConfig {

    private static final Logger logger = LoggerFactory.getLogger(SMSConfig.class);

    @Value("${sms.account_sid}")
    private static String account_sid;

    @Value("${sms.auth_token}")
    private static String auth_token;

    @Value("${sms.appid}")
    private static String appid;

    @Value("${sms.serverip}")
    private static String serverip;

    @Value("${sms.serverport}")
    private static String serverport;

    @Value("${sms.templateid}")
    private static String  templateid;

    @Value("${sms.flag}")
    private static boolean  flag;

    /**
     * SDK
     * @param phoneNumber 要发送的手机号，逗号分隔
     * @return 状态码（000000 成功，160040 发送次数超过最大限制，160038 操作频繁，160032 短信模板无效）
     */
    public static Map<String, Object> pushMsgToClient(String phoneNumber, String... args) {
        if(flag) {
            // 初始化SDK
            CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
            // 初始化配置
            restAPI.init(serverip,serverport);
            restAPI.setAccount(account_sid, auth_token);
            restAPI.setAppId(appid);
            // 发送短信
            return restAPI.sendTemplateSMS(phoneNumber, templateid, new String[]{args[0], args[1], args[2]});
        }else{
            logger.info("未开启短信功能！");
            return null;
        }
    }

}
