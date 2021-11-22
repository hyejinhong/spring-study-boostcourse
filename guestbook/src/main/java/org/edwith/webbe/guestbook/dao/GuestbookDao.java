package org.edwith.webbe.guestbook.dao;

import org.edwith.webbe.guestbook.dto.Guestbook;
import org.edwith.webbe.guestbook.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuestbookDao {

	private final static String DB_URL = "jdbc:mysql://localhost:3306/connectdb?characterEncoding=UTF-8&serverTimezone=UTC";
	private final static String DB_USER = "connectuser";
	private final static String DB_PASSWORD = "connect123!@#";

	// 방명록 가져오기
	public List<Guestbook> getGuestbooks() {
		List<Guestbook> books = new ArrayList<>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		conn = DBUtil.getConnection(DB_URL, DB_USER, DB_PASSWORD);

		String sql = "SELECT id, name, content, regdate FROM guestbook";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String content = rs.getString(3);
				Date regdate = rs.getDate(4);
				
				books.add(0, new Guestbook((long) id, name, content, regdate)); // 최근 작성한 것이 가장 앞에 오도록
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return books;
	}

	// 방명록 추가
	public int addGuestbook(Guestbook guestbook) {
		int ret = 0;

		Connection conn = null;
		PreparedStatement ps = null;

		conn = DBUtil.getConnection(DB_URL, DB_USER, DB_PASSWORD);

		String sql = "INSERT INTO guestbook (name, content, regdate)" + "VALUES(?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, guestbook.getName());
			ps.setString(2, guestbook.getContent());
			ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));

			ret = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return ret;
	}
}
