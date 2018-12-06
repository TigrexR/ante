package com.tigrex.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tigrex.api.vo.BookVo;
import com.tigrex.book.entity.Book;

import java.util.List;

/**
 * userMapper接口
 * @author linus
 */
public interface BookMapper extends BaseMapper<Book> {

    /**
     * 获取userList
     * @return
     */
    List<BookVo> getBookVoList();

}