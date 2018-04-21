package com.jobapplication.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.jobapplication.service.UserService;

@Controller
@RequestMapping("/applicant")
public class ApplicantController {

	@Autowired
	private ApplicantService applicantService;

	@GetMapping("/showUpdateProfile")
	public ModelAndView showUpdateProfile(@RequestParam ("appId") int theId, Model theModel) {
		ModelAndView mav = null;
		//get the applicant data from service
		Applicant theApplicant = applicantService.getUser(theId);
		//set applicant as a model attribute to pre-populate form
		theModel.addAttribute("applicant",theApplicant);
		System.out.println(theApplicant.getUser().getId());
		//send over to profile form applicant
		mav = new ModelAndView("profile-form-applicant");
		mav.addObject("userId", theApplicant.getUser().getId());
		mav.addObject("userName", theApplicant.getUser().getUsername());
		mav.addObject("id", theApplicant.getId());
		mav.addObject("firstname", theApplicant.getFirstName());
		return mav;
	}	

	@PostMapping("/updateProfile")
	public ModelAndView updateProfile(@ModelAttribute("applicant") @Valid Applicant theApplicant, BindingResult theBindingResult) {
		ModelAndView mav = null;
		if (theBindingResult.hasErrors()) {
			mav = new ModelAndView("profile-form-applicant");
		}
		else{
			applicantService.updateApplicant(theApplicant);
			mav = new  ModelAndView("profile-form-applicant");
			mav.addObject("appmessage", "Applicant account updated successfuly!!");
			mav.addObject("id", theApplicant.getId());
			mav.addObject("firstname", theApplicant.getFirstName());
		}
		return mav;
	}

	@GetMapping("/searchJobs")
	public ModelAndView searchJobs(@RequestParam("searchString") String searchString){
		ModelAndView mav = new ModelAndView("homepage-applicant");
		System.out.println(searchString);
		if(searchString != null){
			List<Job> theJobs = applicantService.searchByJobString(searchString);
			if (theJobs.size() > 0){
				mav.addObject("theJobs",theJobs);
			}
			else{
				mav.addObject("message", "No jobs found with this search term");
			}
		}
		//List<Job> searchJobs = applicantService.getJobsByKeword(theJob);
		else{
			mav.addObject("message","Invalid search string");
		}
		return mav;
	}
	
    //Show job page to add new job
	@GetMapping("/showJobPage")
	public ModelAndView showJobPage(@RequestParam ("id") int theId, Model theModel) {
		ModelAndView mav = null;
		//get the employer data from service
		List<Job> theJob = applicantService.getByJobId(theId);
		//System.out.println(theJob);
		mav = new ModelAndView("job-page");
		mav.addObject("jobdetails", "Job details");
		mav.addObject("theJob", theJob);
		return mav;
	}	

	@GetMapping("/applyJob")
	public ModelAndView applyJob(Model theModel){
		ModelAndView mav = null;
		mav = new ModelAndView("job-page");
		mav.addObject("message","Successfully applied for this job");
		//System.out.println(theModel);
		//mav.addObject("theJob",theModel);
		return mav;
	}
	
	@GetMapping("/getHomePage")
	public ModelAndView getHomePage(@RequestParam ("appId") int theId, @RequestParam ("firstname") String firstname){
		ModelAndView mav = null;
		System.out.println(theId);
		mav = new ModelAndView("homepage-applicant");
		mav.addObject("id", theId);
		mav.addObject("firstname", firstname);
		return mav;
	}
	
	@GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        System.out.println("logout()");
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return "redirect:../login/showFormForLogin";
    }
	
}

