package com.tigrex.book.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tigrex.api.vo.BookVo;
import com.tigrex.book.entity.Book;
import com.tigrex.book.mapper.BookMapper;
import com.tigrex.book.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService extends ServiceImpl<BaseMapper<Book>, Book> implements IBookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<BookVo> getBookVoList() {
        return bookMapper.getBookVoList();
    }
}
