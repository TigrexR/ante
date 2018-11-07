package com.tigrex.admin.elasticsearch.repository;

import com.tigrex.admin.elasticsearch.entity.UserEl;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<UserEl, Long> {
}
