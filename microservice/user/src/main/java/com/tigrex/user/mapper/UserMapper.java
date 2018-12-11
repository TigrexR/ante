package com.tigrex.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tigrex.user.entity.User;
import com.tigrex.api.vo.UserVo;

import java.util.List;

/**
 * userMapper接口
 * @author linus
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取userList
     * @return
     */
    List<UserVo> getUserVoList();

}