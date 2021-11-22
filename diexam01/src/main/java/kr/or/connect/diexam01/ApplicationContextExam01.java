package kr.or.connect.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextExam01 {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		System.out.println("초기화 완료!!");
		
		UserBean userBean = (UserBean) ac.getBean("userBean");
		userBean.setName("김기범");
		System.out.println(userBean.getName());
		
		UserBean userBean2 = (UserBean) ac.getBean("userBean");
		if(userBean == userBean2) {
			// Spring Application Context가 싱글턴 패턴을 이용하기 때문에,
			// 객체를 계속 만드는 게 아니라 하나 만든 bean을 이용한다.
			System.out.println("같은 인스턴스입니다..");
		}
	}

}
