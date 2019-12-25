package com.minproject.web;

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
	// step1 : 파라미터 인자를 하나씩 받는 방법

//	@PostMapping("/create")
//	public String create(String userId, String userName, String userPassword) {
//		System.out.println("아이디 : " + userId + " 이름 : " + userName + " 비밀번호 : " + userPassword);
//		return "index";
//	}

	// step2 : 인자를 모델로 만들어서 한번에 받는 방법
	@PostMapping("")
	public String create(User user) {
		userRepository.save(user);
		return "redirect:/users";  
	}
	
	@GetMapping("")
	public String getUserList(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "user/list";
	}
	
	@GetMapping("/form")
	public String form() {
		return "user/form";
	}
	
	// 수정 get
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model) {
		model.addAttribute("user", userRepository.getOne(id));
		return "user/updateForm";
	}
	
	// 수정 post
	@PostMapping("/{id}")
	public String update(@PathVariable Long id, User user) {
		user.setRowNum(id);
		userRepository.save(user);
		return "redirect:/users";
	}
}
