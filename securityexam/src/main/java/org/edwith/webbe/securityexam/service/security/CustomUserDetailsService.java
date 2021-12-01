package org.edwith.webbe.securityexam.service.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserDbService userdbService;

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		// DB에서 user 정보를 읽어 UserEntity 객체에 저장한다.
		UserEntity customUser = userdbService.getUser(loginId);
		if(customUser == null) {
			throw new UsernameNotFoundException("사용자가 입력한 아이디에 해당하는 사용자를 찾을 수 없습니다.");
		}
		
		// 해당 정보를 CustomUserDetails 객체로 전달한다.
		CustomUserDetails customUserDetails = new CustomUserDetails();
		customUserDetails.setUsername(customUser.getLoginUserId());
		customUserDetails.setPassword(customUser.getPassword());
		
		// 해당 사용자의 권한 정보를 리스트로 받는다.
		List<UserRoleEntity> customRoles = userdbService.getUserRoles(loginId);
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		if(customRoles != null) {
			// 모든 권한에 대하여...
			for(UserRoleEntity customRole : customRoles) {
				// authorities 정보에 하나씩 추가해준다.
				// MemberRole 이름은 "ROLE_"로 시작해야 한다.
				authorities.add(new SimpleGrantedAuthority(customRole.getRoleName()));
			}
		}
		
		// CustomUserDetails 객체에 방금 받아온 권한 목록(authorities)를 설정한다.
		customUserDetails.setAuthorities(authorities);
		customUserDetails.setEnabled(true);
		customUserDetails.setAccountNonExpired(true);
		customUserDetails.setAccountNonLocked(true);
		customUserDetails.setCredentialsNonExpired(true);
		
		return customUserDetails;
	}
	
}
