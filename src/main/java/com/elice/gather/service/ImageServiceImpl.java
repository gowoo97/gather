package com.elice.gather.service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elice.gather.entity.Image;
import com.elice.gather.repository.ImageRepository;

@Service
public class ImageServiceImpl {

	@Autowired
	private ImageRepository imageRepository;
	
	
	public Image saveImage(MultipartFile image) throws IllegalStateException, IOException {
		UUID uuid = UUID.randomUUID();
		String fileName = uuid.toString()+"_"+image.getOriginalFilename();
		image.transferTo(Paths.get("src/main/resources/static/images/"+uuid.toString()+"_"+image.getOriginalFilename()));
		Image imageEntity = new Image();
		imageEntity.setFileName(fileName);
		
		return imageRepository.save(imageEntity);
	}
	
}
