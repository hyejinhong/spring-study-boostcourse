package org.edwith.webbe.securityexam.config;

import org.edwith.webbe.securityexam.service.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	// /webjars/** 경로에 대한 요청은 인증/인가 하지 않는다.
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**");
	}
	
	// userDetailsService로 이용할, 구현한 서비스를 설정한다.
	/*
	 AuthenticationFilter가 아이디/암호를 로그인해서 처리하는 필터
	 입력받은 아이디에 해당하는 정보를 DB에서 읽을 때 UserDetailsService를 구현한 객체를 이용함.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter, CsrfFilter.class);
		
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/", "/main", "/members/loginerror", "/members/joinform", "/members/join", "/members/welcome", "/members/join").permitAll() // /main은 누구나 가능
		.antMatchers("/securepage", "/members/**").hasRole("USER")
		.anyRequest().authenticated() // 그 외 요청은 인증 후 접근해야함
		.and()
			.formLogin()
			.loginPage("/members/loginform")
			// 로그인시 사용할 아이디, 비밀번호 설정
			// <input>의 name 파라미터와 같은 이름을 가져야 한다.
			.usernameParameter("userId")
			.passwordParameter("password")
			
			.loginProcessingUrl("/authenticate")
			.failureForwardUrl("/members/loginerror?login_error=1")
			.defaultSuccessUrl("/", true)
			.permitAll()
		.and()
			.logout()
			.logoutUrl("/logout") // 로그아웃 url 설정
			.logoutSuccessUrl("/");
	}
	
	// 암호 인코딩 or 인코딩 암호와 사용자 입력 암호 일치 확인 시 사용
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
