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

import java.util.Arrays;
import java.util.List;

import com.jobapplication.entity.User;
import com.jobapplication.service.UserService;
import com.jobapplication.controller.UserController;

public class UserControllerTest {
	private UserController userController;
	private UserService userService;
	private User user;
	private final int id = 1;
	BindingResult theBindingResult;
	ModelMap theModel;
	ModelAndView mav;

	@Before
	public void setup() {
		
		userController = mock(UserController.class);
		userService = mock(UserService.class);
		user = mock(User.class);
		theModel = new ExtendedModelMap();
	}

	@Test
	public void testShowFormForRegister() throws Exception {
		User user = new User();
		//Setting up mock environment
		when(userController.showFormForRegister(theModel)).thenReturn("register-form");
		//Calling method in controller
		String viewName = userController.showFormForRegister(theModel);
		theModel.addAttribute(user);
		// verify view page name
		Assert.assertNotNull(viewName);
		Assert.assertEquals("register-form", viewName);
		// verify user object
		User actualUser = (User) theModel.get("user");
		Assert.assertEquals(user, actualUser);
	}

	/* Not needed for now */
	/*@Test
	public void testListUsers() throws Exception {
		// Mock data
		User newUser1 = new User("us", "fq", "f2", "Applicant", "123456", "123456");
		User newUser2 = new User("is", "ggq", "df2", "Applicant", "123456", "123456");
		// Setting up mock environment
		when(userController.listUsers(theModel)).thenReturn("list-users");
		when(userService.getUsers()).thenReturn(Arrays.asList(newUser1, newUser2));
		// Calling method in controller and service
		String listUsers = userController.listUsers(theModel);
		List<User> theUsers = userService.getUsers();
		// Assert view page
		assertNotNull(listUsers);
		Assert.assertEquals("list-users", listUsers);
		// Assert model
		theModel.addAttribute(theUsers);
		assertEquals(2, theUsers.size());
		assertNotNull(theUsers);
		assertEquals(Arrays.asList(newUser1, newUser2), theUsers);
	}*/

	/* Test case for registering valid applicant user
	 * check checkUserNameExists, saveUser, getUser, and finally model
	 */
	@Test
	public void testRegisterValidApplicant() throws Exception {
		// Mock data
		User newUser1 = new User("John123", "John", "Doe", "Applicant", "123456", "123456");
        newUser1.setId(id);
        
        when(userController.registerUser(newUser1, theBindingResult)).thenReturn(mav);
		when(userService.checkUserNameExists(newUser1)).thenReturn(true);
		when(userService.saveUser(newUser1)).thenReturn("Applicant");
		when(userService.getUser(1)).thenReturn(2);
		
		ModelAndView mav = userController.registerUser(newUser1, theBindingResult);

		boolean checkUser = userService.checkUserNameExists(newUser1);
		assertEquals(true, checkUser);
		//assertFalse(checkUser);

		String classname = userService.saveUser(newUser1);
		assertEquals("Applicant", classname);
	    
		int empId = userService.getUser(id);
		assertEquals(2, empId);
		
		mav = new ModelAndView("homepage-applicant");
		assertEquals("homepage-applicant", mav.getViewName());
		
		mav.addObject("successmessage", "You registered successfully!!");
		assertEquals("You registered successfully!!", mav.getModel().get("successmessage"));
		
		mav.addObject("firstname", newUser1.getFirstName());
        assertEquals("John", mav.getModel().get("firstname"));
        
        mav.addObject("id", empId);
        assertEquals(2, mav.getModel().get("id"));
        assertNotNull(mav);
	}

	@Test
	public void testRegisterInvalidApplicant() throws Exception {
		// Mock data with same username
		User newUser1 = new User("John123", "John", "Doe", "Applicant", "123456", "123456");
		User newUser2 = new User("John123", "John", "Abel", "Applicant", "123456", "123456");
		
		// Setting up mock environment
	    when(userController.registerUser(newUser1, theBindingResult)).thenReturn(mav);
		when(userService.checkUserNameExists(newUser1)).thenReturn(true);
		when(userService.saveUser(newUser1)).thenReturn("Applicant");
		
		
        ModelAndView mav1 = userController.registerUser(newUser1, theBindingResult);
        boolean checkUser1 = userService.checkUserNameExists(newUser1);
        String className1 = userService.saveUser(newUser1);
		
	    when(userController.registerUser(newUser2, theBindingResult)).thenReturn(mav);
		when(userService.checkUserNameExists(newUser2)).thenReturn(false);
        //Assert to check username availability
		
        ModelAndView mav2 = userController.registerUser(newUser2, theBindingResult);
        assertFalse(userService.checkUserNameExists(newUser2));
        
        mav = new ModelAndView("register-form");
        assertEquals("register-form", mav.getViewName());
        
        mav.addObject("message", "Username already exists!!");
        assertEquals("Username already exists!!", mav.getModel().get("message"));
	}

	@Test
	public void testRegisterValidEmployer() throws Exception {
		// Mock data
		User newUser1 = new User("James123", "James", "Darner", "Employer", "123456", "123456");
        newUser1.setId(id);
        
        when(userController.registerUser(newUser1, theBindingResult)).thenReturn(mav);
		when(userService.checkUserNameExists(newUser1)).thenReturn(true);
		when(userService.saveUser(newUser1)).thenReturn("Employer");
		when(userService.getUser(1)).thenReturn(3);
		
		ModelAndView mav = userController.registerUser(newUser1, theBindingResult);

		boolean checkUser = userService.checkUserNameExists(newUser1);
		assertEquals(true, checkUser);
		//assertFalse(checkUser);

		String classname = userService.saveUser(newUser1);
		assertEquals("Employer", classname);
	    
		int empId = userService.getUser(id);
		assertEquals(3, empId);
		
		mav = new ModelAndView("homepage-employer");
		assertEquals("homepage-employer", mav.getViewName());
		
		mav.addObject("successmessage", "You registered successfully!!");
		assertEquals("You registered successfully!!", mav.getModel().get("successmessage"));
		
		mav.addObject("firstname", newUser1.getFirstName());
        assertEquals("James", mav.getModel().get("firstname"));
        
        mav.addObject("id", empId);
        assertEquals(3, mav.getModel().get("id"));
        assertNotNull(mav);
		
	}
	
	@Test
	public void testRegisterInvalidEmployer() throws Exception {
		// Mock data with same username
		User newUser1 = new User("James123", "James", "Darner", "Employer", "123456", "123456");
		User newUser2 = new User("James123", "James", "Abel", "Employer", "123456", "123456");
		
		// Setting up mock environment
	    when(userController.registerUser(newUser1, theBindingResult)).thenReturn(mav);
		when(userService.checkUserNameExists(newUser1)).thenReturn(true);
		when(userService.saveUser(newUser1)).thenReturn("Employer");
		
		
        ModelAndView mav1 = userController.registerUser(newUser1, theBindingResult);
        boolean checkUser1 = userService.checkUserNameExists(newUser1);
        String className1 = userService.saveUser(newUser1);
		
	    when(userController.registerUser(newUser2, theBindingResult)).thenReturn(mav);
		when(userService.checkUserNameExists(newUser2)).thenReturn(false);
        //Assert to check username availability
		
        ModelAndView mav2 = userController.registerUser(newUser2, theBindingResult);
        assertFalse(userService.checkUserNameExists(newUser2));
		
        mav = new ModelAndView("register-form");
        assertEquals("register-form", mav.getViewName());
        
        mav.addObject("message", "Username already exists!!");
        assertEquals("Username already exists!!", mav.getModel().get("message"));
	}
}