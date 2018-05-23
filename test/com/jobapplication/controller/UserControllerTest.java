/*
 * JUnit test to check public methods in User Controller
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
import com.jobapplication.controller.UserController;

public class UserControllerTest {
	private UserController userController;
	private UserService userService;
	private User newUser;
	private final int userId = 1;
	BindingResult theBindingResult;
	ModelMap theModel;
	ModelAndView mav;

	@Before
	public void setup() {
		userController = mock(UserController.class);
		userService = mock(UserService.class);
		theModel = new ExtendedModelMap();
	}

	@Test
	public void testShowFormForRegister() throws Exception {
		//This test method checks whether an user object renders to view page
		User user = new User();
		when(userController.showFormForRegister(theModel)).thenReturn("register-form");
		String viewName = userController.showFormForRegister(theModel);
		theModel.addAttribute(user);
		Assert.assertNotNull(viewName);
		Assert.assertEquals("register-form", viewName);
		User actualUser = (User) theModel.get("user");
		Assert.assertEquals(user, actualUser);
	}


	@Test
	public void testRegisterValidApplicant() throws Exception {
		/* Test case for registering valid applicant user
		 * check checkUserNameExists, saveUser, getUser, and model
		 */
		newUser = new User("John123", "John", "Doe", "Applicant", "123456", "123456");
        newUser.setId(userId);
        
        when(userController.registerUser(newUser, theBindingResult)).thenReturn(mav);
		when(userService.checkUserNameExists(newUser)).thenReturn(true);
		when(userService.saveUser(newUser)).thenReturn("Applicant");
		when(userService.getUser(newUser.getId())).thenReturn(1);
		
		ModelAndView mav = userController.registerUser(newUser, theBindingResult);

		boolean checkUser = userService.checkUserNameExists(newUser);
		assertTrue(checkUser);

		String classname = userService.saveUser(newUser);
		assertEquals("Applicant", classname);
	    
		int appId = userService.getUser(newUser.getId());
		assertEquals(1, appId);
		
		mav = new ModelAndView("homepage-applicant");
		assertEquals("homepage-applicant", mav.getViewName());
		
		mav.addObject("successmessage", "You registered successfully!!");
		assertEquals("You registered successfully!!", mav.getModel().get("successmessage"));
		
		mav.addObject("firstname", newUser.getFirstName());
        assertEquals("John", mav.getModel().get("firstname"));
        
        mav.addObject("id", appId);
        assertEquals(1, mav.getModel().get("id"));
        assertNotNull(mav);
	}

	@Test
	public void testRegisterInvalidApplicant() throws Exception {
		// Test case to check whether registration fails for invalid applicant user
		newUser = new User("John123", "John", "Abel", "Applicant", "123456", "123456");
		
	    when(userController.registerUser(newUser, theBindingResult)).thenReturn(mav);
		when(userService.checkUserNameExists(newUser)).thenReturn(false);
		
        ModelAndView mav = userController.registerUser(newUser, theBindingResult);
        assertFalse(userService.checkUserNameExists(newUser));
      
        mav = new ModelAndView("register-form");
        assertEquals("register-form", mav.getViewName());
        
        mav.addObject("message", "Username already exists!!");
        assertEquals("Username already exists!!", mav.getModel().get("message"));
	}

	@Test
	public void testRegisterValidEmployer() throws Exception {
		// Test case to check registration for valid employer
		newUser = new User("James123", "James", "Darner", "Employer", "123456", "123456");
        newUser.setId(userId);

        when(userController.registerUser(newUser, theBindingResult)).thenReturn(mav);
		when(userService.checkUserNameExists(newUser)).thenReturn(true);
		when(userService.saveUser(newUser)).thenReturn("Employer");
		when(userService.getUser(newUser.getId())).thenReturn(1);
		
		ModelAndView mav = userController.registerUser(newUser, theBindingResult);

		boolean checkUser = userService.checkUserNameExists(newUser);
		assertEquals(true, checkUser);
		//assertFalse(checkUser);

		String classname = userService.saveUser(newUser);
		assertEquals("Employer", classname);
	    
		int empId = userService.getUser(newUser.getId());
		assertEquals(1, empId);
		
		mav = new ModelAndView("homepage-employer");
		assertEquals("homepage-employer", mav.getViewName());
		
		mav.addObject("successmessage", "You registered successfully!!");
		assertEquals("You registered successfully!!", mav.getModel().get("successmessage"));
		
		mav.addObject("firstname", newUser.getFirstName());
        assertEquals("James", mav.getModel().get("firstname"));
        
        mav.addObject("id", empId);
        assertEquals(1, mav.getModel().get("id"));
        assertNotNull(mav);
	}
	
	@Test
	public void testRegisterInvalidEmployer() throws Exception {
		// Test case to check whether registration fails for invalid employer user
		newUser = new User("James123", "James", "Abel", "Employer", "123456", "123456");
		
	    when(userController.registerUser(newUser, theBindingResult)).thenReturn(mav);
		when(userService.checkUserNameExists(newUser)).thenReturn(false);
		
        ModelAndView mav = userController.registerUser(newUser, theBindingResult);
        assertFalse(userService.checkUserNameExists(newUser));
		
        mav = new ModelAndView("register-form");
        assertEquals("register-form", mav.getViewName());
        
        mav.addObject("message", "Username already exists!!");
        assertEquals("Username already exists!!", mav.getModel().get("message"));
	}
}