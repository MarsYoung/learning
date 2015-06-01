package com.marsyoung.learning.web.ssb.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.marsyoung.learning.web.ssb.entity.User;
import com.marsyoung.learning.web.ssb.mapper.UserMapper;
import com.marsyoung.learning.web.ssb.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource
	UserMapper userMapper;
	
	@Override
	public void addUser(User user) {
		userMapper.addUser(user);
		
	}

	@Override
	public void deleteUser(long userId) {
		userMapper.deleteUser(userId);
	}
	
	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
		
	}
	
	@Override
	public User selectUser(long userId) {
		
		return userMapper.selectUser(userId);
	}



}
