package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessCardManagerDao {
	
	private final static String DB_URL = "jdbc:mysql://localhost:3306/cardmanager?characterEncoding=UTF-8&serverTimezone=UTC";
	private final static String DB_USER = "manager";
	private final static String DB_PASSWORD = "manager123!@#";
	
    public List<BusinessCard> searchBusinessCard(String keyword){
    	List<BusinessCard> cards = new ArrayList<>();

    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;

    	try {
    		// 드라이버 로드
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		// 연결
    		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

    		// 쿼리 작성, 수행
    		String sql = "SELECT name, phone, company_name, create_date "
    				+ "FROM business_card "
    				+ "WHERE name LIKE ?";
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, "%" + keyword + "%");
    		rs = ps.executeQuery();
    		
    		// 결과가 있는 동안
    		while(rs.next()) {
    			String name = rs.getString(1);
    			String phone = rs.getString(2);
    			String companyName = rs.getString(3);
    			
    			cards.add(new BusinessCard(name, phone, companyName));
    		}
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
    	
    	return cards;
    }

    public int addBusinessCard(BusinessCard businessCard){
    	int ret = 0;
    	
    	Connection conn = null;
    	PreparedStatement ps = null;
    	
    	try {
			// 드라이버 로드
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		// 연결
    		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    		
    		// 쿼리
    		String sql = "INSERT INTO business_card (name, phone, company_name, create_date) "
    				+ "VALUES (?, ?, ?, ?) ";
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, businessCard.getName());
    		ps.setString(2, businessCard.getPhone());
    		ps.setString(3, businessCard.getCompanyName());
    		ps.setDate(4, new Date(businessCard.getCreateDate().getTime()));
    		
    		ret = ps.executeUpdate();
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(conn != null) {
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
