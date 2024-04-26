package com.elice.gather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elice.gather.DTO.PostDTO;
import com.elice.gather.entity.Post;
import com.elice.gather.repository.MemberRepository;
import com.elice.gather.repository.PostRepository;
import com.elice.gather.service.interfaces.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private PostRepository postRepository;
	
	@Override
	@Transactional
	public Post savePost(PostDTO postDTO) {
		
		
		
		
		return null;
	}

}
