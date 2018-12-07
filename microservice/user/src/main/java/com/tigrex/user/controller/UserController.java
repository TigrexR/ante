package com.tigrex.user.controller;

import com.tigrex.user.constant.ContextConst;
import com.tigrex.user.constant.DataSource;
import com.tigrex.user.utils.DataSourceContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/getUser")
    @DataSource(value = ContextConst.DataSourceType.USER)
    public Object getUser(
            HttpServletRequest request,
            HttpServletResponse response){
        HttpSession session = request.getSession();
        logger.info(session.getId());

        return DataSourceContextHolder.getDataSource();
    }

    @GetMapping(value = "/getBook")
    @DataSource(value = ContextConst.DataSourceType.BOOK)
    public Object getBook(
            HttpServletRequest request,
            HttpServletResponse response){
        return DataSourceContextHolder.getDataSource();
    }

    @GetMapping(value = "/getAdmin")
    @DataSource(value = ContextConst.DataSourceType.ADMIN)
    public Object getAdmin(
            HttpServletRequest request,
            HttpServletResponse response){
        return DataSourceContextHolder.getDataSource();
    }

}
