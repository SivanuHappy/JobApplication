package com.jobapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobapplication.dao.AdminDAO;
import com.jobapplication.dao.UserDAO;
import com.jobapplication.entity.Admin;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDAO adminDAO;
	
	@Override
	@Transactional
	public boolean loginCheck(Admin theAdmin) {
		return adminDAO.loginCheck(theAdmin);
	}

}
