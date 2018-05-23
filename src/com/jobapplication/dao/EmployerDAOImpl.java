/*
 * This controller handles registered employer functionalities like profile management and posting jobs
 * Author: Anusha, Shweta
 */
package com.jobapplication.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jobapplication.entity.Employer;
import com.jobapplication.entity.Job;
import com.jobapplication.entity.User;

@Repository
public class EmployerDAOImpl implements EmployerDAO {

	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Employer getUser(int empId) {
		// This method will retrieve the employer object by employee id
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Employer> theQuery = currentSession.createQuery("from Employer where id = :id",Employer.class);
		theQuery.setParameter("id", empId);
		List<Employer> employer = theQuery.list();
		return employer.get(0);
	}

	@Override
	public boolean updateEmployer(Employer theEmployer) {
		//This method is used to update the Employee information
		boolean success = false;
		Session currentSession = sessionFactory.getCurrentSession();
		User user = currentSession.get(User.class, theEmployer.getUser().getId());
		Query<Job> theQuery = currentSession.createQuery("from Job where employer = :employer",Job.class);
		theQuery.setParameter("employer", theEmployer);
		List<Job> theJob = theQuery.list();
		theEmployer.setUser(user);
		theEmployer.setJobs(theJob);
		try {
		currentSession.saveOrUpdate(theEmployer);
		success = true; 
		}
		catch (Exception e) {
			throw e;
		}
		return success;
	}

	@Override
	public boolean saveJob(Job theJob) {
		// This method allows the employer to create and save new jobs
		boolean success = true;
		Session currentSession = sessionFactory.getCurrentSession();
		Employer employer = currentSession.get(Employer.class, theJob.getEmployer().getId());
		employer.addJobs(theJob);
		try {
		currentSession.save(employer);
		success = true;
		}
		catch (Exception e) {
			throw e;
		}
		return success;
	}

	@Override
	public List<Job> getJobs(int theId) {
		//This method will list all the jobs created by the employer
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Job> jobQuery = currentSession.createQuery("from Job where employerid= :employerid", Job.class);
		jobQuery.setParameter("employerid", theId);
		List<Job> jobs = jobQuery.getResultList();
		return jobs;
	}
}
