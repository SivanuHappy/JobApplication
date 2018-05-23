/*
 * This controller handles registered employer functionalities like profile management and posting jobs
 * Author: Anusha, Xue
 */
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
		//This method retrieves the applicant information using applicant id
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Applicant> theQuery = currentSession.createQuery("from Applicant where id = :id",Applicant.class);
		theQuery.setParameter("id", appId);
		List<Applicant> applicant = theQuery.list();
		return applicant.get(0);
	}

	@Override
	public List<Job> searchByJobString(String searchString) {
		//This methold will allow the applicant to search for jobs
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria cr = currentSession.createCriteria(Job.class);
		cr.add(Restrictions.ilike("title", searchString));
		List<Job> theJobs = cr.list();
		return theJobs;
	}

	@Override
	public List<Job> getByJobId(int theId) {
		//This method lists the jobs based on the search criteria
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Job> jobQuery = currentSession.createQuery("from Job where id= :id", Job.class);
		jobQuery.setParameter("id", theId);
		List<Job> jobs = jobQuery.getResultList();
		return jobs;
	}

	@Override
	public boolean updateApplicant(Applicant theApplicant) {
		//This method allows the applicant to update the profile
		boolean updateSuccess = false;
		Session currentSession = sessionFactory.getCurrentSession();
		User user = currentSession.get(User.class, theApplicant.getUser().getId());
		theApplicant.setUser(user);
		try {
		currentSession.saveOrUpdate(theApplicant);
		updateSuccess = true;
		}
		catch (Exception e) {
			throw e;
		}
		return updateSuccess;
	}
}
