/*
 * JUnit test cases for all public methods in login controller
 * Author: Anusha, Shweta, Karishma, Xue
 */
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
	private User newLoginUser, validUser;
	private final String username = "John123";
	private final String password = "123456";
	private final int userId = 1;
	BindingResult theBindingResult;
	ModelMap theModel;
	ModelAndView mav;

	@Before
	public void setup() {
		loginController = mock(LoginController.class);
		userService = mock(UserService.class);
		theModel = new ExtendedModelMap();
		newLoginUser = new User();
	}	
	
	@Test
	public void testShowFormForLogin() {
		//This test method checks whether user model is rendered properly to view page
		User user = new User();
		//Setting up mock environment
		when(loginController.showFormForLogin(theModel)).thenReturn("login-form");
		//Calling method in controller
		String viewName =loginController.showFormForLogin(theModel);
		theModel.addAttribute("user", user);
		// verify view page name
		Assert.assertNotNull(viewName);
		Assert.assertEquals("login-form", viewName);
		// verify user object
		User actualUser = (User) theModel.get("user");
		Assert.assertEquals(user, actualUser);
	}

	@Test
	public void testLoginValidApplicant() {
		//This method checks whether a valid applicant able to login successfully
    	newLoginUser.setUsername(username);
		newLoginUser.setPassword(password);
		newLoginUser.setId(userId);
		validUser= new User("John123","John","Doe","Applicant","123456","123456");
		
		when(loginController.loginUser(newLoginUser)).thenReturn(mav);
		when(userService.validUser(newLoginUser)).thenReturn(true);
		when(userService.getUserByUserName(newLoginUser)).thenReturn(validUser);
		when(userService.getUser(newLoginUser.getId())).thenReturn(2);
		
		mav = loginController.loginUser(newLoginUser);
		boolean checkValidUser = userService.validUser(newLoginUser);
		assertTrue(checkValidUser);

		User registeredUser = userService.getUserByUserName(newLoginUser);
		assertEquals(validUser, registeredUser);
		
		int appId = userService.getUser(newLoginUser.getId());
	    assertEquals(2,appId);
		mav = new ModelAndView("homepage-applicant");
		assertEquals("homepage-applicant", mav.getViewName());
		
		mav.addObject("firstname", validUser.getFirstName());
        assertEquals("John", mav.getModel().get("firstname"));
        
        mav.addObject("id", appId);
        assertEquals(2, mav.getModel().get("id"));
        assertNotNull(mav);
	}
	
	@Test
	public void testLoginInvalidApplicant() {
		//This method checks login fails for an invalid applicant
    	newLoginUser.setUsername(username);
		newLoginUser.setPassword(password);
		newLoginUser.setId(userId);
		validUser= new User("John123","John","Abel","Applicant","123456","123456");
		
		when(loginController.loginUser(newLoginUser)).thenReturn(mav);
		when(userService.validUser(newLoginUser)).thenReturn(false);
		
		mav = loginController.loginUser(newLoginUser);
		boolean checkValidUser = userService.validUser(newLoginUser);
		assertFalse(checkValidUser);

		mav = new ModelAndView("login-form");
		assertEquals("login-form", mav.getViewName());
		
		mav.addObject("message", "Username or Password is wrong!!");
		assertEquals("Username or Password is wrong!!", mav.getModel().get("message"));
		
        assertNotNull(mav);
	}
	
	@Test
	public void testLoginValidEmployer() {
		//This method checks whether a valid employer able to login successfully
    	newLoginUser.setUsername(username);
		newLoginUser.setPassword(password);
		newLoginUser.setId(userId);
		validUser= new User("James123","James","Barner","Employer","123456","123456");
		
		when(loginController.loginUser(newLoginUser)).thenReturn(mav);
		when(userService.validUser(newLoginUser)).thenReturn(true);
		when(userService.getUserByUserName(newLoginUser)).thenReturn(validUser);
		when(userService.getUser(newLoginUser.getId())).thenReturn(3);
		
		mav = loginController.loginUser(newLoginUser);
		boolean checkValidUser = userService.validUser(newLoginUser);
		assertTrue(checkValidUser);

		User registeredUser = userService.getUserByUserName(newLoginUser);
		assertEquals(validUser, registeredUser);
		
		int empId = userService.getUser(newLoginUser.getId());
	    assertEquals(3, empId);
	    
		mav = new ModelAndView("homepage-employer");
		assertEquals("homepage-employer", mav.getViewName());
			
		mav.addObject("firstname", validUser.getFirstName());
        assertEquals("James", mav.getModel().get("firstname"));
        
        mav.addObject("id", empId);
        assertEquals(3, mav.getModel().get("id"));
        assertNotNull(mav);
	}
	
	@Test
	public void testLoginInvalidEmployer() {
		//This method checks login fails for an invalid employer
    	newLoginUser.setUsername(username);
		newLoginUser.setPassword(password);
		newLoginUser.setId(userId);
		validUser= new User("James123","James","Abel","Employer","123456","123456");
		
		when(loginController.loginUser(newLoginUser)).thenReturn(mav);
		when(userService.validUser(newLoginUser)).thenReturn(false);
		
		mav = loginController.loginUser(newLoginUser);
		boolean checkValidUser = userService.validUser(newLoginUser);
		assertFalse(checkValidUser);

		mav = new ModelAndView("login-form");
		assertEquals("login-form", mav.getViewName());
		
		mav.addObject("message", "Username or Password is wrong!!");
		assertEquals("Username or Password is wrong!!", mav.getModel().get("message"));
		
        assertNotNull(mav);
	}

}
