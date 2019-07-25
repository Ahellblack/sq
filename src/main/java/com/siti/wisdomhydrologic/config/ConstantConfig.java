package com.siti.wisdomhydrologic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by DC on 2019/6/12.
 *
 * @data ${DATA}-14:58
 */
@Configuration
public class ConstantConfig extends WebMvcConfigurerAdapter {
    public static final String FLAGT = "T";

    public static final String FLAGR = "R";

    public static final String FLAGW = "W";

    public static final String WS="83";

    public static final String TS="81";

    public static final String RS="84";

    public static final String BASEPACKAGE="com";

    public static final String SWAGGER_TITLE="swagger测试接口";

    public static final String DESCRIPTION="智慧水情运维系统";

    public static final String SWAGGER_VERSION="1.0";

    public static final String SWAGGER_URL="http://localhost:8099/sq/swagger-ui.html";

    /*

    */
/**
     * PC版本号
     *//*

    @Value("${version.pc}")
    private String pcversion;
    */
/**
     * 小版本号
     *//*

    @Value("${version.build}")
    private String build;


    public String getPcversion() {
        return pcversion;
    }

    public void setPcversion(String pcversion) {
        this.pcversion = pcversion;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }
*/
}
