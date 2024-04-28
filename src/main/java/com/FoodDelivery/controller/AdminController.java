package com.FoodDelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.FoodDelivery.Dao.AddProductDao;
import com.FoodDelivery.entity.FoodProduct;
import com.FoodDelivery.repository.FoodProductRepository;
import com.FoodDelivery.repository.MyFoodRepository;
import com.FoodDelivery.service.MyFoodListService;
import com.FoodDelivery.service.ProductService;

@Controller
@RequestMapping("/admins")
public class AdminController {
	
	@Autowired
	private MyFoodRepository mcr;
	
	@Autowired
	private MyFoodListService myFoodServices;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private FoodProductRepository foodProductRepository;
	
	@GetMapping("/savestudent")
	public String create(Model model) {
		model.addAttribute("product",new FoodProduct());
		return "addproduct";
		
	}
	@PostMapping("/savestudent")
	public String saveProduct(@ModelAttribute("product")AddProductDao addProductDao,Model model) {
		model.addAttribute("product",addProductDao);
		this.productService.createProduct(addProductDao);
		System.out.println(addProductDao);
		return "index";
	}
	
}
