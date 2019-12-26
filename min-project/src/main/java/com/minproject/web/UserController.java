package com.minproject.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.minproject.domain.User;
import com.minproject.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	// 등록 post
	@PostMapping("")
	public String create(User user) {
		userRepository.save(user);
		return "redirect:/users";  
	}
	
	// 목록 조회
	@GetMapping("")
	public String getUserList(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "user/list";
	}
	
	// 등록 get
	@GetMapping("/form")
	public String form() {
		return "user/form";
	}
	
	// 수정 get
	@GetMapping("/{rowNum}/form")
	public String updateForm(@PathVariable Long rowNum, Model model, HttpSession session) {
		
		// url을 통한 악의적인 수정 접근
		
		// Refactoring
		// 1. 로그인 session에 담긴 정보가 없다면 login-page 이동
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/login";
		}
		
		// Refactoring
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		
		// 2-1. 로그인 session에 담긴 정보가 있다면 session id값과 url {id}값의 동일 여부 확인 
		if(!rowNum.equals(sessionedUser.getRowNum())) {
			throw new IllegalStateException("다른 회원의 정보를 수정할 수 없습니다.");
		}else {
			model.addAttribute("user", userRepository.getOne(rowNum));
		}
		
		// 2-2. 로그인 session에 담긴 정보가 있다면 개인정보 수정 시, sessioned에 담긴 id값으로 수정
//		model.addAttribute("user", userRepository.getOne(sessionedUser.getRowNum()));
		
		return "user/updateForm";
	}
	
	// 수정 post
	@PostMapping("/{rowNum}")
	public String update(@PathVariable Long rowNum, User updateduser, HttpSession session) {
		
		// Refactoring
		// 1. 로그인 session에 담긴 정보가 없다면 login-page 이동
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/login";
		}

		// Refactoring
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		
		if(!rowNum.equals(sessionedUser.getRowNum())) {
			throw new IllegalStateException("다른 회원의 정보를 수정할 수 없습니다.");
		}
		
		updateduser.setRowNum(rowNum);
		userRepository.save(updateduser);
		return "redirect:/users";
	}
	
	// 로그인 get
	@GetMapping("/login")
	public String loginForm() {
		return "user/login";
	}
	
	// 로그인 post
	@PostMapping("/login")
	public String login(String userId, String userPassword, HttpSession session) {
		
		User user = userRepository.findByUserId(userId);
		
		if(user == null) {
			return "redirect:/users/login";
		}
		if(!user.matchPassword(userPassword)) {
			return "redirect:/users/login";
		}
		
		// session에 로그인 기록 저장
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
		
		return "redirect:/";
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
		return "redirect:/";
	}
}
