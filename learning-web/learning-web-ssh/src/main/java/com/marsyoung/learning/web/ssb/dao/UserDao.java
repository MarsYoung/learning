package com.marsyoung.learning.web.ssb.dao;

import com.marsyoung.learning.web.ssb.entity.User;

public interface UserDao {

	void addUser(User user);

	void deleteUser(long userId);

	void updateUser(User user);

	User selectUser(long userId);

}
