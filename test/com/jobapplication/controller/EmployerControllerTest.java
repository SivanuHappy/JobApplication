/*
 * JUnit to test public methods in Employment Controller
 * Author: Anusha, Shweta, Karishma, Xue
 */
package com.jobapplication.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.jobapplication.entity.Employer;
import com.jobapplication.entity.Job;
import com.jobapplication.entity.User;
import com.jobapplication.service.EmployerService;

public class EmployerControllerTest {
	private EmployerController employerController;
	private EmployerService employerService;
	private Employer getEmployer; 
	private User user;
	private Job job;
	private final int id = 1;
	private final int userId = 2;
	private final String userName = "James123";
	List<Job> jobs;
	BindingResult theBindingResult;
	ModelMap theModel;
	ModelAndView mav;

	@Before
	public void setup() {
		employerController = mock(EmployerController.class);
		employerService = mock(EmployerService.class);
		getEmployer = new Employer();
		user = new User();
		theModel = new ExtendedModelMap();
	}	

	@Test
	public void testShowUpdateProfile() {
		//This method checks whether employer information is displayed when My Profile is clicked
		getEmployer.setId(id);
		int empId = getEmployer.getId();
		jobs = null;
		Employer savedEmployer = new Employer(jobs,"James123","James","Barner","","","","");

		when(employerController.showUpdateProfile(empId, theModel)).thenReturn(mav);
		when(employerService.getUser(empId)).thenReturn(savedEmployer);

		mav = employerController.showUpdateProfile(empId, theModel);
		getEmployer = employerService.getUser(empId);
		theModel.addAttribute("employer", getEmployer);
		Employer validEmployer = (Employer) theModel.get("employer");
		assertEquals(savedEmployer, validEmployer);
		assertEquals(savedEmployer.getUsername(), validEmployer.getUsername());

		mav = new ModelAndView("profile-form-employer");
		assertEquals("profile-form-employer", mav.getViewName());

		user.setId(userId);
		user.setUsername(userName);
		getEmployer.setUser(user);
		mav.addObject("userId", getEmployer.getUser().getId());
		assertEquals(2, mav.getModelMap().get("userId"));

		mav.addObject("userName", getEmployer.getUser().getUsername());
		assertEquals("James123", mav.getModelMap().get("userName"));

		mav.addObject("id", empId);
		assertEquals(1, mav.getModelMap().get("id"));

		mav.addObject("firstname", getEmployer.getFirstName());
		assertEquals("James", mav.getModelMap().get("firstname"));
		assertNotNull(mav);
	}

	@Test
	public void testUpdateProfile() {
		//This test method is to check if employer information is updated properly
		jobs = null;
		Employer updateEmployer = new Employer(jobs,"James123","James","Barner","james123@gmail.com","6398445123","Google","Technical Recruiter");
		updateEmployer.setId(1);
		when(employerController.updateProfile(updateEmployer, theBindingResult)).thenReturn(mav);
		when(employerService.updateEmployer(updateEmployer)).thenReturn(true);

		mav = employerController.updateProfile(updateEmployer, theBindingResult);
		boolean checkUpdate = employerService.updateEmployer(updateEmployer);
		assertTrue(checkUpdate);

		mav = new  ModelAndView("profile-form-employer");
		assertEquals("profile-form-employer", mav.getViewName());

		mav.addObject("empmessage", "Employer account updated successfully!!");
		assertEquals("Employer account updated successfully!!", mav.getModelMap().get("empmessage"));

		mav.addObject("id", updateEmployer.getId());
		assertEquals(1, mav.getModelMap().get("id"));

		mav.addObject("firstname", updateEmployer.getFirstName());
		assertEquals("James", mav.getModelMap().get("firstname"));
		assertNotNull(mav);
	}

	@Test
	public void testShowFormForAddJob() {
		//This method checks if job model is rendered properly to the view page
		job = new Job();
		getEmployer.setId(id);
		int empId = getEmployer.getId();
		when(employerController.showFormForAddJob(empId, theModel)).thenReturn(mav);

		mav = employerController.showFormForAddJob(empId, theModel);
		theModel.addAttribute("job", job);
		Job newJob = (Job) theModel.get("job");
		assertEquals(job, newJob);

		mav = new ModelAndView("job-form");
		assertEquals("job-form", mav.getViewName());
		
		mav.addObject("empId",empId);
		assertEquals(1, mav.getModelMap().get("empId"));		
		assertNotNull(mav);
	}
	
