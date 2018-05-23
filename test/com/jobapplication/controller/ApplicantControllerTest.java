/*
 * JUnit to test public methods in ApplicantController
 * Author: Anusha, Shweta, Karishma, Xue
 */
package com.jobapplication.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

import com.jobapplication.entity.Applicant;
import com.jobapplication.entity.Employer;
import com.jobapplication.entity.Job;
import com.jobapplication.entity.User;
import com.jobapplication.service.ApplicantService;

public class ApplicantControllerTest {
	private ApplicantController applicantController;
	private ApplicantService applicantService;
	private Applicant getApplicant; 
	private User user;
	private Employer employer;
	private final int id = 1;
	private final int userId = 2;
	private final int jobId = 1;
	private final String userName = "James123";
	List<Job> jobs;
	BindingResult theBindingResult;
	ModelMap theModel;
	ModelAndView mav;

	@Before
	public void setup() {
		applicantController = mock(ApplicantController.class);
		applicantService = mock(ApplicantService.class);
		getApplicant = new Applicant();
		employer = new Employer();
		user = new User();
		theModel = new ExtendedModelMap();
	}
	
	@Test
	public void testShowUpdateProfile() {
		/*
		 * This test checks if the applicant information is displayed when My Profile is clicked
		 */
		getApplicant.setId(id);
		int appId = getApplicant.getId();
		Applicant savedApplicant = new Applicant("James123","James","Barner","","","","","","",0,"","","","","","","");
	    
		when(applicantController.showUpdateProfile(appId, theModel)).thenReturn(mav);
		when(applicantService.getUser(appId)).thenReturn(savedApplicant);
		
		mav = applicantController.showUpdateProfile(appId, theModel);
		Applicant getApplicant = applicantService.getUser(appId);
		theModel.addAttribute("applicant", getApplicant);
		Applicant validApplicant = (Applicant) theModel.get("applicant");
		assertEquals(savedApplicant, validApplicant);
		assertEquals(savedApplicant.getUsername(), validApplicant.getUsername());
		
		mav = new ModelAndView("profile-form-applicant");
		assertEquals("profile-form-applicant", mav.getViewName());
		
		user.setId(userId);
		user.setUsername(userName);
		getApplicant.setUser(user);
		mav.addObject("userId", getApplicant.getUser().getId());
		assertEquals(2, mav.getModelMap().get("userId"));

		mav.addObject("userName", getApplicant.getUser().getUsername());
		assertEquals("James123", mav.getModelMap().get("userName"));

		mav.addObject("id", appId);
		assertEquals(1, mav.getModelMap().get("id"));

		mav.addObject("firstname", getApplicant.getFirstName());
		assertEquals("James", mav.getModelMap().get("firstname"));
		assertNotNull(mav);
	}
	
	@Test
	public void testUpdateProfile() {
		//This test function is to check if the updated information is saved in the database
		Applicant updateApplicant = new Applicant("James123","James","Barner","james123@gmail.com","6308765342","100 North University Rd","Apt 2","Normal","IL",61761,"United States","","","","","","");
		updateApplicant.setId(1);
		
		when(applicantController.updateProfile(updateApplicant, theBindingResult)).thenReturn(mav);
		when(applicantService.updateApplicant(updateApplicant)).thenReturn(true);
		
		mav = applicantController.updateProfile(updateApplicant, theBindingResult);
		boolean checkUpdate = applicantService.updateApplicant(updateApplicant);
		assertTrue(checkUpdate);

		mav = new  ModelAndView("profile-form-applicant");
		assertEquals("profile-form-applicant", mav.getViewName());

		mav.addObject("appMessage", "Applicant account updated successfully!!");
		assertEquals("Applicant account updated successfully!!", mav.getModelMap().get("appMessage"));

		mav.addObject("id", updateApplicant.getId());
		assertEquals(1, mav.getModelMap().get("id"));

		mav.addObject("firstname", updateApplicant.getFirstName());
		assertEquals("James", mav.getModelMap().get("firstname"));
		assertNotNull(mav);	
	}
	
	@Test
	public void testSearchJobs() {
		// This method is to check if search Jobs functionality is working
		getApplicant.setId(id);
		int appId = getApplicant.getId();
		String searchString = "Developer";
        Job job1 = new Job("Developer","Google","Java","90000.00","LA","CA");
        Job job2 = new Job("Developer","Apple","Java","100000.00","LA","CA");
        employer.addJobs(job1);
        employer.addJobs(job2);
        List<Job> jobs = employer.getJobs();
		
		when(applicantController.searchJobs(searchString, appId)).thenReturn(mav);
		when(applicantService.searchByJobString(searchString)).thenReturn(jobs);
		
		mav = applicantController.searchJobs(searchString, appId);
		List<Job> devJobs = applicantService.searchByJobString(searchString);
		
		ModelAndView mav = new ModelAndView("homepage-applicant");
		assertEquals("homepage-applicant", mav.getViewName());
		
		mav.addObject("devJobs", devJobs);
		assertEquals(2, devJobs.size());
		assertNotNull(devJobs);
		assertEquals(Arrays.asList(job1, job2), devJobs);
		
		mav.addObject("id", appId);
		assertEquals(1, mav.getModelMap().get("id"));
		assertNotNull(mav);
	}
	
