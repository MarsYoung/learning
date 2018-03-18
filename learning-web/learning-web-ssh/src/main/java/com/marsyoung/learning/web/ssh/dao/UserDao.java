package com.marsyoung.learning.web.ssh.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.marsyoung.learning.web.ssh.entity.User;

@Repository("userDao")
public class UserDao extends BaseDao<User>{

	public void addUser(User user){
		this.save(user);
	};

	public void deleteUser(long userId){
		this.delete(userId);
	};

	public void updateUser(User user){
		Session session = this.getSessionFactory().openSession();
		Query q = session.createQuery("update " + entityName + " set username='"+user.getUsername() +"',password='"+user.getPassword()+"' where id=" + user.getId());
		q.executeUpdate();
		session.close();
	};

	public User selectUser(long userId){
		return this.get(userId);
	};

}
