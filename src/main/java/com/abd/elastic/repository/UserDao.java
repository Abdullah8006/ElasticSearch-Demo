package com.abd.elastic.repository;

import java.util.List;

import com.abd.elastic.entity.User;

/**
 *
 * @author : aaabdullah
 * @date   : Apr 2, 2019 
 */
public interface UserDao {

	User getUserById(String userId);

	User searchBy(String field, String value);
	
	List<User> getAllBy(String value);

}
