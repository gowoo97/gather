package com.elice.gather.service.interfaces;

import com.elice.gather.entity.Request;

import jakarta.servlet.http.HttpServletRequest;

public interface RequestService {

	Request saveRequest(long postId,HttpServletRequest req);
	
}
