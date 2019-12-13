package com.minproject.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

	private List<User> userList = new ArrayList<User>();
	
	// step1 : 파라미터 인자를 하나씩 받는 방법

//	@PostMapping("/create")
//	public String create(String userId, String userName, String userPassword) {
//		System.out.println("아이디 : " + userId + " 이름 : " + userName + " 비밀번호 : " + userPassword);
//		return "index";
//	}

	// step2 : 인자를 모델로 만들어서 한번에 받는 방법
	@PostMapping("/create")
	public String create(User user) {
		System.out.println(user);
		userList.add(user);
		return "redirect:/list";  
	}
	
	@GetMapping("/list")
	public String getUserList(Model model) {
		model.addAttribute("userList", userList);
		return "list";
	}
}
