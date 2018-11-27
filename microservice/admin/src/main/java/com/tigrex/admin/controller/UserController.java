package com.tigrex.admin.controller;

import com.tigrex.admin.entity.User;
import com.tigrex.admin.service.IUserService;
import com.tigrex.admin.excel.ExcelUtil;
import com.tigrex.api.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/getUser")
    public Object getUser(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "userId", required = true) Integer userId){

        HttpSession session = request.getSession();
        logger.info(session.getId());

        User george = userService.getById(userId);

        return george;
    }

    @GetMapping(value = "/getUserExcel")
    public void getExcel(
            HttpServletRequest request,
            HttpServletResponse response){
        ExcelUtil.exportExcel(userService.getUserVoList(), "测试名", "什么名字", UserVo.class, "测试.xls", response);
    }

}
