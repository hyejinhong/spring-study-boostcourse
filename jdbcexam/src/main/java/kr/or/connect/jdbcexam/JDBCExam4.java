package kr.or.connect.jdbcexam;

import kr.or.connect.jdbcexam.dao.RoleDao;

public class JDBCExam4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 삭제 테스트
		int roleId = 500;
		
		RoleDao dao = new RoleDao();
		int deleteCount = dao.deleteRole(roleId);
		System.out.println(deleteCount);
	}

}
