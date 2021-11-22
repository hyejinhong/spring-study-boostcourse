package kr.or.connect.guestbook.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.guestbook.dto.Guestbook;
import kr.or.connect.guestbook.service.GuestbookService;

@RestController
@RequestMapping(path="/guestbooks") // 같은 배핑으로 여러 개를 수행하기 때문에 선언
public class GuestbookApiController {
	@Autowired
	GuestbookService guestbookService;
	
	@GetMapping
	public Map<String, Object> list(@RequestParam(name="start", required=false, defaultValue="0") int start) {
		
		List<Guestbook> guestbooks = guestbookService.getGuestbooks(start);
		
		int count = guestbookService.getCount();
		int pageCount = count / GuestbookService.LIMIT;
		
		if(count % GuestbookService.LIMIT > 0) {
			pageCount++;
		}
		
		List<Integer> pageStartList = new ArrayList<Integer>();
		for(int i=0; i<pageCount; i++) {
			pageStartList.add(i * GuestbookService.LIMIT);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", guestbooks);
		map.put("count", count);
		map.put("pageStartList", pageStartList);
		
		System.out.println("ㅎㅇ욘");
		
		// jsonMessageConverter가 map 객체를 json 형태로 변환하여 반환함
		return map;
	}
	
	@PostMapping
	public Guestbook write(@RequestBody Guestbook guestbook,
			HttpServletRequest request) {
		String clientIp = request.getRemoteAddr();
		
		// id가 입력된 guestbook이 반환된다.
		Guestbook resultGuestbook = guestbookService.addGuestbook(guestbook, clientIp);
		
		// 클라이언트로 갈 때는 json으로 전송됨
		return resultGuestbook;
	}
	
	@DeleteMapping("/{id}")
	public Map<String, String> delete(@PathVariable(name="id") Long id,
			HttpServletRequest request) {
		String clientIp = request.getRemoteAddr();
		
		int deleteCount = guestbookService.deleteGuestbook(id, clientIp);
		return Collections.singletonMap("success", deleteCount > 0 ? "true" : "false");
	}
}
