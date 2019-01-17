package com.tigrex.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tigrex.user.entity.User;
import com.tigrex.api.vo.UserVo;

import java.util.List;

public interface IUserService extends IService<User> {

    /**
     * 获取user信息
     * @return
     */
    List<UserVo> getUserVoList();

    boolean insert(List<User> userList);

    boolean insertAdmin(User user);
    boolean insertBook(User user);
    boolean insertUser(User user);

}