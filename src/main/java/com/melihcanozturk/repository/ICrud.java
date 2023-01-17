package com.melihcanozturk.repository;

import java.util.List;

import org.hibernate.Session;

import com.melihcanozturk.util.HibernateUtils;

public interface ICrud<T> {

	public void create(T entity);
	
	public void delete(long id);
	
	public void update(long id ,T entity);
	
	public List<T>listAll();
	
	public T find(long id);
	
	default Session databaseConnectionHibernate() {
		return HibernateUtils.getSessionFactory().openSession();
	}
	
}
