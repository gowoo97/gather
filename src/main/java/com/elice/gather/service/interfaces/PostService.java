package com.elice.gather.service.interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.elice.gather.DTO.PostDTO;
import com.elice.gather.entity.Post;


public interface PostService {

	Post savePost(PostDTO postDTO);
	
	Page<Post> findAll(int page,int size);
	
	
	Post findPostById(long postId);
	
	int deletePost(long postId,String userId);
	
	Post modifyPost(Map<String, Object> paramMap,List<String> week);
	
	List<Post> getPostsByKeyword(String keyword, Pageable pageable);
}
