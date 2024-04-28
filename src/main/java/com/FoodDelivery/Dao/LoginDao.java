package com.FoodDelivery.Dao;

public class LoginDao {
	
	private String Email;
	private String Password;
	
	
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		this.Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		this.Password = password;
	}
	@Override
	public String toString() {
		return "LoginDao [Email=" + Email + ", Password=" + Password + "]";
	}
	
	
	
	

}
