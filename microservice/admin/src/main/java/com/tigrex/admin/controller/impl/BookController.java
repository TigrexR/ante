package com.tigrex.admin.controller.impl;

import com.tigrex.admin.controller.IBookController;
import com.tigrex.api.vo.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private IBookController bookController;

    @GetMapping(value = "/getBookInfo")
    public Object getBookInfo(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "bookId") Integer bookId
        ){
        BookVo bookVo = new BookVo().setId(bookId);
        bookVo = bookController.getBookInfo(bookVo);
        return bookVo;
    }

}
