package com.tigrex.admin.context;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class SystemData {

    private SystemData(){}

    /**
     * 初始化参数map
     */
    public static Map<String, Object> configMap;

    /**
     * url配置
     */
    public static Map<String, Object> urlMap;

}
