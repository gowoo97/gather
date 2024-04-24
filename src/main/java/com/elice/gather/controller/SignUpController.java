package com.elice.gather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elice.gather.entity.Member;
import com.elice.gather.service.interfaces.MemberService;

@Controller
@RequestMapping("/signup")
public class SignUpController {
	
	@Autowired
	private MemberService memberService;

	@GetMapping
	public String signUpPage() {
		return "signup";
	}
	
	@PostMapping
	public String signup(@RequestParam("userId") String userId, @RequestParam("password") String password) {
		Member member = memberService.enrollMember(userId, password);
		return "redirect:/";
	}
	
}
