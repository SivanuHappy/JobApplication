package com.jobapplication.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobapplication.dao.UserDAO;
import com.jobapplication.entity.Employer;
import com.jobapplication.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public List<User> getUsers(){
		return userDAO.getUsers();
	}

	@Override
	@Transactional
	public String saveUser(@Valid User theUser) {
		return userDAO.saveUser(theUser);
		
	}

	@Override
	@Transactional
	public boolean validUser(@Valid User theUser) {
		return userDAO.validUser(theUser);	
	}

	@Override
	@Transactional
	public boolean checkUserNameExists(@Valid User theUser) {
		return  userDAO.checkUserNameExists(theUser);
	}

	@Override
	@Transactional
	public User getUserByUserName(User theUser) {
		return userDAO.getUserByUserName(theUser);
	}

	@Override
	@Transactional
	public int getUser(int id) {
		return userDAO.getUser(id);
	}

	@Override
	@Transactional
	public User getUserById(int id) {
		return userDAO.getUserById(id);
	}
}
