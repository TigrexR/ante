package com.tigrex.admin.elasticsearch.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Document(indexName = "index_entity", type = "tstype")
public class UserEl implements Serializable {

    private static final long serialVersionUID = -763638353551774166L;

    private Integer id;

    private String name;

    private Integer age;

}
