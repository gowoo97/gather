package com.elice.gather.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elice.gather.DTO.CommentDTO;
import com.elice.gather.entity.Comment;
import com.elice.gather.entity.Member;
import com.elice.gather.entity.Post;
import com.elice.gather.repository.CommentRepository;
import com.elice.gather.repository.MemberRepository;
import com.elice.gather.repository.PostRepository;
import com.elice.gather.service.interfaces.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	@Transactional
	public Comment save(CommentDTO commentDTO) {
		Post post = postRepository.findById(commentDTO.getPost()).get();
		Member publisher = memberRepository.findByUserId(commentDTO.getPublisher());
		
		Comment comment = commentDTO.toEntity(post, publisher);
		
		commentRepository.save(comment);

		return commentRepository.save(comment);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Comment> findAll(int page,int size) {
		return commentRepository.findAll(PageRequest.of(page, size)).toList();
	}
	
	
	
	
	@Transactional
	public void deleteComment(long id) {
		commentRepository.deleteById(id);
	}
	
	@Transactional
	public Comment modifyComment(long id ,String content) {
		Comment comment = commentRepository.findById(id).get();
		comment.setContent(content);
		return comment;
	}

	
	

	
	
	
}
