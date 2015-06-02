package com.marsyoung.learning.web.ssh.dao;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
 * 实现了最基本的增删改查功能
 * 这是一个可以方便其它dao继承之后进行增删改查等操作的类
 * 
 */
/**
 * @author mshootingstar
 * 
 * @param <M>
 */
@Scope("prototype")
@Repository("baseDao")
public class BaseDao<M> {
	protected final Log log = LogFactory.getLog(getClass());
	String entityName = "";

	@Autowired
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Required
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * 构造方法,获得所要操作的entity，但是要保证dao是按照"表名+Dao"命名的
	 */
	BaseDao() {
		entityName = getClass().getSimpleName();
		entityName = StringUtils.substringBefore(entityName, "Dao");
		log.info(entityName);
	}

	/**
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<M> getAll() {
		Session session = this.getSessionFactory().openSession();
		List<M> list = session.createQuery("from " + entityName).list();
		session.close();
		return list;
	}

	/**
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public M get(int id) {
		Session session = this.getSessionFactory().openSession();
		M model = (M) session.createQuery("from " + entityName + " where id=" + id).uniqueResult();
		session.close();
		return model;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public M get(long id) {
		Session session = this.getSessionFactory().openSession();
		M model = (M) session.createQuery("from " + entityName + " where id=" + id).uniqueResult();
		session.close();
		return model;
	}

	public M getByUserName(String username) {
		Session session = this.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<M> list = session.createQuery("from " + entityName + " where username='" + username + "'").list();
		session.close();
		if (null != list && list.size() != 0) {
			M u = list.get(0);
			return u;
		} else {
			return null;
		}

	}
	/**
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<M> getAllByIDList(List<Integer> ids) {
		Session session = this.getSessionFactory().openSession();
		List<M> list;
		String queryStr="from " + entityName + " where id in (";
		for(Integer id:ids){
			queryStr+=id+",";
		}
		queryStr+=")";
		Query q = session.createQuery(queryStr);
		try {
			list = q.list();
			session.close();
		} catch (RuntimeException re) {
			throw re;
		}
		return list;
	}

	
	
	@Transactional
	public Long getTotalNum() {
		Session session = this.getSessionFactory().openSession();
		// return ((Integer)
		// (this.sessionFactory.openSession().createQuery("select count(*) from "+entityName).iterate().next())).intValue();
		Query q = session.createQuery("select count(*) from " + entityName);
		long n = (Long) (q.uniqueResult());
		session.close();
		return n;
	}

	@Transactional
	public Long getTotalNumByUsername(String username) {
		Session session = this.getSessionFactory().openSession();
		// return ((Integer)
		// (this.sessionFactory.openSession().createQuery("select count(*) from "+entityName).iterate().next())).intValue();
		Query q = session.createQuery("select count(*) from " + entityName + " where questioncommitterid='" + username
				+ "'");
		long n = (Long) (q.uniqueResult());
		session.close();
		return n;
	}

	/**
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<M> getCurrentPageListDesc(int pageOffSet, int pageSize) {
		Session session = this.getSessionFactory().openSession();
		List<M> list;
		Query q = session.createQuery("from " + entityName + " order by id desc");
		try {
			q.setFirstResult(pageOffSet);
			q.setMaxResults(pageSize);
			list = q.list();
			session.close();
		} catch (RuntimeException re) {
			throw re;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<M> getCurrentPageListByUsernameDesc(int pageOffSet, int pageSize, String username) {
		Session session = this.getSessionFactory().openSession();
		List<M> list;
		Query q = session.createQuery("from " + entityName + " where questioncommitterid='" + username
				+ "' order by id desc");
		try {
			q.setFirstResult(pageOffSet);
			q.setMaxResults(pageSize);
			list = q.list();
			session.close();
		} catch (RuntimeException re) {
			throw re;
		}
		return list;
	}

	/**
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<M> getCurrentPageList(int pageOffSet, int pageSize) {
		Session session = this.getSessionFactory().openSession();
		List<M> list;
		Query q = session.createQuery("from " + entityName);
		try {
			q.setFirstResult(pageOffSet);
			q.setMaxResults(pageSize);
			list = q.list();
			session.close();
		} catch (RuntimeException re) {
			throw re;
		}
		return list;
	}

	/**
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public M get(Class<M> entity, int id) {
		Session session = this.getSessionFactory().openSession();
		M model = (M) session.get(entity, id);
		session.close();
		return model;
	}

	/**
	 * 函数说明：通过id删除entity 疑问：这里不加事务不执行。 猜想：hibernate4把原来的template包装好的事务去掉了，需要自己加
	 * 但是奇怪的是，为什么get不加事务就可以呢？
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public void delete(Class<M> entity, long id) {
		Session session = this.getSessionFactory().openSession();
		;
		Transaction tx = session.beginTransaction();
		;
		try {
			M model = (M) session.get(entity, id);
			session.delete(model);
			tx.commit();
			session.close();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			// throw e;
		} finally {
			session.close();
		}

	}

	/**
	 * 函数说明：通过id del entity
	 */
	@Transactional
	public void delete(int id) {
		Session session = this.getSessionFactory().openSession();
		session.createQuery("delete from " + entityName + " where id=" + id).executeUpdate();
		session.close();
	}
	
	/**
	 * 函数说明：通过id del entity
	 */
	@Transactional
	public void delete(long id) {
		Session session = this.getSessionFactory().openSession();
		session.createQuery("delete from " + entityName + " where id=" + id).executeUpdate();
		session.close();
	}

	@Transactional
	public void delete(String username) {
		Session session = this.getSessionFactory().openSession();
		session.createQuery("delete from " + entityName + " where username='" + username+"'").executeUpdate();
		session.close();
	}

	/**
	 * 函数说明：存
	 */
	@Transactional
	public void save(M model) {
		Session session = this.getSessionFactory().openSession();
		try {
			session.save(model);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			// throw e;
		}
	}

	/**
	 * 函数说明：更新,没用啊。。。
	 */
	@Transactional
	public void update(M model, Class<M> entity, long l) {
		Session session = this.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			M dbmodel = (M) session.get(entity, l);
			dbmodel = model;
			session.update(dbmodel);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			// throw e;
		}
	}

}
