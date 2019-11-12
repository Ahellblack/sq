package com.siti.wisdomhydrologic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by zyw on 2019/11/1.
 */
@Configuration
public class FileUploadConfig extends WebMvcConfigurerAdapter {

    @Value("${file.maxFileSize}")
    private Long maxFileSize;

    public Long getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(Long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }
}
