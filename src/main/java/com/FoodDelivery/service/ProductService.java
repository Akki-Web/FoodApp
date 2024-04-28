package com.FoodDelivery.service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.FoodDelivery.FileStoreageProperties;
import com.FoodDelivery.Dao.AddProductDao;
import com.FoodDelivery.Dao.UpdateProductDao;
import com.FoodDelivery.entity.FoodProduct;
import com.FoodDelivery.repository.FoodProductRepository;
import com.FoodDelivery.repository.MyFoodRepository;


@Service
public class ProductService {

	@Autowired
	private FoodProductRepository productRepository;
	private final Path rootLoaction;
	
	public ProductService(FileStoreageProperties properties) {
		this.rootLoaction = Paths.get(properties.getUploadDir());
		
		try {
			Files.createDirectories(rootLoaction);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String storeFile(Integer id,MultipartFile File) {
		try {
			if(File.isEmpty()) {
				System.out.println("empty File");
			}
			Path detinatiFile = this.rootLoaction.resolve(Paths.get(File.getOriginalFilename()));
			
			try
				(InputStream inputStream = File.getInputStream()){
					Files.copy(inputStream, detinatiFile,StandardCopyOption.REPLACE_EXISTING);
					
					FoodProduct foodProduct = this.productRepository.findById(id).orElseThrow();
					String fileuploadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
							.path("FoodProducts/download/")
							.path(File.getOriginalFilename())
							.toUriString();
					foodProduct.setImageUrl(fileuploadUri);
					this.productRepository.save(foodProduct);
					return "file uploaded";
					
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "error";	
	}
	public Resource loadResource(String filename) {
		Path file=rootLoaction.resolve(filename);
		try {
			Resource resource = new UrlResource(file.toUri());
			if(resource.exists()||resource.isReadable()) {
				return resource;
			}else {
				System.out.println("File not Found");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	public String createProduct(AddProductDao addProductDao) {
		FoodProduct foodProduct = new FoodProduct();
		
		foodProduct.setFoodName(addProductDao.getFoodName());
		foodProduct.setFoodPrice(addProductDao.getFoodPrice());
		foodProduct.setFoodCategory(addProductDao.getFoodCategory());
		foodProduct.setImageUrl(addProductDao.getImageUrl());
		
		this.productRepository.save(foodProduct);
		return "Product save";
	}
	public void deleteProduct(Integer id) {
		this.productRepository.deleteById(id);
	}
	
	public void updateProduct(UpdateProductDao updateProductDao,Integer id) {
		
		FoodProduct foodProduct = this.productRepository.findById(id).orElse(null);
		if(updateProductDao.getFoodName()!=null) {
			foodProduct.setFoodName(updateProductDao.getFoodName());
		}
		if(updateProductDao.getFoodPrice()!=null) {
			foodProduct.setFoodPrice(updateProductDao.getFoodPrice());
		}
		if(updateProductDao.getFoodCategory()!=null) {
			foodProduct.setFoodCategory(updateProductDao.getFoodCategory());
		}
		if(updateProductDao.getImageUrl()!=null) {
			foodProduct.setImageUrl(updateProductDao.getImageUrl());
		}
		this.productRepository.save(foodProduct);
	}
	
	public FoodProduct getFoodById(int id) {
		return productRepository.findById(id).get();
		
	}

}
