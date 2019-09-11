package com.siti.wisdomhydrologic.log.service;

import java.lang.annotation.*;

/**
 * Created by DC on 2019/8/29.
 *
 * @data ${DATA}-11:05
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLog {
    String value();
}
