package org.springframework.data.elasticsearch.repository.support;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.UserEntity;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repositories.UserRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Thibault Normand
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/simple-repository-test.xml")
public class UserRepositoryTests {

    @Resource
    private UserRepository repository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Before
    public void before() {
        elasticsearchTemplate.deleteIndex(UserEntity.class);
        elasticsearchTemplate.createIndex(UserEntity.class);
        elasticsearchTemplate.refresh(UserEntity.class, true);
    }

    @Test
    public void shouldDoBulkIndexDocument() {
        // given
        UserEntity user1 = new UserEntity("Alice", "Cooper", "alice@cooper.com");
        user1.setVersion(System.currentTimeMillis());
        String user1Id = user1.getUuid();

        UserEntity user2 = new UserEntity("Bob", "Marley", "bob@marley.com");
        user2.setVersion(System.currentTimeMillis());
        String user2Id = user2.getUuid();

        // when
        repository.save(Arrays.asList(user1, user2));

        // then
        UserEntity user1FromElasticSearch = repository.findOne(user1Id);
        assertThat(user1FromElasticSearch, is(notNullValue()));

        UserEntity user2FromElasticSearch = repository.findOne(user2Id);
        assertThat(user2FromElasticSearch, is(notNullValue()));
    }

    @Test
    public void shouldSaveDocument() {
        // given
        UserEntity user1 = new UserEntity("Alice", "Cooper", "alice@cooper.com");
        user1.setVersion(System.currentTimeMillis());
        String user1Id = user1.getUuid();

        // when
        repository.save(user1);
        // then
        UserEntity user1FromElasticSearch = repository.findOne(user1Id);
        assertThat(user1FromElasticSearch, is(notNullValue()));
    }

    @Test
    public void shouldFindDocumentById() {
        // given
        UserEntity user1 = new UserEntity("Alice", "Cooper", "alice@cooper.com");
        user1.setVersion(System.currentTimeMillis());
        String user1Id = user1.getUuid();
        repository.save(user1);
        // when
        UserEntity user1FromElasticSearch = repository.findOne(user1Id);
        // then
        assertThat(user1FromElasticSearch, is(notNullValue()));
        assertThat(user1FromElasticSearch, is((equalTo(user1))));
    }

    @Test
    public void shouldReturnCountOfDocuments() {
        // given
        UserEntity user1 = new UserEntity("Alice", "Cooper", "alice@cooper.com");
        user1.setVersion(System.currentTimeMillis());
        String user1Id = user1.getUuid();
        repository.save(user1);
        // when
        Long count = repository.count();
        // then
        assertThat(count, is(greaterThanOrEqualTo(1L)));
    }

    @Test
    public void shouldFindDocumentByEmail() {
        // given
        UserEntity user1 = new UserEntity("Alice", "Cooper", "alice@cooper.com");
        user1.setVersion(System.currentTimeMillis());
        String user1Id = user1.getUuid();
        repository.save(user1);

        // when
        List<UserEntity> userListFromElasticSearch = repository.findByEmail("alice@cooper.com");

        // then
        assertThat(userListFromElasticSearch, is(notNullValue()));
        Integer count = userListFromElasticSearch.size();
        assertThat(count, is(greaterThanOrEqualTo(1)));
    }

}
