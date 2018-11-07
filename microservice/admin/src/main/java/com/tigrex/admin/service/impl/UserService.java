package com.tigrex.admin.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tigrex.admin.entity.User;
import com.tigrex.admin.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<BaseMapper<User>, User> implements IUserService {

}
