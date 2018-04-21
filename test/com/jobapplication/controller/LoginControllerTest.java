package com.jobapplication.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.jobapplication.entity.User;
import com.jobapplication.service.UserService;

public class LoginControllerTest {
	private LoginController loginController;
	private UserService userService;
	private User newUser1, newValidUser, user;
	//private String userName = "John123";
	private String password = "123456";
	private int id;
	BindingResult theBindingResult;
	ModelMap theModel;
	ModelAndView mav;


	@Before
	public void setup() {
		loginController = mock(LoginController.class);
		userService = mock(UserService.class);
		user = mock(User.class);
		theModel = new ExtendedModelMap();
	}	
	
	@Test
	public void testShowFormForLogin() {
		User user = new User();
		//Setting up mock environment
		when(loginController.showFormForLogin(theModel)).thenReturn("login-form");
		//Calling method in controller
		String viewName =loginController.showFormForLogin(theModel);
		theModel.addAttribute(user);
		// verify view page name
		Assert.assertNotNull(viewName);
		Assert.assertEquals("login-form", viewName);
		// verify user object
		User actualUser = (User) theModel.get("user");
		Assert.assertEquals(user, actualUser);
	}

	@Test
	public void testLoginValidUser() {
		User newUser1 = new User();
		newUser1.setUsername("John123");
		newUser1.setPassword(password);
		newUser1.setId(id);
		newValidUser= new User(newUser1.getUsername(),"John","Doe","Applicant","123456","123456");
		
		
		when(loginController.loginUser(newUser1)).thenReturn(mav);
		when(userService.validUser(newUser1)).thenReturn(true);
		when(userService.getUserByUserName(newUser1)).thenReturn(newValidUser);
		when(userService.getUser(newUser1.getId())).thenReturn(2);
		
		ModelAndView mav = loginController.loginUser(newUser1);
		boolean validUser = userService.validUser(newUser1);
		assertEquals(true, validUser);
		//assertFalse(checkUser);
		

		User validName = userService.getUserByUserName(newUser1);
		assertEquals(newValidUser, validName);
		
		int appId = userService.getUser(newUser1.getId());
	    assertEquals(2,appId);
		mav = new ModelAndView("homepage-applicant");
		assertEquals("homepage-applicant", mav.getViewName());
		
		mav.addObject("successmessage", "You registered successfully!!");
		assertEquals("You registered successfully!!", mav.getModel().get("successmessage"));
		
		mav.addObject("firstname", newValidUser.getFirstName());
        assertEquals("John", mav.getModel().get("firstname"));
        
        mav.addObject("id", appId);
        assertEquals(2, mav.getModel().get("id"));
        assertNotNull(mav);
		
		
	}

}
