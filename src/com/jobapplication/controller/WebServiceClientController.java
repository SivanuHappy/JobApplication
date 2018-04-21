package com.jobapplication.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.jobapplication.entity.User;


@Controller
@RequestMapping("/wsclient")
public class WebServiceClientController {
	
	@GetMapping("/listUsers")
	public ModelAndView getUsers(){
		ModelAndView mav = null;
		final String url = "http://localhost:8091/JobApplication/ws/listUsers.json";
		RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity <List<User>> response = restTemplate.exchange(url,
	    	    HttpMethod.GET, null, new ParameterizedTypeReference <List<User>>() {});
	    List <User> user = response.getBody(); 
	    mav = new ModelAndView("wsclient-page");
	    mav.addObject("users",user);
	    return mav;
	}
	
	@GetMapping("/getUserById")
	public ModelAndView getUserById(@RequestParam("searchString") String searchString){
		int id = Integer.parseInt(searchString);
		ModelAndView mav = null;
		final String url = "http://localhost:8091/JobApplication/ws/getUser/" + id;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity <User> response = restTemplate.exchange(url,
	    	    HttpMethod.GET, null, new ParameterizedTypeReference <User>() {});
		User user = response.getBody();
		System.out.println(user);
		mav = new ModelAndView("homepage-admin");
	    mav.addObject("user",user);
		return mav;
	}
}
