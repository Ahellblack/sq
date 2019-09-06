package com.siti.wisdomhydrologic.mainpage.controller;

import com.siti.wisdomhydrologic.config.ConstantConfig;
import com.siti.wisdomhydrologic.config.HaiKangVideoConfig;
import com.siti.wisdomhydrologic.mainpage.entity.HaiKangResult;
import com.siti.wisdomhydrologic.mainpage.vo.DeviceVo;
import com.siti.wisdomhydrologic.mainpage.vo.HaiKangVo;
import com.siti.wisdomhydrologic.mainpage.vo.WeatherVo;
import com.siti.wisdomhydrologic.util.HttpClientUtil;
import com.siti.wisdomhydrologic.util.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dell on 2019/8/7.
 */
@RestController
@RequestMapping("/haikang")
@Api(value = "海康视频水情首页问湿度controller", tags = {"海康视频水情首页问湿度"})
public class HaiKangController {

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
        /*RETURNresult.setFluencyEzopen("//open.ys7.com/D24409905/1.live");
        RETURNresult.setHighDefinitionEzopen("//open.ys7.com/D24409905/1.hd.live");*/
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
    public static DeviceVo getDeviceConfig() {
        DeviceVo deviceVo = new DeviceVo();
        DeviceVo deviceVo2 = new DeviceVo();
        try {
            String url = ConstantConfig.DEVIDURL + "?devid=" + ConstantConfig.DEVID;
            String result = HttpClientUtil.doPost(url);
            deviceVo = JsonUtils.parse(result, DeviceVo.class);
            deviceVo2 = JsonUtils.parse(deviceVo.getDevice_real_status(), DeviceVo.class);
        } catch (Exception e) {
            System.out.println("获取机房温湿度失败");
        }

        return deviceVo2;
    }


    @ApiOperation(value = "水情首页温湿度天气接口", httpMethod = "GET", notes = "水情首页温湿度天气接口")
    @GetMapping("/getWeather")
    public static WeatherVo getWeather() {
        WeatherVo vo = new WeatherVo();
        WeatherVo vo1 = new WeatherVo();
        WeatherVo vo2 = new WeatherVo();
        try {
            String url = "http://114.80.231.178:18080/openDataTest/weatherAction/getWeatherInfoEx?cityName=浦东&index=1&en=1";
            String result = HttpClientUtil.doGet(url);
            vo = JsonUtils.parse(result, WeatherVo.class);
            vo1 = JsonUtils.parse(vo.getData(), WeatherVo.class);
            vo2 = vo1.getForcast()[0];
            vo2.setCityName(vo1.getCityName());
            vo2.setUpdateTime(vo1.getUpdateTime());
            vo2.setCurrentTemp(vo1.getCurrentTemp());
            vo2.setCurrentShidu(vo1.getCurrentShidu());
            vo2.setCurrentWindOrient(vo1.getCurrentWindOrient());
        } catch (Exception e) {
            System.out.println("水情首页温湿度天气获取失败");
        }

        return vo2;
    }

}
