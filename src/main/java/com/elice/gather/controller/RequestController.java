package com.elice.gather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.elice.gather.DTO.RequestDTO;
import com.elice.gather.entity.Request;
import com.elice.gather.service.interfaces.RequestService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class RequestController {

	@Autowired	
	private RequestService requestService;
	
	@PostMapping("/api/request")
	public ResponseEntity<?> createRequest(@RequestBody RequestDTO postId,HttpServletRequest req) {
		try {
			Request request = requestService.saveRequest(postId.getPostId(),req);
			
			return ResponseEntity.ok(null);
		}catch(Exception e) {
			//e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	
}
