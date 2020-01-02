package com.siti.wisdomhydrologic.util;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.siti.wisdomhydrologic.config.CaffeineInit;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.function.Function;

/**
 * Created by dell on 2020/1/2.
 */
public class CaffeineUtil<K,V,T,R> {

    @Autowired
    private static Cache cache;

    private static CaffeineUtil CaffeineUtil;
    private CaffeineUtil(){
    }
    static {
        cache = CaffeineInit.getCaffeineConfig();
    }
    public static CaffeineUtil build() {
        return new CaffeineUtil();
    }


    public R getValues(String key,Function<T,R> function) {


        if (StringUtils.isBlank(key) ) {
            return null;
        }
        R res = (R)cache.getIfPresent(key);
        /*Iterable iterable = new Iterable() {
            @Override
            public Iterator iterator() {
                return null;
            }
        };
        Map map = cache.getAllPresent(iterable);*/
        if (Objects.isNull(res)) {//从db取值
            //obj = db.getdata;
            res = function.apply(null);
            cache.put(key, res);
        }
        return res;
    }
}
