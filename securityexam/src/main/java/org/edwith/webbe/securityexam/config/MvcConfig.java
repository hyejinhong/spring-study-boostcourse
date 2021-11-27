package org.edwith.webbe.securityexam.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "org.edwith.webbe.securityexam.controller" })
public class MvcConfig implements WebMvcConfigurer {

	// Default Servlet 핸들러 설정
	// 원래는 모든 요청(/)을 처리하는 default servlet을 제공한다.
	// 스프링에서 설정한 path는 스프링이 처리하고, 스프링이 처리하지 못한 경로에 대한 처리는 디폴트 서블릿에게 전달하여 처리하게 된다.
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	// jsp 위치 설정
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view", "jsp");
	}

	// root path 요청시 main으로 리다이렉트
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/main");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources");
	}
}
