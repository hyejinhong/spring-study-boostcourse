package kr.or.connect.guestbook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GuessNumberController {
	@GetMapping("/guess")
	public String guess(@RequestParam(required = false) Integer number, HttpSession session, ModelMap model) {
		
		String message = null;
		
		// 처음 요청이 들어왔을 때
		if(number == null) {
			session.setAttribute("count", 0);
			session.setAttribute("randomNumber", (int) (Math.random()*100) + 1);
			message = "내가 생각한 숫자를 맞춰 보세요.";
		}
		// 사용자가 값을 입력한 이후
		else {
			int count = (Integer) session.getAttribute("count");
			int randomNumber = (Integer) session.getAttribute("randomNumber");
			
			if(number < randomNumber) {
				message = number+ "는 내가 생각한 값보다 작습니다.";
				session.setAttribute("count", ++count);
			}
			else if(number > randomNumber){
				message = number+ "는 내가 생각한 값보다 큽니다.";
				session.setAttribute("count", ++count);
			}
			else {
				message = ++count + "번 만에 맞췄습니다~ 정답은 " + number + "입니다.";
				session.removeAttribute("count");
				session.removeAttribute("count");
				session.removeAttribute("randomNumber");
			}
		}
		
		model.addAttribute("message", message);
		// jsp 이름
		return "guess";
	}
}
