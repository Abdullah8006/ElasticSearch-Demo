package com.abd.elastic.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abd.elastic.entity.User;
import com.abd.elastic.repository.UserDao;

/**
 *
 * @author : aaabdullah
 * @date : Apr 2, 2019
 */
@RestController
@RequestMapping("/api/v1/user/manual")
public class RunnerManualController {

	private UserDao userDao;
	
	public RunnerManualController(UserDao userDao) {
		this.userDao = userDao;
	}


	@GetMapping("/{value}")
	public List<User> findUsersById(@PathVariable String value) {
		return userDao.getAllBy(value);
	}

}
