package org.springframework.data.elasticsearch.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.SampleEntity;
import org.springframework.data.elasticsearch.UserEntity;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author Thibault Normand
 */
public interface UserRepository extends ElasticsearchRepository<UserEntity, String> {

    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"email\" : \"?0\"}}}}")
    List<UserEntity> findByEmail(final String email);

}
