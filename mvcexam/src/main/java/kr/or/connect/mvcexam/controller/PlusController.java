package kr.or.connect.mvcexam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlusController {

	@GetMapping(path="/plusform")
	public String plusform() {
		// 뷰에 대한 정보만 넘겨주면 됨.
		return "plusform"; // 뷰 이름을 넘겨줌
	}
	
	@PostMapping(path="plus")
	public String plus(
			@RequestParam(name="value1", required=true) int value1,
			@RequestParam(name="value2", required=true) int value2,
			ModelMap modelMap) {
		// name은 http parameter의 name과 매핑
		// required는 필수인지 아닌지 판단
		
		int result = value1 + value2;
		
		modelMap.addAttribute("value1", value1);
		modelMap.addAttribute("value2", value2);
		modelMap.addAttribute("result", result);
		
		return "plusResult"; // view name view resolver를 통해서 view를 찾아내어 출력
	}
}
