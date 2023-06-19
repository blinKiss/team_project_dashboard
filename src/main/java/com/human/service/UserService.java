package com.human.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.human.domain.MusicUsers;

public interface UserService {

	public int join(Users user) throws Exception;
	
	public HttpSession tokenAuthenticaion(Users user, HttpServletRequest reauest) throws Exception;
	
	// 프로필 사진
//	public int getProfileById(String userId);
//    void updateProfile(String userId, int profile);
}
