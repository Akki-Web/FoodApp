package com.FoodDelivery.Dao;

public class UpdateProductDao {

	private String foodName;
	private String foodPrice;
	private String foodCategory;
	private String imageUrl;
	
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
		return "UpdateProductDao [foodName=" + foodName + ", foodPrice=" + foodPrice + ", foodCategory=" + foodCategory
				+ ", imageUrl=" + imageUrl + "]";
	}
	
}
