package kr.or.connect.mvcexam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.or.connect.mvcexam.dto.User;

@Controller
public class UserController {
	
	@GetMapping(path="/userForm")
	public String userForm() {
		return "userForm";
	}
	
	@PostMapping(path="/regist")
	public String regist(@ModelAttribute User user, ModelMap modelMap) {
		System.out.println("사용자가 입력한 user 정보입니다.");
		System.out.println(user);
		
		modelMap.addAttribute(user);
		
		return "regist";
	}
}
