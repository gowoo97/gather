package com.elice.gather.service.interfaces;

import com.elice.gather.entity.Member;

public interface LoginService {

	
	Member login(String userId, String password);
	
}
