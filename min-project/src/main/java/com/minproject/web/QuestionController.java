package com.minproject.web;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.minproject.domain.Question;
import com.minproject.domain.QuestionRepository;
import com.minproject.domain.User;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	QuestionRepository questionRepository; 
	
	@GetMapping("/form")
	public String form(HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "user/login";
		}
		return "qna/form";
	}
	
	@PostMapping("")
	public String create(HttpSession session, Question question) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "user/login";
		}
		
		System.err.println(question);
		
		User user = HttpSessionUtils.getUserFromSession(session);
		question.setIdx(UUID.randomUUID().toString());
		question.setUserId(user.getUserId());
		
		questionRepository.save(question);
		
		return "redirect:/";
	}
}
