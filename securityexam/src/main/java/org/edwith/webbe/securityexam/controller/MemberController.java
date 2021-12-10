package org.edwith.webbe.securityexam.controller;

import org.edwith.webbe.securityexam.dto.Member;
import org.edwith.webbe.securityexam.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/members")
public class MemberController {
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
	public MemberController(MemberService memberService, PasswordEncoder passwordEncoder) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@GetMapping("/loginform")
	public String loginform() {
		return "/members/loginform";
	}
	
	@RequestMapping("loginerror")
	public String loginerror(@RequestParam("login_error") String loginError) {
		return "/members/loginerror";
	}
	
	@GetMapping("/join")
	public String joinform() {
		return "members/joinform";
	}
	
	@PostMapping("/join")
	public String join(@ModelAttribute Member member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		memberService.addMember(member, false);
		
		return "redirect:/members/welcome";
	}
}
