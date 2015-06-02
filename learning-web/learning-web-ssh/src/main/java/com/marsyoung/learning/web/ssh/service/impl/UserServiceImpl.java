package com.marsyoung.learning.web.ssh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.marsyoung.learning.web.ssh.dao.UserDao;
import com.marsyoung.learning.web.ssh.entity.User;
import com.marsyoung.learning.web.ssh.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource
	UserDao userDao;
	
	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Override
	public void deleteUser(long userId) {
		userDao.deleteUser(userId);
	}
	
	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}
	
	@Override
	public User selectUser(long userId) {
		return userDao.selectUser(userId);
	}



}
