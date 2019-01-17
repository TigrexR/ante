package com.tigrex.user.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tigrex.api.constant.ContextConst;
import com.tigrex.api.annotation.DataSource;
import com.tigrex.api.vo.UserVo;
import com.tigrex.user.entity.User;
import com.tigrex.user.service.IUserService;
import com.tigrex.user.utils.DataSourceContextHolder;
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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

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

    @GetMapping(value = "/getUser")
    public Object getUser(
            HttpServletRequest request,
            HttpServletResponse response){
        List<UserVo> userVos = userService.getUserVoList();
        List<User> users = new LinkedList<>();
        for (i = 9279; i < 10000; i++) {
            User user = new User().setName("1354797" + i);
            users.add(user);
        }
        userService.insert(users);
        return "hello";
    }

    @GetMapping(value = "/getBook")
    @DataSource(value = ContextConst.DataSourceType.BOOK)
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
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<String>> futures = new ArrayList<>();
        Set<Callable<String>> set = new LinkedHashSet<>();
        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userList = userService.list(userWrapper);
        ReentrantLock reentrantLock = new ReentrantLock(false);
        if(userList != null && userList.size() > 0){
            for (i = 0; i < userList.size(); i++) {
                Callable<String> callable = new Callable<>() {//创建callable
                    @Override
                    public String call() throws Exception {
                        reentrantLock.lock();
                        try {
                            User baseUser = userList.get(i);
                            List<User> users = new LinkedList<>();
                            for (int j = 0; j < 10; j++) {
                                if(j < 10){
                                    User user = new User().setName(baseUser.getName() + "000" + j);
                                    users.add(user);
                                } else if(j > 9 && j < 100){
                                    User user = new User().setName(baseUser.getName() + "00" + j);
                                    users.add(user);
                                } else if(j > 99 && j < 1000){
                                    User user = new User().setName(baseUser.getName() + "0" + j);
                                    users.add(user);
                                } else {
                                    User user = new User().setName(baseUser.getName() + j);
                                    users.add(user);
                                }
                            }
                            userService.saveBatch(users);
                            return "";
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                            return "";
                        } finally {
                            reentrantLock.unlock();
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
