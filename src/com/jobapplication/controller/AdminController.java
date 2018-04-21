package com.jobapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jobapplication.entity.Admin;
import com.jobapplication.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/")
	public String showFormForRegister(ModelMap theModel) {
		// create model attribute to bind form data
		Admin theAdmin = new Admin();
		theModel.addAttribute("admin", theAdmin);
		return "admin-form";
	}
	
	@PostMapping("/loginAdmin")
	public ModelAndView loginAdmin(@ModelAttribute("admin") Admin theAdmin, BindingResult theBindingResult) {
		ModelAndView mav = null;
		boolean checkAdmin = adminService.loginCheck(theAdmin);
		if (checkAdmin){
			mav = new  ModelAndView("homepage-admin");
			mav.addObject("firstname", theAdmin.getUsername());
		}
		return mav;
	}

}
