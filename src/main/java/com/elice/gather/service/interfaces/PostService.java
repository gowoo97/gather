package com.elice.gather.service.interfaces;

import org.springframework.data.domain.Page;

import com.elice.gather.DTO.PostDTO;
import com.elice.gather.entity.Post;


public interface PostService {

	Post savePost(PostDTO postDTO);
	
	Page<Post> findAll(int page,int size);
	
	
	Post findPostById(long postId);
}
