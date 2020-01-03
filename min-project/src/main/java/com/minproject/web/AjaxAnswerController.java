package com.minproject.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minproject.domain.Answer;
import com.minproject.domain.AnswerRepository;
import com.minproject.domain.Question;
import com.minproject.domain.QuestionRepository;
import com.minproject.domain.Result;
import com.minproject.domain.User;

//스프링 mvc가 알아서 create메소드를 json으로 변환해 주지 않는다. restcontroller 선언으로 인해 변환
@RestController
@RequestMapping("/ajax/questions/{idx}/answers")
public class AjaxAnswerController {

	@Autowired
	AnswerRepository answerRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@PostMapping("")
	public Answer create(@PathVariable String idx, HttpSession session, Answer answer) {

		// 로그인 되어있는지 여부
		if(!HttpSessionUtils.isLoginUser(session)) {
			return null;
		}
		
		User user = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepository.findById(idx).get();
		answer.setUnit(user, question);
		
		question.addAnswer();
		
		return answerRepository.save(answer);
	}
	
	@DeleteMapping("/{answerId}/delete")
	public Object delete(@PathVariable String idx, @PathVariable String answerId, HttpSession session) {
		
		// 로그인 되어있는지 여부
		if(!HttpSessionUtils.isLoginUser(session)) {
			return Result.fail("로그인 해야합니다.");
		}
		
		User user = HttpSessionUtils.getUserFromSession(session);
		Answer answer = answerRepository.findById(answerId).get();
		
		if(!answer.isSameUser(user)) {
			return Result.fail("작성자만 삭제할수 있습니다.");
		}
		answerRepository.deleteById(answerId);
		
		Question question = questionRepository.getOne(idx);
		question.deleteAnswer();
		questionRepository.save(question);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", Result.success());
		result.put("answer", answer);
		
		return result;
	}
	
}
