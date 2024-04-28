package com.FoodDelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.FoodDelivery.Dao.AddProductDao;
import com.FoodDelivery.Dao.UpdateProductDao;
import com.FoodDelivery.entity.FoodProduct;
import com.FoodDelivery.entity.MyFoodList;
import com.FoodDelivery.repository.FoodProductRepository;
import com.FoodDelivery.repository.MyFoodRepository;
import com.FoodDelivery.service.MyFoodListService;
import com.FoodDelivery.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private MyFoodRepository mcr;

	@Autowired
	private MyFoodListService myFoodServices;

	@Autowired
	private ProductService productService;

	@Autowired
	private FoodProductRepository foodProductRepository;

	@PostMapping("/{id}/{upload}")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @PathVariable Integer id) {
		return ResponseEntity.ok(this.productService.storeFile(id, file));

	}

	@GetMapping("/show")
	public ModelAndView show() {
		ModelAndView mav = new ModelAndView("showproduct");
		mav.addObject("products", this.foodProductRepository.findAll());
		return mav;

	}

	@GetMapping("/{id}")
	public String delete(@PathVariable("id") Integer id) {
		this.productService.deleteProduct(id);
		return "index";

	}

	@GetMapping("/editproduct/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("product", this.foodProductRepository.findById(id));
		return "editproduct";

	}

	@PostMapping("/editproduct/{id}")
	public String saveProduct(@ModelAttribute("product") UpdateProductDao updateProductDao, Model model,
			@PathVariable("id") Integer id) {
		this.productService.updateProduct(updateProductDao, id);
		FoodProduct foodProduct = this.foodProductRepository.findById(id).orElse(null);
		model.addAttribute("product", foodProduct);
		return "index";

	}

	@GetMapping("/Admin")
	public ModelAndView show1() {
		ModelAndView mav = new ModelAndView("Admin");
		mav.addObject("products", this.foodProductRepository.findAll());
		return mav;

	}

	@RequestMapping("/mylist/{id}")
	public String getMyFood(@PathVariable("id") int id) {
		FoodProduct p = productService.getFoodById(id);
		MyFoodList mc = new MyFoodList(p.getId(), p.getFoodName(), p.getFoodPrice(), p.getFoodCategory(),
				p.getImageUrl());
		myFoodServices.saveMyFood(mc);
		return "redirect:/products/sho";

	}

	@GetMapping("/sho")
	public ModelAndView sho() {
		ModelAndView mav = new ModelAndView("MyFood");
		mav.addObject("products", this.mcr.findAll());
		return mav;

	}
	

	/*
	 * @GetMapping("/show1") public ModelAndView sho1() { ModelAndView mav=new
	 * ModelAndView("show1"); mav.addObject("products",this.mcr.findAll()); return
	 * mav; }
	 */

}
