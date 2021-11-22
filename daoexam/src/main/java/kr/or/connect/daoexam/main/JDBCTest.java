package kr.or.connect.daoexam.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.daoexam.config.ApplicationConfig;
import kr.or.connect.daoexam.dao.RoleDao;
import kr.or.connect.daoexam.dto.Role;

public class JDBCTest {

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		RoleDao roleDao = ac.getBean(RoleDao.class);
		
		Role role = new Role();
//		role.setRoleId(500);
//		role.setDescription("CEO");
//		int count = roleDao.insert(role);
//		System.out.println(count + "입력했습니다.");
		
//		role.setRoleId(101);
//		role.setDescription("Programmer");
//		int count = roleDao.update(role);
//		System.out.println(count + "건 수정했습니다.");
		
		Role resultRole = roleDao.selectById(201);
		System.out.println(resultRole);
		
		int count = roleDao.deleteById(500);
		System.out.println(count + "건 삭제했습니다..");
	}

}
