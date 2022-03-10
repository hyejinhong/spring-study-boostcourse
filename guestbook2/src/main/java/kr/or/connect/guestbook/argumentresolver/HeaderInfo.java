package kr.or.connect.guestbook.argumentresolver;

import java.util.HashMap;
import java.util.Map;

// ArgumentResolver에서 Map을 직접 사용할 수 없어 만든 클래서
public class HeaderInfo {
	private Map<String, String> map;
	
	public HeaderInfo() {
		map = new HashMap<>();
	}
	
	public void put(String name, String value) {
		map.put(name, value);
	}
	
	public String get(String name) {
		return map.get(name);
	}
}
