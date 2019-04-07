package com.abd.elastic.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.abd.elastic.entity.User;

/**
 *
 * @author : aaabdullah
 * @date   : Apr 2, 2019 
 */
@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {

	List<User> findByUserId(String userId);
	
	List<User> findByName(String name);

}
