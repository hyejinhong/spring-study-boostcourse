package org.edwith.webbe.securityexam.service.security;

public class UserRoleEntity {
	private String userLoginId;
	private String roleName;
	
	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public UserRoleEntity(String userLoginId, String roleName) {
		this.userLoginId = userLoginId;
		this.roleName = roleName;
	}
	
	
}
