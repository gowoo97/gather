package com.elice.gather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elice.gather.entity.Member;
import com.elice.gather.repository.MemberRepository;
import com.elice.gather.service.interfaces.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public Member enrollMember(String userId, String password) {
		Member member = new Member();
		member.setUserId(userId);
		member.setPassword(password);
		return memberRepository.save(member);
	}



}
