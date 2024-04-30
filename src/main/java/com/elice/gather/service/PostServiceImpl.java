package com.elice.gather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elice.gather.DTO.BoardDTO;
import com.elice.gather.DTO.PostDTO;
import com.elice.gather.entity.Board;
import com.elice.gather.entity.Member;
import com.elice.gather.entity.Post;
import com.elice.gather.repository.MemberRepository;
import com.elice.gather.repository.PostRepository;
import com.elice.gather.repository.interfaces.BoardRepository;
import com.elice.gather.service.interfaces.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Override
	@Transactional
	public Post savePost(PostDTO postDTO) {		
		BoardDTO boardDTO = boardRepository.getBoardById(postDTO.getBoard_id());
		Board board = boardDTO.toEntity(memberRepository.findById(boardDTO.getId()).get());
		Member publisher = memberRepository.findByUserId(postDTO.getPublisher());
	
		Post post = postDTO.toEntity(board, publisher); 
		postRepository.save(post);

		return post;
	}
	
	
	public Page<Post> findAll(int page,int size){
		return postRepository.findAll(PageRequest.of(page, size));
	}
	
	public Post findPostById(long postId) {
		return postRepository.findById(postId).get();
	}
	

}
