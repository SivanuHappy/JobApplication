package com.jobapplication.dao;

import java.util.List;
import javax.validation.Valid;
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
		//get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		//System.out.println("user id from get user method: " + userId);
		//now retrieve record from database using primary key
		//Query<User> userQuery = currentSession.createQuery("from User where id = :id", User.class);
		//userQuery.setParameter("id", userId);
		//User user = userQuery.list().get(0);
		Query<Employer> theQuery = currentSession.createQuery("from Employer where id = :id",Employer.class);
		theQuery.setParameter("id", empId);
		List<Employer> employer = theQuery.list();
		System.out.println("from get method Employer details: " + employer.get(0));
		return employer.get(0);
	}

	@Override
	public void updateEmployer(Employer theEmployer) {
		System.out.println(theEmployer);
		//get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		User user = currentSession.get(User.class, theEmployer.getUser().getId());
		Query<Job> theQuery = currentSession.createQuery("from Job where employer = :employer",Job.class);
		theQuery.setParameter("employer", theEmployer);
		List<Job> theJob = theQuery.list();
		theEmployer.setUser(user);
		theEmployer.setJobs(theJob);
		currentSession.saveOrUpdate(theEmployer);
	}

	@Override
	public void saveJob(Job theJob) {
		Session currentSession = sessionFactory.getCurrentSession();
		Employer employer = currentSession.get(Employer.class, theJob.getEmployer().getId());
		employer.addJobs(theJob);
		System.out.println(employer.getUsername());
		currentSession.save(employer);
	}

	@Override
	public List<Job> getJobs(int theId) {
		//get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		/*Query<Employer> theQuery = currentSession.createQuery("from Employer where id= :id", Employer.class);
		theQuery.setParameter("id", theId);
		List<Employer> employer = theQuery.list();
		//int empId = employer.get(0).getId();
		System.out.println("the employee id: " + empId + " " + "the user id is "+ " " + theId);*/
		Query<Job> jobQuery = currentSession.createQuery("from Job where employerid= :employerid", Job.class);
		System.out.println("I am getJobs list: " + theId);
		jobQuery.setParameter("employerid", theId);
		//execute query and get result list
		List<Job> jobs = jobQuery.getResultList();
		return jobs;
	}
}
