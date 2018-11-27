package com.tigrex.admin.config;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tigrex.admin.context.SystemData;
import com.tigrex.admin.entity.SystemConfig;
import com.tigrex.admin.service.ISystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * 系统从数据库初始化配置数据
 */
@Component
@Order(value = 1)
public class SystemDataConfig implements CommandLineRunner {

    @Autowired
    private ISystemConfigService systemConfigService;

    @Override
    public void run(String... args) throws Exception {
        SystemData.configMap = new HashMap();
        List<SystemConfig> systemConfigs = systemConfigService.list(new QueryWrapper<>());
        if(systemConfigs != null && systemConfigs.size() > 0){
            for (SystemConfig systemConfig : systemConfigs) {
                SystemData.configMap.put(systemConfig.getKey(), systemConfig.getValue());
            }
        }
    }

}
