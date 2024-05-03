package com.elice.gather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elice.gather.DTO.BoardDTO;
import com.elice.gather.entity.Post;
import com.elice.gather.service.interfaces.BoardService;
import com.elice.gather.service.interfaces.PostService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private PostService postService;
	
	@GetMapping
	public String boardPage(Model model) {
		
		List<BoardDTO> list = boardService.getBoards(10);
		
		model.addAttribute("boards", list);
		
		return "board";
	}
	
	
	@PostMapping
	public String createBoard(Model model, @RequestParam("boardName") String boardName,
			HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");
		System.out.println(userId);
		if(userId == null  || userId.equals("")) {
			model.addAttribute("message","로그인을 하셔야 합니다.");
			model.addAttribute("url","board");
			return "alert";
		}
	
		boardService.save(boardName, userId);
		model.addAttribute("message", "게시판 생성 완료!");
		model.addAttribute("url", "board");
		return "alert";
	}
	
	@GetMapping("/view")
	public String boardDetail(@RequestParam("id") Long id , Model model,@RequestParam(name = "page",defaultValue = "0") Integer page) {
		
		Page<Post> posts = postService.findAll(page,10);
		BoardDTO board= boardService.getBoardById(id);
		model.addAttribute("board", board);
		model.addAttribute("posts", posts.toList());
		
		return "board_detail";
		
	}
	
	
	@PostMapping("/modify.do")
	public String modify(@RequestParam("boardName") String boardName,
			@RequestParam("boardId") long id,HttpServletRequest req) {
		HttpSession session = req.getSession();
		boardService.modifyBoard(id, boardName, (String)session.getAttribute("userId"));
		
		return "redirect:/board";
	}
	
	@PostMapping("/delete.do")
	public String delete(@RequestParam("boardId") long boardId,HttpServletRequest req,Model model) {
		HttpSession session = req.getSession();
		
		int rst = boardService.deleteBoard(boardId, (String)session.getAttribute("userId"));
		
		if(rst == -1) {
			model.addAttribute("message", "권한이 없습니다.");
			model.addAttribute("url", "/board");
			return "alert";
		}
		
		return "redirect:/board";
		
	}
	

	
	
}
