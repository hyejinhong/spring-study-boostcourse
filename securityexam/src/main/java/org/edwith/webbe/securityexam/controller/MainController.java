package org.edwith.webbe.securityexam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	@RequestMapping("/main")
	@ResponseBody
	public String main() {
		return "hi";
	}
	
	@RequestMapping("/securepage")
	@ResponseBody
	public String securitypage() {
		return "이건 못볼걸";
	}
}
