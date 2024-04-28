package com.FoodDelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.FoodDelivery.Dao.LoginDao;
import com.FoodDelivery.Dao.RegisterDao;
import com.FoodDelivery.entity.User;
import com.FoodDelivery.service.MyFoodListService;
import com.FoodDelivery.service.UserServices;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private MyFoodListService myFoodListService;
	
	@Autowired
	private UserServices userServices;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody RegisterDao registerDao){
		return ResponseEntity.ok(this.userServices.createuser(registerDao));
		
	}
	@GetMapping("/login")
	public String log(Model model) {
		model.addAttribute("user",new User());
		return "login";
		
	}
	@PostMapping("/login")
	public String Login(@ModelAttribute("user")LoginDao loginDao,Model model) {
		model.addAttribute("user",loginDao);
		Authentication authentication = authenticationManager.authenticate(
		new UsernamePasswordAuthenticationToken(loginDao.getEmail(),	
		loginDao.getPassword())
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "";
	}
	
	@GetMapping("/register")
	public String registerGet(Model model) {
		model.addAttribute("user",new User());
		return "register";
		
	}
	@PostMapping("/register")
	public String registerPost(@ModelAttribute("user")RegisterDao registerDao,Model model) {
		model.addAttribute("user",registerDao);
		this.userServices.createuser(registerDao);
		return "login";
		
	}
	@GetMapping("/about")
	public String aboutGString(Model model) {
		model.addAttribute("user", new User());
		return "about";
		
	}
	@GetMapping("/{id}")
	public String delete(@PathVariable("id") Integer id) {
		this.myFoodListService.deleteProduct(id);
		return "index";
		
	}
}
