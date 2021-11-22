package kr.or.connect.jdbcexam;

import kr.or.connect.jdbcexam.dao.RoleDao;
import kr.or.connect.jdbcexam.dto.Role;

public class JDBCExam5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 수정 테스트
		int roleId = 102;
		String description = "Project Manager";
		
		Role role = new Role(roleId, description);
		RoleDao dao = new RoleDao();
		int updateCount = dao.updateRole(role);
		
		System.out.println(updateCount);
	}

}
