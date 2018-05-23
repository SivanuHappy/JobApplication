/*
 * This controller handles registered employer functionalities like profile management and posting jobs
 * Author: Anusha, Shweta
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
import com.jobapplication.entity.Employer;
import com.jobapplication.entity.Job;
import com.jobapplication.service.EmployerService;

@Controller
@RequestMapping("/employer")
public class EmployerController {

	//need to inject the user service
	@Autowired
	private EmployerService employerService;


	@GetMapping("/showUpdateProfile")
	public ModelAndView showUpdateProfile(@RequestParam ("empId") int theId, ModelMap theModel) {
		//This method fetches existing existing user from DB and renders the view
		ModelAndView mav = null;
		Employer theEmployer = employerService.getUser(theId);
		theModel.addAttribute("employer",theEmployer);
		mav = new ModelAndView("profile-form-employer");
		mav.addObject("userId", theEmployer.getUser().getId());
		mav.addObject("userName", theEmployer.getUser().getUsername());
		mav.addObject("id", theEmployer.getId());
		mav.addObject("firstname", theEmployer.getFirstName());
		return mav;
	}	

	@PostMapping("/updateProfile")
	public ModelAndView updateProfile(@ModelAttribute("employer") @Valid Employer theEmployer, BindingResult theBindingResult) {
		/*
		 * This method allows an employer to update his profile
		 */
		ModelAndView mav = null;
		if (theBindingResult.hasErrors()) {
			mav = new ModelAndView("profile-form-employer");
		}
		else {
			boolean updateSuccess = employerService.updateEmployer(theEmployer);
			if(updateSuccess) {
				mav = new  ModelAndView("profile-form-employer");
				mav.addObject("empmessage", "Employer account updated successfully!!");
				mav.addObject("id", theEmployer.getId());
				mav.addObject("firstname", theEmployer.getFirstName());
			}
		}
		return mav;
	}

	@GetMapping("/showFormForAddJob")
	public ModelAndView showFormForAddJob(@RequestParam ("empId") int theId, ModelMap theModel) {
		//This method renders model to view
		Job theJob = new Job();
		theModel.addAttribute("job",theJob);
		ModelAndView mav = new ModelAndView("job-form");
		mav.addObject("empId",theId);
		return mav;
	}

	@GetMapping("/listJobs")
	public String listJobs(@RequestParam ("empId") int theId, ModelMap theModel) {
		//This method list jobs created by an employer
		List<Job> theJobs = employerService.getJobs(theId);
		Employer employer = employerService.getUser(theId);
		theModel.addAttribute("job", theJobs);
		theModel.addAttribute("id", theId);
		theModel.addAttribute("firstname", employer.getFirstName());
		return "list-jobs";
	}


	@PostMapping("/addJob")
	public ModelAndView addJob(@ModelAttribute("job") @Valid Job theJob, BindingResult theBindingResult) {
		//This method allows an employer to add a new job
		ModelAndView mav = null;
		if (theBindingResult.hasErrors()) {
			mav = new ModelAndView("job-form");
		}
		else {
			int theId = theJob.getEmployer().getId();
			boolean checkSave = employerService.saveJob(theJob);
			if(checkSave) {
				List<Job> theJobs = employerService.getJobs(theId);
				mav = new  ModelAndView("list-jobs");
				mav.addObject("job", theJobs);
				mav.addObject("id",theId);
			}
		}
		return mav;
	}

	@GetMapping("/getHomePage")
	public ModelAndView getHomePage(@RequestParam ("empId") int theId, @RequestParam ("firstname") String firstname){
		//This method provides a link to an employer home page
		ModelAndView mav = null;
		mav = new ModelAndView("homepage-employer");
		mav.addObject("id", theId);
		mav.addObject("firstname", firstname);
		return mav;
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		//This method terminates the session and redirects to login page
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
		return "redirect:../login/showFormForLogin";
	}
}


