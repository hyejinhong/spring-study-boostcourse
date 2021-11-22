package kr.or.connect.guestbook.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration // 저 설정파일입니다.
@ComponentScan(basePackages = {"kr.or.connect.guestbook.dao", "kr.or.connect.guestbook.service"})
@Import({DBConfig.class}) // DBConfig 거를 써야하니깐 임포트합니다
public class ApplicationConfig {

}
