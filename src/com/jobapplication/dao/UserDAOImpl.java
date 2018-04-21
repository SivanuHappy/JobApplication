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

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	// Check availability of username during registration
	@Override
	public boolean checkUserNameExists(@Valid User theUser) {
		boolean result = true;
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// create a query
		Query<User> theQuery = currentSession.createQuery("from User where userName = :username", User.class);
		theQuery.setParameter("username", theUser.getUsername());
		List<User> user = theQuery.list();
		if (user.size() > 0) {
			result = false;
		}
		return result;
	}

	// Save user after registration
	@Override
	public String saveUser(@Valid User theUser) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		System.out.println("The role is:" + theUser.getRole());
		if (theUser.getRole().equals("Applicant")) {
			Applicant tempApplicant = new Applicant();
			// System.out.println("From user table Applicant name: " +
			// theUser.getFirstName() + " " + theUser.getUsername());
			// System.out.println("From applicant table Applicant name: " +
			// tempApplicant.getUser());
			tempApplicant.setUser(theUser);
			tempApplicant.setFirstName(theUser.getFirstName());
			tempApplicant.setLastName(theUser.getLastName());
			tempApplicant.setUsername(theUser.getUsername());
			return currentSession.save(tempApplicant).getClass().getName();
		} else {
			Employer tempEmployer = new Employer();
			System.out
					.println("From user table Employer name: " + theUser.getFirstName() + " " + theUser.getUsername());
			System.out.println("From employer table employer name: " + tempEmployer.getUser());
			tempEmployer.setUser(theUser);
			tempEmployer.setFirstName(theUser.getFirstName());
			tempEmployer.setLastName(theUser.getLastName());
			tempEmployer.setUsername(theUser.getUsername());
			return currentSession.save(tempEmployer).getClass().getName();
		}
	}

	@Override
	public List<User> getUsers() {
		// get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// create a query
		Query<User> theQuery = currentSession.createQuery("from User", User.class);
		// execute query and get result list
		List<User> users = theQuery.getResultList();
		return users;
	}

	@Override
	public boolean validUser(@Valid User theUser) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// create a query
		Query<User> theQuery = currentSession
				.createQuery("from User where username = :username and password = :password", User.class);
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
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// create a query
		Query<User> theQuery = currentSession.createQuery("from User where userName = :username", User.class);
		theQuery.setParameter("username", theUser.getUsername());
		List<User> user = theQuery.list();
		return user.get(0);
	}

	@Override
	public int getUser(int userId) {
		// get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		System.out.println("user id from get user method: " + userId);
		// now retrieve record from database using primary key
		Query<User> userQuery = currentSession.createQuery("from User where id = :id", User.class);
		userQuery.setParameter("id", userId);
		User user = userQuery.list().get(0);
		System.out.println(user.getRole());
		if (user.getRole().equals("Employer")) 
		{
			Query<Employer> theQuery = currentSession.createQuery("from Employer where user = :user", Employer.class);
			theQuery.setParameter("user", user);
			System.out.println(theQuery);
			List<Employer> employer = theQuery.list();
			System.out.println("from get method Employer details: " + employer.get(0));
			return employer.get(0).getId();
		} else {
			Query<Applicant> theQuery = currentSession.createQuery("from Applicant where user = :user", Applicant.class);
			theQuery.setParameter("user", user);
			List<Applicant> applicant = theQuery.list();
			System.out.println("from get method applicant details: " + applicant.get(0));
			return applicant.get(0).getId();
		}
	}

	@Override
	public User getUserById(int id) {
		// get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> userQuery = currentSession.createQuery("from User where id = :id", User.class);
		userQuery.setParameter("id", id);
		List<User> user = userQuery.list();
		return user.size() > 0 ? user.get(0) : null;
	}
}
