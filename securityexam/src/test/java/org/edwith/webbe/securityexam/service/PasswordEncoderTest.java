package org.edwith.webbe.securityexam.service;

import org.edwith.webbe.securityexam.config.ApplicationConfig;
import org.edwith.webbe.securityexam.config.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, SecurityConfig.class})
public class PasswordEncoderTest {

	@Autowired
	PasswordEncoder encoder;
	
	@Test
	public void passwordEncode() throws Exception {
		System.out.println(encoder.encode("12345"));
	}
	
	@Test
	public void passwordTest() throws Exception {
		String encodedPasswd = "$2a$10$BLc6LrJjEIFKdDcIGlRvtOqVS2Faq8sV/VjyThZrsfPqo5i6fHkHm";
		String password = "12345";
		
		boolean test = encoder.matches(password, encodedPasswd);
		System.out.println(test);
	}
}
