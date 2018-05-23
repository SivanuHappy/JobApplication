/*
 * DAO implementation of an user object
 * Author: Anusha
 */
package com.jobapplication.dao;

import java.util.List;
import javax.validation.Valid;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jobapplication.entity.Applicant;
import com.jobapplication.entity.Employer;
import com.jobapplication.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	//Injecting the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean checkUserNameExists(@Valid User theUser) {
		// Check availability of user name during registration
		boolean result = true;
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> theQuery = currentSession.createQuery("from User where userName = :username", User.class);
		theQuery.setParameter("username", theUser.getUsername());
		List<User> user = theQuery.list();
		if (user.size() > 0) {
			result = false;
		}
		return result;
	}

	@Override
	public String saveUser(@Valid User theUser) {
		// Save user after registration
		Session currentSession = sessionFactory.getCurrentSession();
		if (theUser.getRole().equals("Applicant")) {
			Applicant tempApplicant = new Applicant();
			tempApplicant.setUser(theUser);
			tempApplicant.setFirstName(theUser.getFirstName());
			tempApplicant.setLastName(theUser.getLastName());
			tempApplicant.setUsername(theUser.getUsername());
			return currentSession.save(tempApplicant).getClass().getName();
		} else {
			Employer tempEmployer = new Employer();
			tempEmployer.setUser(theUser);
			tempEmployer.setFirstName(theUser.getFirstName());
			tempEmployer.setLastName(theUser.getLastName());
			tempEmployer.setUsername(theUser.getUsername());
			return currentSession.save(tempEmployer).getClass().getName();
		}
	}

	@Override
	public List<User> getUsers() {
		// get list of users
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> theQuery = currentSession.createQuery("from User", User.class);
		List<User> users = theQuery.getResultList();
		return users;
	}

	@Override
	public boolean validUser(@Valid User theUser) {
		// Check for valid user during login
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> theQuery = currentSession.createQuery("from User where username = :username and password = :password", User.class);
		theQuery.setParameter("username", theUser.getUsername());
		theQuery.setParameter("password", theUser.getPassword());
		List<User> user = theQuery.list();
		if (user.size() > 0) {
			return true;
		} else
			return false;
	}

	@Override
	public User getUserByUserName(User theUser) {
		// Get user object by user name
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> theQuery = currentSession.createQuery("from User where userName = :username", User.class);
		theQuery.setParameter("username", theUser.getUsername());
		List<User> user = theQuery.list();
		return user.get(0);
	}

	@Override
	public int getUser(int userId) {
		// get applicant or employer object based on userid
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> userQuery = currentSession.createQuery("from User where id = :id", User.class);
		userQuery.setParameter("id", userId);
		User user = userQuery.list().get(0);
		if (user.getRole().equals("Employer")) 
		{
			Query<Employer> theQuery = currentSession.createQuery("from Employer where user = :user", Employer.class);
			theQuery.setParameter("user", user);
			List<Employer> employer = theQuery.list();
			return employer.get(0).getId();
		} else {
			Query<Applicant> theQuery = currentSession.createQuery("from Applicant where user = :user", Applicant.class);
			theQuery.setParameter("user", user);
			List<Applicant> applicant = theQuery.list();
			return applicant.get(0).getId();
		}
	}

	@Override
	public User getUserById(int id) {
		// Get user object by user id
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> userQuery = currentSession.createQuery("from User where id = :id", User.class);
		userQuery.setParameter("id", id);
		List<User> user = userQuery.list();
		return user.size() > 0 ? user.get(0) : null;
	}
}
