package com.jobapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobapplication.dao.ApplicantDAO;
import com.jobapplication.entity.Applicant;
import com.jobapplication.entity.Job;

@Service
@Transactional
public class ApplicantServiceImpl implements ApplicantService {
	
	@Autowired
	private ApplicantDAO applicantDAO;
	
	@Override
	@Transactional
	public Applicant getUser(int userId) {
		return applicantDAO.getUser(userId);
	}

	@Override
	@Transactional
	public List<Job> searchByJobString(String searchString) {
		return applicantDAO.searchByJobString(searchString);
	}

	@Override
	@Transactional
	public List<Job> getByJobId(int theId) {
		return applicantDAO.getByJobId(theId);
	}

	@Override
	@Transactional
	public void updateApplicant(Applicant theApplicant) {
		applicantDAO.updateApplicant(theApplicant);
	}

}
