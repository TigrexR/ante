package com.tigrex.user.utils;

import com.tigrex.user.constant.ContextConst;

public class DataSourceContextHolder {

    public static final ContextConst.DataSourceType DEFAULT_DATASOURCE = ContextConst.DataSourceType.USER;

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDataSource(String dbType){
        contextHolder.set(dbType);
    }

    public static String getDataSource(){
        return contextHolder.get();
    }

    public static void clearDataSource(){
        contextHolder.remove();
    }

}
