package org.edwith.webbe.calculatorcli;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 컴포넌트 스캔한다.
		// 찾으면 인스턴스 생성하여 ApplicationContext가 관리한다.
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		// CalculatorService 객체 요청합니다.
		CalculatorService calculatorService = applicationContext.getBean(CalculatorService.class);
		System.out.println(calculatorService.plus(10, 40));
	}

}
