package com.FoodDelivery.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	 @GetMapping("/")
	    public String home() {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
	            return "redirect:/admin";
	        } else {
	            return "redirect:/user";
	        }
	    }

	 @GetMapping("/admin")
	    public String adminPage() {
	        return "adminpage.html";
	    }

	    @GetMapping("/user")
	    public String userPage() {
	        return "index";
	    }
	 
	 
		/*
		 * @RequestMapping("/index") public String index() { return "index.html";
		 * 
		 * }
		 * 
		 * @RequestMapping("") public String create() { return "index";
		 * 
		 * }
		 */
}
