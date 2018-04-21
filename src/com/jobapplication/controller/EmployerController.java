package com.jobapplication.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
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
	public ModelAndView showUpdateProfile(@RequestParam ("empId") int theId, Model theModel) {
		ModelAndView mav = null;
		//get the employer data from service
		Employer theEmployer = employerService.getUser(theId);
		//set employer as a model attribute to pre-populate form
		theModel.addAttribute("employer",theEmployer);
		//send over to profile form employee
		mav = new ModelAndView("profile-form-employer");
		mav.addObject("userId", theEmployer.getUser().getId());
		mav.addObject("userName", theEmployer.getUser().getUsername());
		return mav;
	}	
	
	@PostMapping("/updateProfile")
	public ModelAndView updateProfile(@ModelAttribute("employer") Employer theEmployer) {
		ModelAndView mav = null;
		employerService.updateEmployer(theEmployer);
		mav = new  ModelAndView("profile-form-employer");
		mav.addObject("empmessage", "Employer account updated successfuly!!");
		return mav;
	}
	
	@GetMapping("/showFormForAddJob")
	public ModelAndView showFormForAddJob(@RequestParam ("empId") int theId, Model theModel) {
		Job theJob = new Job();
		theModel.addAttribute("job",theJob);
		//int getEmpId = employerService.getUser(theId).getId();
		//System.out.println("from showformforadd" + getEmpId);
		ModelAndView mav = new ModelAndView("job-form");
		mav.addObject("empId",theId);
		return mav;
	}
		
	@GetMapping("/listJobs")
	public String listJobs(@RequestParam ("empId") int theId, Model theModel) {
		System.out.println("I am from list method: " + theId);
		List<Job> theJobs = employerService.getJobs(theId);
		//add the customers to the model
		theModel.addAttribute("job", theJobs);
		theModel.addAttribute("id", theId);
		return "list-jobs";
	}
	
	@PostMapping("/addJob")
	public ModelAndView addJob(@ModelAttribute("job") Job theJob) {
		ModelAndView mav = null;
		int theId = theJob.getEmployer().getId();
		employerService.saveJob(theJob);	
		List<Job> theJobs = employerService.getJobs(theId);
		mav = new  ModelAndView("list-jobs");
		mav.addObject("job", theJobs);
		mav.addObject("id",theId);
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


