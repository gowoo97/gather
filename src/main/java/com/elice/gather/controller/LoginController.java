package com.elice.gather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elice.gather.entity.Member;
import com.elice.gather.service.interfaces.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}


	@PostMapping("/login")
	public String login(final HttpServletRequest request,@RequestParam("userId") String userId, @RequestParam("password") String password,Model model){
		HttpSession session = request.getSession();
		Member existMember = loginService.login(userId, password);
		
		if(existMember!=null) {
			session.setAttribute("userId", userId);
			session.setAttribute("id", existMember.getId());
			model.addAttribute("message", "로그인 성공!");
			model.addAttribute("url", "/");
			return "alert";
		}
		model.addAttribute("message", "로그인 실패");
		model.addAttribute("url", "/login");
		return "alert";
		
	}
	
	@GetMapping("/logout")
	public String logout(final HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}


}
