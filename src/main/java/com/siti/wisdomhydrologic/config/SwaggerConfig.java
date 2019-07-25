package com.siti.wisdomhydrologic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by DC on 2019/7/25.
 *
 * @data ${DATA}-16:22
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(ConstantConfig.BASEPACKAGE))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        // TODO Auto-generated method stub
        return new ApiInfoBuilder()
                .title(ConstantConfig.SWAGGER_TITLE)
                .description(ConstantConfig.DESCRIPTION)
                .termsOfServiceUrl(ConstantConfig.SWAGGER_URL)
                .version(ConstantConfig.SWAGGER_VERSION)
                .build();
    }

}
