package com.jobapplication.dao;

import java.util.List;

import com.jobapplication.entity.Employer;
import com.jobapplication.entity.Job;
import com.jobapplication.entity.User;

public interface EmployerDAO {
	public Employer getUser(int userId);
	public void updateEmployer(Employer theEmployer);
	public void saveJob(Job theJob);
	public List<Job> getJobs(int theId);
}