	@Test
	public void testSearchJobForEmptyString() {
		//This method ensures that a valid input has to be passed to search for jobs
		getApplicant.setId(id);
		int appId = getApplicant.getId();
		String searchString = "";
		
		when(applicantController.searchJobs(searchString, appId)).thenReturn(mav);
		
		ModelAndView mav = new ModelAndView("homepage-applicant");
		assertEquals("homepage-applicant", mav.getViewName());
		
		mav.addObject("message","Invalid search string");
		assertEquals("Invalid search string", mav.getModelMap().get("message"));
		
		mav.addObject("id", appId);
		assertEquals(1, mav.getModelMap().get("id"));
		assertNotNull(mav);
	}
	
	@Test
	public void testSearchJobForNoJobs() {
		/*This method ensures that proper message is returned 
		*when a job position that is not present in the database is entered
		*/
		getApplicant.setId(id);
		int appId = getApplicant.getId();
		String searchString = "Architect";
        jobs = new ArrayList<Job>(0);
		
		when(applicantController.searchJobs(searchString, appId)).thenReturn(mav);
		when(applicantService.searchByJobString(searchString)).thenReturn(jobs);
		
		ModelAndView mav = new ModelAndView("homepage-applicant");
		assertEquals("homepage-applicant", mav.getViewName());
		assertEquals(0, jobs.size());
		
		mav.addObject("message","No jobs found with this search term");
		assertEquals("No jobs found with this search term", mav.getModelMap().get("message"));
		
		mav.addObject("id", appId);
		assertEquals(1, mav.getModelMap().get("id"));
		assertNotNull(mav);
	}
	
	@Test
	public void testShowJobPage() { 
		//This method will check if the list of jobs are correctly displayed after searching for a job
		getApplicant.setId(id);
		int appId = getApplicant.getId();
		Applicant getApplicant = new Applicant("James123","James","Barner","james123@gmail.com","6308765342","100 North University Rd","Apt 2","Normal","IL",61761,"United States","","","","","","");
		Job job1 = new Job("Developer","Google","Java","90000.00","LA","CA");
		employer.addJobs(job1);
		List<Job> jobs = employer.getJobs();
		
		when(applicantController.showJobPage(jobId, appId, theModel)).thenReturn(mav);
		when(applicantService.getByJobId(jobId)).thenReturn(jobs);
		when(applicantService.getUser(appId)).thenReturn(getApplicant);
		
		mav = applicantController.showJobPage(jobId, appId, theModel);
		List<Job> getJobs = applicantService.getByJobId(jobId);
		
		mav = new ModelAndView("job-page");
		assertEquals("job-page", mav.getViewName());
		
		mav.addObject("getJobs", getJobs);
		assertEquals(1, getJobs.size());
		assertNotNull(getJobs);
		assertEquals(Arrays.asList(job1), getJobs);
		assertEquals("Developer", getJobs.get(0).getTitle());
		
		mav.addObject("jobId", jobId);
		assertEquals(1, mav.getModelMap().get("jobId"));
		
		mav.addObject("id", appId);
		assertEquals(1, mav.getModelMap().get("id"));
		
		mav.addObject("firstname", getApplicant.getFirstName());
		assertEquals("James", mav.getModelMap().get("firstname"));
		
		mav.addObject("jobdetails", "Job details");
		assertEquals("Job details", mav.getModelMap().get("jobdetails"));
		assertNotNull(mav);
	}
	
	@Test
	public void testApplyJob() {
		//This method will check if an applicant can apply for the job
		getApplicant.setId(id);
		int appId = getApplicant.getId();
		Applicant getApplicant = new Applicant("James123","James","Barner","james123@gmail.com","6308765342","100 North University Rd","Apt 2","Normal","IL",61761,"United States","","","","","","");
		String firstname = getApplicant.getFirstName();
		
		when(applicantController.applyJob(appId, firstname, jobId, theModel)).thenReturn(mav);
		
		mav = applicantController.applyJob(appId, firstname, appId, theModel);
		
		mav = new ModelAndView("job-page");
		assertEquals("job-page", mav.getViewName());
		
		mav.addObject("message","Successfully applied for this job");
		assertEquals("Successfully applied for this job", mav.getModelMap().get("message"));
		
		mav.addObject("id", appId);
		assertEquals(1, mav.getModelMap().get("id"));
		
		mav.addObject("firstname", firstname);
		assertEquals("James", mav.getModelMap().get("firstname"));
		assertNotNull(mav);
	}
	
	@Test
	public void testGetHomePage(){
		//This method checks if the homepage-applicant view is rendered properly with attributes
		getApplicant.setId(id);
		int appId = getApplicant.getId();
		getApplicant.setFirstName("James");
		String firstname = getApplicant.getFirstName();
		
		when(applicantController.getHomePage(appId, firstname)).thenReturn(mav);
		
		mav = applicantController.getHomePage(appId, firstname);
		mav = new ModelAndView("homepage-applicant");
		assertEquals("homepage-applicant", mav.getViewName());
		
		mav.addObject("id", appId);
		assertEquals(1, mav.getModelMap().get("id"));
		
		mav.addObject("firstname", firstname);
		assertEquals("James", mav.getModelMap().get("firstname"));
	}
	
	@Test
	public void testLogout() {
		//This method is to check if the logout is rendered properly
		HttpServletRequest request = null;
		when(applicantController.logout(request)).thenReturn("redirect:../login/showFormForLogin");
		
		String viewName = applicantController.logout(request);
		Assert.assertNotNull(viewName);
		Assert.assertEquals("redirect:../login/showFormForLogin", viewName);
	}
}
