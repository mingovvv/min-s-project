package com.minproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minproject.domain.Answer;
import com.minproject.domain.AnswerRepository;
import com.minproject.domain.User;
import com.minproject.domain.UserRepository;

@RestController
@RequestMapping("/json/users")
public class AjaxAUserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@GetMapping("/{idx}")
	public User detail(@PathVariable String idx) {
		return userRepository.getOne(idx);
	}
	
	@GetMapping("/min/{idx}")
	public Answer detail2(@PathVariable String idx) {
		return answerRepository.getOne(idx);
	}

	
}
