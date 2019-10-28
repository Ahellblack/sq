package com.siti.wisdomhydrologic.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

public class DataSourceContextHolder {

    public enum DataSourceEnum {master,slaver;}

    private static final ThreadLocal<DataSourceEnum> CONTEXT_HOLDER = new ThreadLocal<DataSourceEnum>() {

        @Override
        protected DataSourceEnum initialValue() {
            return DataSourceEnum.master;
        }
    };
    public static void setDataSourceType(DataSourceEnum type) {
        CONTEXT_HOLDER.set(type);
    }
    public static DataSourceEnum getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }
    public static void resetDataSourceType() {
        CONTEXT_HOLDER.set(DataSourceEnum.master);
    }

}
