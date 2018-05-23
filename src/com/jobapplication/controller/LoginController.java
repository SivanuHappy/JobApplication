/*
 * This controller handles existing user login
 * Author: Karishma
 */

package com.jobapplication.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jobapplication.entity.User;
import com.jobapplication.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {

	//need to inject the user service
	@Autowired
	private UserService userService;

	@GetMapping("/showFormForLogin")
	public String showFormForLogin(ModelMap theModel) {
		//This method renders model to view
		User theUser = new User();
		theModel.addAttribute("user",theUser);
		return "login-form";
	}

	@PostMapping("/loginUser")
	public ModelAndView loginUser(@ModelAttribute("user") User theUser) {
		/*
		 * This method checks for valid user
		 * Then based on role redirects to corresponding home page
		 */
		ModelAndView mav = null;
		boolean validUser = userService.validUser(theUser);
		if (validUser) {
			User user = userService.getUserByUserName(theUser);
			if(user.getRole().equals("Applicant")) {
				mav = new ModelAndView("homepage-applicant");
				int appId = userService.getUser(user.getId());
				mav.addObject("firstname", user.getFirstName());
				mav.addObject("id", appId);
			}
			else {
				mav = new ModelAndView("homepage-employer");
				int empId = userService.getUser(user.getId());
				mav.addObject("firstname", user.getFirstName());
				mav.addObject("id", empId);
			}
		} else {
			mav = new ModelAndView("login-form");
			mav.addObject("message", "Username or Password is wrong!!");
		}
		return mav;
	}

}

