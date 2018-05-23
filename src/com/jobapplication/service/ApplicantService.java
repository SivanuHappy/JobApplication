package com.jobapplication.service;

import java.util.List;

import com.jobapplication.entity.Applicant;
import com.jobapplication.entity.Job;


public interface ApplicantService {

	public Applicant getUser(int userId);

	public List<Job> searchByJobString(String searchString);

	public List<Job> getByJobId(int theId);

	public boolean updateApplicant(Applicant theApplicant);
	
}
