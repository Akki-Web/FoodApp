
package com.FoodDelivery.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodDelivery.entity.FoodProduct;
import com.FoodDelivery.entity.MyFoodList;
import com.FoodDelivery.repository.FoodProductRepository;
import com.FoodDelivery.repository.MyFoodRepository;




@Service
public class MyFoodListService {

	@Autowired
	private FoodProductRepository foodProductRepository;
	
	@Autowired
	private MyFoodRepository myFood;
	
	public void saveMyFood(MyFoodList product) {
		myFood.save(product);
	}
	
	public List<FoodProduct> getAllFood(){
		return foodProductRepository.findAll();
		
	}
	public void deleteProduct(Integer id) {
		this.myFood.deleteById(id);
	}
	
	
}
