package com.tigrex.user.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tigrex.user.entity.User;
import com.tigrex.user.mapper.UserMapper;
import com.tigrex.user.service.IUserService;
import com.tigrex.api.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserService extends ServiceImpl<BaseMapper<User>, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserVo> getUserVoList() {
        return userMapper.getUserVoList();
    }
}
