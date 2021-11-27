package org.edwith.webbe.securityexam.service.security;

public class UserEntity {
	private String loginUerId;
	private String password;
	
	public UserEntity(String loginUerId, String password) {
		this.loginUerId = loginUerId;
		this.password = password;
	}
	
	public String getLoginUerId() {
		return loginUerId;
	}
	public void setLoginUerId(String loginUerId) {
		this.loginUerId = loginUerId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
