package org.edwith.webbe.securityexam.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// Spring Config 파일 설정
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { ApplicationConfig.class, SecurityConfig.class };
	}

	// Spring WEB Config 파일 설정
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { MvcConfig.class };
	}

	// DispatcherServlet이 매핑되기 위한 패스(들)을 지정한다.
	// 즉, 모든 경로를 DispatcherServlet이 처리할 것
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	// 인코딩 필터 설정
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");

		return new Filter[] { encodingFilter };
	}
}
