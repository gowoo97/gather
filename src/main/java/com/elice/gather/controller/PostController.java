package com.elice.gather.controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.elice.gather.DTO.PostDTO;
import com.elice.gather.entity.Comment;
import com.elice.gather.entity.Post;
import com.elice.gather.service.interfaces.CommentService;
import com.elice.gather.service.interfaces.PostService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/write")
	public String writePostPage() {
		return "write_post";
	}
	
	@PostMapping
	@Transactional
	public String createPost(@RequestParam Map<String, Object> paramMap,
			@RequestParam("week") List<String> week,HttpServletRequest request) throws IllegalStateException, IOException, InterruptedException {
		HttpSession session = request.getSession();
		PostDTO postDTO = createPostDTO(paramMap,week,(String) session.getAttribute("userId"));
		postService.savePost(postDTO);
		Thread.sleep(1000);
		return "redirect:/board/view?id="+(String)paramMap.get("id");
	}
	
	@GetMapping("/view")
	public String postView(Model model,@RequestParam("id") long postId) {
		Post post = postService.findPostById(postId);
		List<Comment> comments = commentService.findAll(0, 10);
		model.addAttribute("post", post);
		model.addAttribute("comments", comments);
		
		return "post_detail";
	}
	
	@GetMapping("/modify")
	public String modifyPage(@RequestParam("postId") long id,Model model) {
		
		Post post = postService.findPostById(id);
		PostDTO postDTO = PostDTO.builder()
				.id(post.getId())
				.title(post.getTitle())
				.content(post.getContent())
				.dayOfWeek(post.getDayOfWeek())
				.build();
		model.addAttribute("post", postDTO);
		
		return "modify_post";
	}
	
	@PostMapping("/modify.do")
	public String modifyPost(@RequestParam Map<String, Object> paramMap,@RequestParam("week") List<String> week) {
		
		postService.modifyPost(paramMap, week);
		
		return "redirect:/post/view?id="+(String)paramMap.get("id");
	}
	
	private PostDTO createPostDTO(Map<String, Object> paramMap,List<String> week,String userId) throws IllegalStateException, IOException, InterruptedException {
		//UUID uuid=UUID.randomUUID();
		//image.transferTo(Paths.get("src/main/resources/static/images/"+uuid.toString()+"_"+image.getOriginalFilename()));
		//////////////////////////////////////////이미지 저장
		
		//String fileName =  uuid.toString()+"_"+image.getOriginalFilename();
		String title = (String)paramMap.get("title");
		String content = (String)paramMap.get("content");
		StringBuffer joinWeek = new StringBuffer("0000000");
		long imageId  = Long.parseLong((String)paramMap.get("imageId"));
		int participants  = Integer.parseInt((String) paramMap.get("participants"));
		long board_id = Long.parseLong((String)paramMap.get("id"));
		String publisher = userId;
		for(String val : week) {
			joinWeek.setCharAt(Integer.parseInt(val),'1');
		}
		
		return PostDTO.builder()
				.board_id(board_id)
				.publisher(publisher)
				.title(title)
				.content(content)
				.dayOfWeek(joinWeek.toString())
				.maxParticipants(participants)
				.participants(0)
				.image(imageId)
				.build();

	}
	
	@PostMapping("/delete.do")
	public String deletePost(@RequestParam("postId") long id,HttpServletRequest req,Model model) {
		HttpSession session = req.getSession();
		int rst = postService.deletePost(id, (String)session.getAttribute("userId"));
		if(rst ==-1) {
			model.addAttribute("message","권한이 없습니다.");
			model.addAttribute("url","/board");
			return "alert";
		}
		
		return "redirect:/board";
	}
	
	
}
