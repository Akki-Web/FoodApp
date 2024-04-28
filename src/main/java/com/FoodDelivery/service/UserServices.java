package com.FoodDelivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.FoodDelivery.Dao.RegisterDao;
import com.FoodDelivery.entity.User;
import com.FoodDelivery.repository.UserRepository;

@Service
public class UserServices {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder brBCryptPasswordEncoder;
	
	public User createuser(RegisterDao registerDao) {
		
		if(this.userRepository.findByEmail(registerDao.getEmail()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"User with email already exit");
		}
		User user = new User();
				user.setFirstname(registerDao.getFirstname());
				user.setLastname(registerDao.getLastname());
				user.setEmail(registerDao.getEmail());
				user.setPassword(brBCryptPasswordEncoder.encode(registerDao.getPassword()));
				user.setMobile(registerDao.getMobile());
				user.setRoles(registerDao.getRoles());
				this.userRepository.save(user);
		return user;
		
	}
}
