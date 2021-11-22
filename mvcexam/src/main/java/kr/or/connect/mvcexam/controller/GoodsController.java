package kr.or.connect.mvcexam.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class GoodsController {
	@GetMapping("/goods/{id}")
	public String getGoodsById(
			@PathVariable(name="id") int id,
			@RequestHeader(value="user-Agent", defaultValue="myBrowser") String userAgent,
			HttpServletRequest request, // 가급적 종속되는 부분 때문에 사용하지 않는 게 좋아요
			ModelMap model) {
		
		String path = request.getServletPath();
		
		System.out.println("id: " + id);
		System.out.println("user_agent: " + userAgent);
		System.out.println("path: " + path);
		
		model.addAttribute("id", id);
		model.addAttribute("userAgent", userAgent);
		model.addAttribute("path", path);
		
		return "goodsById";
	}
}
