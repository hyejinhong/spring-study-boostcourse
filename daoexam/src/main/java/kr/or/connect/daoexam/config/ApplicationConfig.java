package kr.or.connect.daoexam.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"kr.or.connect.daoexam.dao"})
@Import({DBConfig.class}) // 설정 파일을 여러 개로 나눠서 작성 가능
public class ApplicationConfig {

}
