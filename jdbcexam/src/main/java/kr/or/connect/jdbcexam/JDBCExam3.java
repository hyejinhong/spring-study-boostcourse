package kr.or.connect.jdbcexam;

import java.util.List;

import kr.or.connect.jdbcexam.dao.RoleDao;
import kr.or.connect.jdbcexam.dto.Role;

public class JDBCExam3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RoleDao dao = new RoleDao();
		List<Role> list = dao.getRoles();
		System.out.println(list);
		
		for(Role role : list) {
			System.out.println(role);
		}
	}

}
