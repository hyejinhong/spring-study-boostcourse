package kr.or.connect.guestbook.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.guestbook.dao.GuestbookDao;
import kr.or.connect.guestbook.dao.LogDao;
import kr.or.connect.guestbook.dto.Guestbook;
import kr.or.connect.guestbook.dto.Log;
import kr.or.connect.guestbook.service.GuestbookService;

@Service
public class GuestbookServiceImpl implements GuestbookService {
	@Autowired
	GuestbookDao guestbookDao; // 선언만 하면 알아서 생성해서 주입해줌.
	
	@Autowired
	LogDao logDao;

	// gestbook 목록 가져오기
	@Override
	@Transactional // 내부적으로 ReadOnly 형태로 Connection을 사용합니다.
	public List<Guestbook> getGuestbooks(Integer start) {
		List<Guestbook> guestbooks = guestbookDao.selectAll(start, GuestbookService.LIMIT);
		return guestbooks;
	}

	// 방명록 삭제하기
	@Override
	@Transactional(readOnly=false)
	public int deleteGuestbook(Long id, String ip) {
		int ret = guestbookDao.deleteById(id); // 삭제 잘 되면 1, 아니면 0
		
		// 로그 남기기
		Log log = new Log();
		log.setIp(ip);
		log.setMethod("delete");
		log.setRegdate(new Date());
		logDao.insert(log);
		
		return ret; 
	}

	// 방명록 추가하기
	@Override
	@Transactional(readOnly=false)
	public Guestbook addGuestbook(Guestbook guestbook, String ip) {
		guestbook.setRegdate(new Date());
		Long id = guestbookDao.insert(guestbook);
		guestbook.setId(id);
		
		// 여기서 Exception이 일어난다면?
//		if(true) {
//			throw new RuntimeException("갑자기.");
//		}
		
		// 로그 남기기
		Log log = new Log();
		log.setIp(ip);
		log.setMethod("insert");
		log.setRegdate(new Date());
		logDao.insert(log);

		return guestbook;
	}

	// 전체 몇 건인지 반환
	@Override
	public int getCount() {
		return guestbookDao.selectCount();
	}
	
	
}
