package com.jobapplication.service;

import java.util.List;

import com.jobapplication.entity.Employer;
import com.jobapplication.entity.Job;

public interface EmployerService {

	public Employer getUser(int userId);

	public boolean updateEmployer(Employer theEmployer);

	public boolean saveJob(Job theJob);

	public List<Job> getJobs(int theId);
	
}
