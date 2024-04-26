package com.elice.gather.controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.elice.gather.DTO.PostDTO;
import com.elice.gather.service.interfaces.PostService;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	@GetMapping("/write")
	public String writePostPage() {
		return "write_post";
	}
	
	@PostMapping
	@Transactional
	public String createPost(@RequestParam Map<String, Object> paramMap,@RequestParam("image") MultipartFile image,
			@RequestParam("week") List<String> week) throws IllegalStateException, IOException {
		
		PostDTO postDTO = createPostDTO(paramMap,image,week);
		postService.savePost(postDTO);
		
		
		return "board";
	}
	
	
	private PostDTO createPostDTO(Map<String, Object> paramMap,MultipartFile image,List<String> week) throws IllegalStateException, IOException {
		UUID uuid=UUID.randomUUID();
		image.transferTo(Paths.get("src/main/resources/static/images/"+uuid.toString()+"_"+image.getOriginalFilename()));
		//////////////////////////////////////////이미지 저장
		
		String fileName =  uuid.toString()+"_"+image.getOriginalFilename();
		String title = (String)paramMap.get("title");
		String content = (String)paramMap.get("content");
		StringBuffer joinWeek = new StringBuffer("0000000");
		int participants  = Integer.parseInt((String) paramMap.get("participants"));
		for(String val : week) {
			joinWeek.setCharAt(Integer.parseInt(val),'1');
		}
		
		return PostDTO.builder().title(title)
				.imagePath("/image/"+fileName)
				.content(content)
				.dayOfWeek(joinWeek.toString())
				.participants(participants)
				.build();
		
	
		
		
		
	
	}
	
}
