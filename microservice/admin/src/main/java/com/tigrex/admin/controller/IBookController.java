package com.tigrex.admin.controller;

import com.tigrex.admin.config.FeignConfig;
import com.tigrex.api.vo.BookVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "book", configuration = FeignConfig.class)
@RequestMapping(value = "/book")
public interface IBookController {

    @GetMapping(value = "/getBookInfo")
    BookVo getBookInfo(BookVo bookVo);

}
