package com.abd.elastic.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abd.elastic.entity.User;
import com.abd.elastic.repository.UserDao;
import com.abd.elastic.repository.UserRepository;

/**
 *
 * @author : aaabdullah
 * @date : Apr 2, 2019
 */
@RestController
@RequestMapping("/api/v1/user")
public class RunnertController {

	private UserRepository userRepository;
	private UserDao userDao;

	public RunnertController(UserRepository userRepository, UserDao userDao) {
		this.userRepository = userRepository;
		this.userDao = userDao;
	}

	@PostMapping
	public User addNewUser(@RequestBody User user) {
		user.setUserId(String.valueOf(System.currentTimeMillis()));
		return userRepository.save(user);
	}

	@GetMapping
	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@GetMapping("/find/{field}/{value}")
	public User findUsersById(@PathVariable String field, @PathVariable String value) {
		return userDao.searchBy(field, value);
	}

}
