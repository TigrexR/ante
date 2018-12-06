package com.tigrex.book.controller;

import com.tigrex.api.vo.BookVo;
import com.tigrex.book.service.IBookService;
import com.tigrex.book.service.impl.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private IBookService bookService;

    @GetMapping(value = "/getBookInfo")
    public BookVo getBookInfo(
            HttpServletRequest request,
            HttpServletResponse response,
            BookVo bookVo){

        HttpSession session = request.getSession();
        logger.info(session.getId());

        List<BookVo> bookVoList = bookService.getBookVoList();

        return new BookVo().setId(1).setName("springCloud全解").setAuthor("george");
    }

}
