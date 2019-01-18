package com.tigrex.user.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tigrex.api.annotation.DataSource;
import com.tigrex.api.constant.ContextConst;
import com.tigrex.user.entity.User;
import com.tigrex.user.mapper.UserMapper;
import com.tigrex.user.service.IUserService;
import com.tigrex.api.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "userService")
public class UserService extends ServiceImpl<BaseMapper<User>, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @DataSource(value = ContextConst.DataSourceType.ADMIN)
    public List<UserVo> getUserVoList() {
        return userMapper.getUserVoList();
    }

    @Override
    @DataSource(value = ContextConst.DataSourceType.BOOK)
    public boolean insert(List<User> userList) {
        saveBatch(userList);
        return false;
    }

    @Override
    @Transactional(value = "transactionManagerAdmin")
    @DataSource(value = ContextConst.DataSourceType.ADMIN)
    public boolean insertAdmin(User user) {
        return save(user);
    }

    @Override
    @Transactional(value = "transactionManagerBook")
    @DataSource(value = ContextConst.DataSourceType.BOOK)
    public boolean insertBook(User user) {
        return save(user);
    }

    @Override
    @Transactional(value = "transactionManagerUser")
    @DataSource(value = ContextConst.DataSourceType.USER)
    public boolean insertUser(User user) {
        return save(user);
    }


}
