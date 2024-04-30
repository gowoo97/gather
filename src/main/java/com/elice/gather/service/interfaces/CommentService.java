package com.elice.gather.service.interfaces;

import java.util.List;

import com.elice.gather.DTO.CommentDTO;
import com.elice.gather.entity.Comment;

public interface CommentService {

		Comment save(CommentDTO commentDTO);
		
		List<Comment> findAll(int page, int size);
}
