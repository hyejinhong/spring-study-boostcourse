package kr.or.connect.guestbook.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.connect.guestbook.dto.Guestbook;
import kr.or.connect.guestbook.service.GuestbookService;

@Controller
public class GuestbookController {
	@Autowired
	GuestbookService guestbookService;

	@GetMapping(path = "/list")
	public String list(@RequestParam(name = "start", required = false, defaultValue = "0") int start, ModelMap model,
			@CookieValue(value = "count", defaultValue = "0", required = true) String value,
			HttpServletResponse response) {

		try {
			int i = Integer.parseInt(value);
			value = Integer.toString(++i);
		} catch (Exception e) {
			value = "1";
		}

		Cookie cookie = new Cookie("count", value);
		cookie.setMaxAge(60 * 60 * 24 * 365);
		cookie.setPath("/");
		response.addCookie(cookie);

		// start로 시작하는 방명록 목록 구하기
		List<Guestbook> guestbooks = guestbookService.getGuestbooks(start);

		// 전체 페이지수 구하기
		int count = guestbookService.getCount();
		int pageCount = count / guestbookService.LIMIT;

		// 전체 게시글수 / 한페이지 게시글 수 나머지가 0보다 크면
		// (더 표시해야할 게시글들이 남아있으면)
		if (count % GuestbookService.LIMIT > 0) {
			pageCount++;
		}

		// 페이지 수만큼 start의 값을 List로 저장
		List<Integer> pageStartList = new ArrayList<Integer>();

		for (int i = 0; i < pageCount; i++) {
			pageStartList.add(i * GuestbookService.LIMIT);
		}

		model.addAttribute("list", guestbooks);
		model.addAttribute("count", count);
		model.addAttribute("pageStartList", pageStartList);
		model.addAttribute("cookieCount", value);

		return "list"; // 뷰 이름을 넘겨줌
	}
	
//	@PostMapping("write")
//	public String write(@RequestBody Guestbook guestbook,
//			HttpServletRequest request) {
//	
//		String clientIp = request.getRemoteAddr();
//		System.out.println("ㅎㅇ" + clientIp);
//		// id가 입력된 guestbook이 반환된다.
//		Guestbook resultGuestbook = guestbookService.addGuestbook(guestbook, clientIp);
//		
//		// 클라이언트로 갈 때는 json으로 전송됨
//		return "list";
//	}
	@PostMapping(path="/write")
	public String write(@ModelAttribute Guestbook guestbook, HttpServletRequest request) {
		String clientIp = request.getRemoteAddr();
		System.out.println("clientIp : " + clientIp);
		guestbookService.addGuestbook(guestbook, clientIp);
		return "redirect:list";
	}


	@GetMapping("/delete")
	public String delete(@RequestParam Long id,
			@SessionAttribute String isAdmin,
			HttpServletRequest request,
			RedirectAttributes redirectAttr) {
		
		if(isAdmin == null || !"true".equals(isAdmin)) {
			redirectAttr.addFlashAttribute("errorMessage", "로그인을 하지 않았습니다..");
			return "redirect:loginform";
		}
		
		String clientIp = request.getRemoteAddr();
		System.out.println(clientIp);
		guestbookService.deleteGuestbook(id, clientIp);
		return "redirect:list";
		
	}
}
