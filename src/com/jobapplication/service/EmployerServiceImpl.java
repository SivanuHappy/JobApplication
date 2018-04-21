package com.jobapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobapplication.dao.EmployerDAO;
import com.jobapplication.entity.Employer;
import com.jobapplication.entity.Job;

@Service
@Transactional
public class EmployerServiceImpl implements EmployerService {
	
	@Autowired
	private EmployerDAO employerDAO;
	
	@Override
	@Transactional
	public Employer getUser(int userId) {
		return employerDAO.getUser(userId);
	}

	@Override
	@Transactional
	public void updateEmployer(Employer theEmployer) {
		employerDAO.updateEmployer(theEmployer);
	}

	@Override
	@Transactional
	public void saveJob(Job theJob) {
		employerDAO.saveJob(theJob);	
	}

	@Override
	@Transactional
	public List<Job> getJobs(int theId) {
	    return employerDAO.getJobs(theId);
	}
}
