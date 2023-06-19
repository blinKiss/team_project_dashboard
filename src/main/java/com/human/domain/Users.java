package com.human.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Users {
	private int userNo;
	private String userId;
	private String userPw;
	private String userPwChk;
	private String name;
	private String gender;
	private int age;
	private int enabled;
	private LocalDateTime regDate;
	private LocalDateTime updDate;
}
