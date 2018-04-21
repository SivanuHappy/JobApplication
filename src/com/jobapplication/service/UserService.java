package com.jobapplication.service;

import java.util.List;

import javax.validation.Valid;
import com.jobapplication.entity.User;

public interface UserService {
	public List<User> getUsers();
	public String saveUser(@Valid User theUser);
	public boolean validUser(@Valid User theUser);
	public boolean checkUserNameExists(@Valid User theUser);
	public User getUserByUserName(User theUser);
	public int getUser(int id);
	public User getUserById(int id);
}
