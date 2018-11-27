package com.tigrex.api.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class UserVo implements Serializable {

    private Integer id;

    private String name;

    private Integer age;

}
