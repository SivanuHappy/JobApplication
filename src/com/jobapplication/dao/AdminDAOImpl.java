package com.jobapplication.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobapplication.entity.Admin;
import com.jobapplication.entity.User;
import com.jobapplication.service.AdminService;

@Repository
public class AdminDAOImpl implements AdminDAO{

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean loginCheck(Admin theAdmin) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// create a query
		Query<Admin> theQuery = currentSession
				.createQuery("from Admin where username = :username and password = :password", Admin.class);
		theQuery.setParameter("username", theAdmin.getUsername());
		theQuery.setParameter("password", theAdmin.getPassword());
		List<Admin> user = theQuery.list();
		if (user.size() > 0) {
			return true;
		} else
			return false;
	}

}
