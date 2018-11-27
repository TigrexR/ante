package com.tigrex.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tigrex.admin.entity.User;
import com.tigrex.api.vo.UserVo;

import java.util.List;

public interface IUserService extends IService<User> {

    /**
     * 获取user信息
     * @return
     */
    List<UserVo> getUserVoList();

}