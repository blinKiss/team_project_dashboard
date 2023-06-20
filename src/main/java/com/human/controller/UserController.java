package com.human.controller;

import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.human.domain.Users;
import com.human.mapper.UserMapper;
import com.human.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/auth/login")
	public String login( Model model
					  , @CookieValue(value="remember-id", required = false) Cookie cookie ) {
		
		String userId = "";
		boolean rememberId = false;
		
		// remember-id 쿠키가 존재하면,
		if( cookie != null ) {
			log.info("CookieName : " + cookie.getName() );
			log.info("CookieValue : " + cookie.getValue() );
			userId = cookie.getValue();		// 쿠키에 저장된 아이디
			rememberId = true;				// 아이디 저장 여부
		}
		
		model.addAttribute("userId", userId);
		model.addAttribute("rememberId", rememberId);
		
		return "/auth/login";
	}
	
	@GetMapping("/auth/join")
	public String joinForm() {
		
		return "/auth/join";
	}
	
	@PostMapping("/auth/join")
	public String join(Users user, RedirectAttributes rttr, HttpServletRequest request) throws Exception {
		log.info("user : " + user);
		
		int result = userService.join(user);

		
		rttr.addFlashAttribute("msg", user.getName() );
		
		return "redirect:/";
	}
	
	@GetMapping("/auth/success")
	public String success() {
	
		return "/auth/success";
	}
	
	// 아이디 중복 확인
	@Autowired
	private UserMapper userMapper;

	@GetMapping("/checkUserId")
	@ResponseBody
	public String checkUserId(@RequestParam("userId") String userId) throws Exception {
	    int result = userMapper.checkUserId(userId);
	    if (result == 1) {
	        return "fail";
	    } else {
	        return "success";
	    }
	}

	// 프로필 사진
	@GetMapping("/profile")
	@ResponseBody
	public int getProfileById(Principal principal) {
	    String userId = principal.getName(); // 로그인한 사용자의 아이디 가져오기
	    return userMapper.getProfileById(userId);
	}
	
	// 프로필 수정
	@PostMapping("/profile")
	@ResponseBody
	public boolean updateProfile(Principal principal, @RequestParam("profile") int profile) {
	    String userId = principal.getName(); // 로그인한 사용자의 아이디 가져오기
	    // profile 업데이트
	    int rowsUpdated = userMapper.updateProfile(userId, profile);
	    return rowsUpdated > 0;
	}



}
