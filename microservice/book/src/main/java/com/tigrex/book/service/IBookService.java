package com.tigrex.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tigrex.api.vo.BookVo;
import com.tigrex.book.entity.Book;

import java.util.List;

public interface IBookService extends IService<Book> {

    /**
     * 获取user信息
     * @return
     */
    List<BookVo> getBookVoList();

}