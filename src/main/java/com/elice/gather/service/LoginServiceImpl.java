package com.elice.gather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elice.gather.entity.Member;
import com.elice.gather.repository.MemberRepository;
import com.elice.gather.service.interfaces.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public boolean login(String userId, String password) {
		
		Member existMember = memberRepository.findByUserId(userId);
		
		if(existMember == null) {
			return false;
		}
		
		if(existMember.getUserId().equals(userId) && existMember.getPassword().equals(password)) {
			return true;
		}
		
		return false;
	}
	
	
	
}
