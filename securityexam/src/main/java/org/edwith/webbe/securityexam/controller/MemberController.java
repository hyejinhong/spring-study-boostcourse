package org.edwith.webbe.securityexam.controller;

import org.edwith.webbe.securityexam.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/members")
public class MemberController {
	private final MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/loginform")
	public String loginform() {
		return "/members/loginform";
	}
	
	@RequestMapping("loginerror")
	public String loginerror(@RequestParam("login_error") String loginError) {
		return "/members/loginerror";
	}
}
