package com.human.service;

import com.human.domain.Users;

public interface UserService {

	public int join(Users user) throws Exception;
	
	
	// 프로필 사진
//	public int getProfileById(String userId);
//    void updateProfile(String userId, int profile);
}
