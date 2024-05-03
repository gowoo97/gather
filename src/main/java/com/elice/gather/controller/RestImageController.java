package com.elice.gather.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elice.gather.entity.Image;
import com.elice.gather.service.ImageServiceImpl;

@RestController
public class RestImageController {

	@Autowired
	private ImageServiceImpl imageService; 
	
	@PostMapping("/api/image")
	public ResponseEntity<?> saveImage(@RequestPart("image") MultipartFile image) throws IllegalStateException, IOException{
		System.out.println(image);
		Image imageEntity =imageService.saveImage(image);
		
		return ResponseEntity.ok(imageEntity);
	}
	
}
