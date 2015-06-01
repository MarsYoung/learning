package com.marsyoung.learning.web.ssb.mapper;

import com.marsyoung.learning.web.ssb.entity.User;

public interface UserMapper {

	void addUser(User user);

	void deleteUser(long userId);

	void updateUser(User user);

	User selectUser(long userId);

}
