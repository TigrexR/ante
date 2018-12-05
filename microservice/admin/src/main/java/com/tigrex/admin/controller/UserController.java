package com.tigrex.admin.controller;

import com.tigrex.admin.context.SystemData;
import com.tigrex.admin.entity.User;
import com.tigrex.admin.service.IUserService;
import com.tigrex.admin.excel.ExcelUtil;
import com.tigrex.api.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/user")
//@Api("UserController")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @Transactional(value = "master")
    @GetMapping(value = "/getUser")
//    @ApiOperation(value = "根据id查询信息", notes = "查询数据库中某个的信息")
//    @ApiImplicitParam(name = "userId", value = "人员ID", paramType = "path", required = true, dataType = "Integer")
    public Object getUser(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "userId", required = true) Integer userId){

        HttpSession session = request.getSession();
        logger.info(session.getId());

        System.out.println(SystemData.configMap.get("george"));
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
