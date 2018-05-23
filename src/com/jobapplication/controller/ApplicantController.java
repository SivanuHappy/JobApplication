/*
 * This controller handles registered employer functionalities like profile management and posting jobs
 * Author: Anusha, Xue
 */
package com.jobapplication.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jobapplication.entity.Applicant;
import com.jobapplication.entity.Job;
import com.jobapplication.service.ApplicantService;

@Controller
@RequestMapping("/applicant")
public class ApplicantController {

	@Autowired
	private ApplicantService applicantService;

	@GetMapping("/showUpdateProfile")
	public ModelAndView showUpdateProfile(@RequestParam ("appId") int theId, ModelMap theModel) {
		//This method fetches existing applicant from DB
		ModelAndView mav = null;
		Applicant theApplicant = applicantService.getUser(theId);
		theModel.addAttribute("applicant",theApplicant);
		mav = new ModelAndView("profile-form-applicant");
		mav.addObject("userId", theApplicant.getUser().getId());
		mav.addObject("userName", theApplicant.getUser().getUsername());
		mav.addObject("id", theApplicant.getId());
		mav.addObject("firstname", theApplicant.getFirstName());
		return mav;
	}	

	@PostMapping("/updateProfile")
	public ModelAndView updateProfile(@ModelAttribute("applicant") @Valid Applicant theApplicant, BindingResult theBindingResult) {
		//This method allows an applicant to update profile
		ModelAndView mav = null;
		if (theBindingResult.hasErrors()) {
			mav = new ModelAndView("profile-form-applicant");
		}
		else{
			boolean  success = applicantService.updateApplicant(theApplicant);
			if(success) {
				mav = new  ModelAndView("profile-form-applicant");
				mav.addObject("appmessage", "Applicant account updated successfully!!");
				mav.addObject("id", theApplicant.getId());
				mav.addObject("firstname", theApplicant.getFirstName());
			}
		}
		return mav;
	}

	@GetMapping("/searchJobs")
	public ModelAndView searchJobs(@RequestParam("searchString") String searchString, @RequestParam("appId") int appId){
		//This method allows an applicant to search for jobs using job title
		ModelAndView mav = new ModelAndView("homepage-applicant");
		if(searchString.isEmpty()) {
			mav.addObject("message","Invalid search string");
			mav.addObject("appId", appId);
		}
		else {
			List<Job> theJobs = applicantService.searchByJobString(searchString);
			if (theJobs.size() > 0){
				mav.addObject("theJobs",theJobs);
				mav.addObject("id", appId);
			}
			else{
				mav.addObject("message", "No jobs found with this search term");
				mav.addObject("id", appId);
			}

		}
		return mav;
	}

	@GetMapping("/showJobPage")
	public ModelAndView showJobPage(@RequestParam ("id") int theId, @RequestParam ("appId") int appId, ModelMap theModel) {
		//This method renders job model to view
		ModelAndView mav = null;
		List<Job> theJob = applicantService.getByJobId(theId);
		Applicant applicant = applicantService.getUser(appId);
		mav = new ModelAndView("job-page");
		mav.addObject("jobId", theId);
		mav.addObject("id", appId);
		mav.addObject("firstname", applicant.getFirstName());
		mav.addObject("jobdetails", "Job details");
		mav.addObject("theJob", theJob);
		return mav;
	}	

	@GetMapping("/applyJob")
	public ModelAndView applyJob(@RequestParam ("appId") int appId, @RequestParam ("firstname") String firstname, @RequestParam ("jobid") int jobId, ModelMap theModel){
		//This method allows an applicant to apply for a job
		ModelAndView mav = null;
		mav = new ModelAndView("job-page");
		mav.addObject("message","Successfully applied for this job");
		mav.addObject("id", appId);
		mav.addObject("firstname", firstname);
		return mav;
	}

	@GetMapping("/getHomePage")
	public ModelAndView getHomePage(@RequestParam ("appId") int theId, @RequestParam ("firstname") String firstname){
		ModelAndView mav = null;
		mav = new ModelAndView("homepage-applicant");
		mav.addObject("id", theId);
		mav.addObject("firstname", firstname);
		return mav;
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
		return "redirect:../login/showFormForLogin";
	}

}

