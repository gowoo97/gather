package com.elice.gather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elice.gather.entity.Post;
import com.elice.gather.service.interfaces.PostService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private PostService postService;
	
    @GetMapping
    public String home(Model model){
       Page<Post>	posts = postService.findAll(0, 10);
    	model.addAttribute("posts", posts.toList());
       
        return "home";
    }
}