	@Test
	public void testListJobs(){
		//This test method checks whether list of jobs is displayed properly to an employer
		Job job1 = new Job("Developer", "Google", "10000.00", "Java", "LA", "CA");
		Job job2 = new Job("Tester", "Amazon", "10000.00", "Selenium", "Seattle", "Washington");
		Employer getEmployer = new Employer(jobs,"James123","James","Barner","james123@gmail.com","6398445123","Google","Technical Recruiter");
		getEmployer.setId(id);
		int empId = getEmployer.getId();
		
		when(employerController.listJobs(empId, theModel)).thenReturn("list-jobs");
		when(employerService.getJobs(empId)).thenReturn(Arrays.asList(job1, job2));
		when(employerService.getUser(empId)).thenReturn(getEmployer);
		
		String viewName = employerController.listJobs(empId, theModel);
		
		List<Job> theJobs = employerService.getJobs(empId);	
		theModel.addAttribute(theJobs);
		assertEquals(2, theJobs.size());
		assertNotNull(theJobs);
		assertEquals(Arrays.asList(job1, job2), theJobs);
		
		theModel.addAttribute("id", empId);
		int checkId = (Integer) theModel.get("id");
		assertEquals(1, checkId);
		
		theModel.addAttribute("firstname", getEmployer.getFirstName());
		String firstName = (String) theModel.get("firstname");
		assertEquals("James", firstName);
		
		Assert.assertNotNull(viewName);
		Assert.assertEquals("list-jobs", viewName);
	}
	
	@Test
	public void testAddJob() {
		//This method checks whether an employer is able to add a job and renders to view page
		Job theJob = new Job("Developer", "Google", "10000.00", "Java", "LA", "CA");
		Job otherJob = new Job("Tester", "Amazon", "10000.00", "Selenium", "Seattle", "Washington");
		getEmployer.addJobs(theJob);
		getEmployer.addJobs(otherJob);
		List<Job> jobs = getEmployer.getJobs();
		getEmployer.setId(id);
		int empId = getEmployer.getId();
		theJob.setEmployer(getEmployer);
		
		when(employerController.addJob(theJob, theBindingResult)).thenReturn(mav);
		when(employerService.saveJob(theJob)).thenReturn(true);
		when(employerService.getJobs(empId)).thenReturn(jobs);
		
		mav = employerController.addJob(theJob, theBindingResult);
		int checkId = theJob.getEmployer().getId();
		assertEquals(1, checkId);
		
		boolean checkSave = employerService.saveJob(theJob);
		assertTrue(checkSave);
		
		mav = new ModelAndView("list-jobs");
		assertEquals("list-jobs", mav.getViewName());
		
		List<Job> theJobs = employerService.getJobs(empId);
		mav.addObject("job", theJobs);
		assertEquals(2, theJobs.size());
		assertEquals("Developer", jobs.get(0).getTitle());
		assertEquals("Tester", jobs.get(1).getTitle());
		assertEquals(Arrays.asList(theJob, otherJob), theJobs);
		
		mav.addObject("id",checkId);
		assertEquals(1, mav.getModelMap().get("id"));
		assertNotNull(mav);
	}
	
	@Test
	public void testGetHomePage(){
		//This method checks whether an employer can reach his/her homepage with attributes
		getEmployer.setId(id);
		int empId = getEmployer.getId();
		getEmployer.setFirstName("James");
		String firstname = getEmployer.getFirstName();
		
		when(employerController.getHomePage(empId, firstname)).thenReturn(mav);
		
		mav = employerController.getHomePage(empId, firstname);
		mav = new ModelAndView("homepage-employer");
		assertEquals("homepage-employer", mav.getViewName());
		
		mav.addObject("id", empId);
		assertEquals(1, mav.getModelMap().get("id"));
		
		mav.addObject("firstname", firstname);
		assertEquals("James", mav.getModelMap().get("firstname"));
	}
	
	@Test
	public void testLogout() {
		//This checks whether an employer can terminate session and logout successfully
		HttpServletRequest request = null;
		when(employerController.logout(request)).thenReturn("redirect:../login/showFormForLogin");
		
		String viewName = employerController.logout(request);
		Assert.assertNotNull(viewName);
		Assert.assertEquals("redirect:../login/showFormForLogin", viewName);
	}

}
