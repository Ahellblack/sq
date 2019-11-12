package com.siti.wisdomhydrologic.mainpage.controller;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.config.HaiKangVideoConfig;
import com.siti.wisdomhydrologic.mainpage.entity.HaiKangResult;
import com.siti.wisdomhydrologic.mainpage.mapper.DeviceTemMapper;
import com.siti.wisdomhydrologic.mainpage.vo.DeviceVo;
import com.siti.wisdomhydrologic.mainpage.vo.HaiKangVo;
import com.siti.wisdomhydrologic.mainpage.vo.WeatherApiVo;
import com.siti.wisdomhydrologic.mainpage.vo.WeatherVo;
import com.siti.wisdomhydrologic.util.HttpClientUtil;
import com.siti.wisdomhydrologic.util.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zyw on 2019/8/7.
 */
@RestController
@RequestMapping("/haikang")
@Api(value = "海康视频水情首页问湿度controller", tags = {"海康视频水情首页问湿度"})
public class HaiKangController {

    @Resource
    DeviceTemMapper deviceTemMapper;

    @ApiOperation(value = "海康视频接口", httpMethod = "GET", notes = "根据appkey及secret获取的视频地址")
    @GetMapping("/getConfig")
    public static HaiKangVo getVideoConfig() {
        HaiKangVideoConfig haiKangVideoConfig = new HaiKangVideoConfig();
        haiKangVideoConfig.setAppKey(ConstantConfig.HAIKANGAPPKEY);
        haiKangVideoConfig.setAppSecret(ConstantConfig.HAIKANGAPPSECRET);
        haiKangVideoConfig.setFluencyEzopen(ConstantConfig.FLUENCYEZOPEN);
        haiKangVideoConfig.setHighDefinitionEzopen(ConstantConfig.HIGHDEFINITIONEZOPEN);
        HaiKangResult haiKangResult = new HaiKangResult();
        HaiKangResult RETURNresult = new HaiKangResult();
        HaiKangVo haiKangVo = new HaiKangVo();
        HaiKangVo returnVo = new HaiKangVo();
        try {
            String url = ConstantConfig.HAIKANGURL + "?appKey=" + ConstantConfig.HAIKANGAPPKEY + "&appSecret=" + ConstantConfig.HAIKANGAPPSECRET;
            String result = HttpClientUtil.doPost(url);
            haiKangResult = JsonUtils.parse(result, HaiKangResult.class);
            RETURNresult = JsonUtils.parse(haiKangResult.getData(), HaiKangResult.class);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("获取token失败");
        }
        try {
            String url2 = ConstantConfig.HAIKANGURL2 + "?accessToken=" + RETURNresult.getAccessToken();
            String s = HttpClientUtil.doPost(url2);
            haiKangVo = JsonUtils.parse(s, HaiKangVo.class);
            String data = null;
            for (int i = 0; i < haiKangVo.getData().length; i++) {
                data = haiKangVo.getData()[i];
                returnVo = JsonUtils.parse(data, HaiKangVo.class);
                if (ConstantConfig.HAIKANGLIVELIST.equals(returnVo.getDeviceSerial())) {
                    haiKangVo = returnVo;
                }
            }
        } catch (Exception e) {
        }
        return haiKangVo;
    }

    @ApiOperation(value = "水情首页温湿度接口", httpMethod = "GET", notes = "获取实时机房温湿度")
    @GetMapping("/getDeviceConfig")
    public  DeviceVo getDeviceConfig() {
        try {
            DeviceVo deviceVo = deviceTemMapper.getTemperature();
            return deviceVo;
        } catch (Exception e) {
            System.out.println("获取机房温湿度失败");
            return null;
        }
    }


    @ApiOperation(value = "水情首页温湿度天气接口", httpMethod = "GET", notes = "水情首页温湿度天气接口")
    @GetMapping("/getWeather")
    public static WeatherApiVo getWeather() {

        WeatherApiVo vo = new WeatherApiVo();
        try {
            //天气api浦东新区接口调用  城市信息为 cityid  101020600为浦东新区
            String url = "https://www.tianqiapi.com/api/?version=v6&cityid=101020600&appid=68261499&appsecret=IfTIll7V";
            String result = HttpClientUtil.doGet(url);
            vo = JsonUtils.parse(result, WeatherApiVo.class);

        } catch (Exception e) {
            System.out.println("水情首页温湿度天气获取失败");
        }

        return vo;
    }

}
