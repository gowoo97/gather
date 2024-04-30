package com.elice.gather.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elice.gather.DTO.CommentDTO;
import com.elice.gather.service.interfaces.CommentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	
	@PostMapping("/write.do")
	public String saveComment(@RequestParam Map<String,Object> params
			,HttpServletRequest req) {
		HttpSession session = req.getSession();
		
		String publisher = (String) session.getAttribute("userId");
		String content = (String)params.get("content");
		String post_id = (String)params.get("post_id");
		System.out.println(post_id);
		CommentDTO commentDTO = CommentDTO.builder()
				.post(Long.parseLong(post_id))
				.publisher(publisher)
				.content(content)
				.build();
		
		commentService.save(commentDTO);
		
		return "redirect:/post/view?id="+post_id;
		
	}
	
	
	
}
