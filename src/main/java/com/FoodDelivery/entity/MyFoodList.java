package com.FoodDelivery.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="MyFood")
public class MyFoodList
{

	@Id
	@Column(name="food_id")
	private Integer id;
	
	private String foodName;
	
	private String foodPrice;
	
	private String foodCategory;
	
	private String imageUrl;
	
	

	public MyFoodList(Integer id, String foodName, String foodPrice, String foodCategory, String imageUrl) {
		super();
		this.id = id;
		this.foodName = foodName;
		this.foodPrice = foodPrice;
		this.foodCategory = foodCategory;
		this.imageUrl = imageUrl;
	}
	

	public MyFoodList() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(String foodPrice) {
		this.foodPrice = foodPrice;
	}

	public String getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "addToCart [id=" + id + ", foodName=" + foodName + ", foodPrice=" + foodPrice + ", foodCategory="
				+ foodCategory + ", imageUrl=" + imageUrl + "]";
	}
	
	
}
