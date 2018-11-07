package com.tigrex.admin.elasticsearch.service;

import com.tigrex.admin.elasticsearch.entity.UserEl;

public interface IUserElService {

    boolean save(UserEl user);

    boolean exist(UserEl user);

}
