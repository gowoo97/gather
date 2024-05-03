package com.elice.gather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elice.gather.entity.Member;
import com.elice.gather.entity.Post;
import com.elice.gather.entity.Request;
import com.elice.gather.repository.MemberRepository;
import com.elice.gather.repository.PostRepository;
import com.elice.gather.repository.RequestRepository;
import com.elice.gather.service.interfaces.RequestService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class RequestServiceImpl implements RequestService{

	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	@Transactional
	public Request saveRequest(long postId,HttpServletRequest req) {
		HttpSession session = req.getSession();
		
		Post post = postRepository.findById(postId).get();
		Member member = memberRepository.findByUserId((String)session.getAttribute("userId"));
		Request request = new Request(0,post,member,true);

		return requestRepository.save(request);
	}

	
	
}
