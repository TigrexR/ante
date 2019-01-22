package com.tigrex.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tigrex.user.entity.User;
import com.tigrex.user.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private static List<User> userList;

    /**
     * 用户表
     */
    @Autowired
    private IUserService userService;

    private static int i = 0;

    private static int k = -1;

    @GetMapping(value = "/getUser")
    public Object getUser(
            HttpServletRequest request,
            HttpServletResponse response){
        User user = new User();
        return "hello";
    }

    @GetMapping(value = "/getBook")
    public Object getBook(
            HttpServletRequest request,
            HttpServletResponse response){
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("PG24"));
//        session.setAttribute("PG24", "Pacers");
//        System.out.println(session.getId());
//        session.invalidate();

        User user = userService.getById(1);
        return user;
    }

    @GetMapping(value = "/getAdmin")
    public Object getAdmin(
            HttpServletRequest request,
            HttpServletResponse response){
        ExecutorService executorService = Executors.newFixedThreadPool(16);
        List<Future<String>> futures = new ArrayList<>();
        Set<Callable<String>> set = new LinkedHashSet<>();
        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userList = userService.list(userWrapper);
//        ReentrantLock reentrantLock = new ReentrantLock(false);
        if(userList != null && userList.size() > 0){
            for (i = 0; i < userList.size(); i++) {
                Callable<String> callable = new Callable<>() {//创建callable
                    @Override
                    public String call() throws Exception {
                        try {
                            return "";
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                            return "";
                        }
                    }
                };
                set.add(callable);//add进set集合
            }
            try {
                futures = executorService.invokeAll(set);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    @RequestMapping(value = "/dataSource")
    public void dataSource() throws RuntimeException{
        User user = new User().setId(1).setAge(13).setName("george");
        userService.insertAdmin(user);
        userService.insertBook(user);
        userService.insertUser(user);
    }

}
