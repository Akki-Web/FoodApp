package com.FoodDelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FoodDelivery.entity.FoodProduct;

@Repository
public interface FoodProductRepository extends JpaRepository<FoodProduct, Integer> {

	
}
