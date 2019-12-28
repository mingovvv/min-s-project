package com.minproject.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.minproject.domain.Answer;
import com.minproject.domain.AnswerRepository;
import com.minproject.domain.Question;
import com.minproject.domain.QuestionRepository;
import com.minproject.domain.User;

@Controller
@RequestMapping("/questions/{idx}/answers")
public class AnswerController {

	@Autowired
	AnswerRepository answerRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@PostMapping("")
	public String create(@PathVariable String idx, HttpSession session, Answer answer) {
		// 로그인 되어있는지 여부
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "/users/login";
		}
		
		User user = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepository.getOne(idx);
		
		answer.setUnit(user, question);
		answerRepository.save(answer);
		
		return String.format("redirect:/questions/%s", idx);
	}
	
	
	
}
