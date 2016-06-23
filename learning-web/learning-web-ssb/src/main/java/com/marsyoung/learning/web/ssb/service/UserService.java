package com.marsyoung.learning.web.ssb.service;

import com.marsyoung.learning.web.ssb.entity.User;

public interface UserService {

	void addUser(User user);

	void deleteUser(long userId);

	User selectUser(long userId);

	void updateUser(User user);

}
