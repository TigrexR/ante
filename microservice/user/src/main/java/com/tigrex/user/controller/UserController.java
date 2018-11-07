package com.tigrex.user.controller;

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
    public Object getUser(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "userId", required = true) Integer userId){

        HttpSession session = request.getSession();
        logger.info(session.getId());

        return "项目成功";
    }

}
