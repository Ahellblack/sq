package com.siti.wisdomhydrologic.video;

import com.siti.wisdomhydrologic.config.HaiKangVideoConfig;
import com.siti.wisdomhydrologic.config.RabbitMQConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dell on 2019/8/7.
 */
@RestController
@RequestMapping("/haikang")
public class HaiKangController {

    @RequestMapping("/getConfig")
    public static HaiKangVideoConfig getVideoConfig(){
        HaiKangVideoConfig haiKangVideoConfig =new HaiKangVideoConfig();
        haiKangVideoConfig.setAppKey("c725240706e4454bad75945b219ba0e5");
        haiKangVideoConfig.setAppSecret("528c2c2221b206c90861ce56a73d73bf");
        haiKangVideoConfig.setFluencyEzopen("//open.ys7.com/D24409905/1.live");
        haiKangVideoConfig.setHighDefinitionEzopen("//open.ys7.com/D24409905/1.hd.live");
        return haiKangVideoConfig;
    }

}
