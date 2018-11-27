package com.tigrex.admin.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tigrex.admin.entity.SystemConfig;
import com.tigrex.admin.service.ISystemConfigService;
import org.springframework.stereotype.Service;

/**
 * 初始数据serviceimpl
 */
@Service
public class SystemConfigService extends ServiceImpl<BaseMapper<SystemConfig>, SystemConfig> implements ISystemConfigService {
}
