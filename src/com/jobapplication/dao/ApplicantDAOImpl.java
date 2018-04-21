package com.jobapplication.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jobapplication.entity.Applicant;
import com.jobapplication.entity.Job;
import com.jobapplication.entity.User;

@Repository
public class ApplicantDAOImpl implements ApplicantDAO {

	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Applicant getUser(int appId) {
		//get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		//System.out.println("user id from get user method: " + userId);
		//now retrieve record from database using primary key
		//Query<User> userQuery = currentSession.createQuery("from User where id = :id", User.class);
		//userQuery.setParameter("id", userId);
		//User user = userQuery.list().get(0);
		Query<Applicant> theQuery = currentSession.createQuery("from Applicant where id = :id",Applicant.class);
		theQuery.setParameter("id", appId);
		List<Applicant> applicant = theQuery.list();
		System.out.println("from get method applicant details: " + applicant.get(0));
		return applicant.get(0);
	}

	@Override
	public List<Job> searchByJobString(String searchString) {
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria cr = currentSession.createCriteria(Job.class);
		System.out.println(searchString);
		cr.add(Restrictions.ilike("title", searchString));
		//System.out.println(cr.list());	
		List<Job> theJobs = cr.list();
		//System.out.println(cr.list());
		return theJobs;
	}

	@Override
	public List<Job> getByJobId(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Job> jobQuery = currentSession.createQuery("from Job where id= :id", Job.class);
		//System.out.println("I am getJobs list: " + theId);
		jobQuery.setParameter("id", theId);
		//execute query and get result list
		List<Job> jobs = jobQuery.getResultList();
		return jobs;
	}

	@Override
	public void updateApplicant(Applicant theApplicant) {
		//get the current Hibernate session
		System.out.println(theApplicant);
				Session currentSession = sessionFactory.getCurrentSession();
				System.out.println(theApplicant.getUser().getId());
				User user = currentSession.get(User.class, theApplicant.getUser().getId());
				System.out.println(user);
				theApplicant.setUser(user);
				currentSession.saveOrUpdate(theApplicant);
	}
}
