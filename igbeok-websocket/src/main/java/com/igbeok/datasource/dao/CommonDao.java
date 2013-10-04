package com.igbeok.datasource.dao;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.SimpleHibernateDao;

import com.igbeok.datasource.entity.Item;

@Repository
public class CommonDao extends SimpleHibernateDao<Item, Long> {

	@Autowired
	private SessionFactory sessionFactory;
	
	@PostConstruct
	private void setSuperSessionFactory() {
		super.setSessionFactory(sessionFactory);
	}
}
