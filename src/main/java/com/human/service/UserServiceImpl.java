package com.human.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import com.human.domain.UserAuth;
import com.human.domain.Users;
import com.human.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public int join(Users user) throws Exception {
		
		String plainPw = user.getUserPw();
		String encodedPw = passwordEncoder.encode(plainPw);
		user.setUserPw(encodedPw);

		int result = mapper.join(user);

		String userId = user.getUserId();
		
		if( result > 0 ) {
			UserAuth userAuth = new UserAuth();
			userAuth.setUserId(userId);
			userAuth.setAuth("ROLE_USER");
			mapper.insertAuth(userAuth);
		}

		return result;
	}
	
	@Override
	public HttpSession tokenAuthenticaion(Users user, HttpServletRequest request) throws Exception {
		String username = user.getUserId();
		String password = user.getUserPwChk();
		log.info("username : " + username);
		log.info("password : " + password);
		
		HttpSession session = request.getSession();
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken( username, password );
		
		// 토큰에 요청정보를 등록
		token.setDetails( new WebAuthenticationDetails(request) );
		
		// 토큰을 이용하여 인증 (로그인)
		Authentication authentication = authenticationManager.authenticate(token);
		
		User authUser = (User) authentication.getPrincipal();
		log.info("인증된 사용자 아이디: " + authUser.getUsername());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return session;
	}
	
	// 프로필 사진
//	@Override
//	public int getProfileById(String userId) {
//	    int profile = mapper.getProfileById(userId);
//	    System.out.println("Profile value: " + profile);
//	    return profile;
//	}

//    @Override
//    public void updateProfile(String userId, int profile) {
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("userId", userId);
//        paramMap.put("profile", profile);
//        mapper.updateProfile(paramMap);
//    }
	
}