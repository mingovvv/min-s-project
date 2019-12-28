package com.minproject.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.minproject.domain.Question;
import com.minproject.domain.QuestionRepository;
import com.minproject.domain.Result;
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
	
	// 질문작성
	@PostMapping("")
	public String create(HttpSession session, Question question) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "user/login";
		}
		
		User user = HttpSessionUtils.getUserFromSession(session);
		question.setUnit(user);
		questionRepository.save(question);
		
		return "redirect:/";
	}
	
	// 질문 상세 보기
	@GetMapping("{idx}")
	public String detail(@PathVariable String idx, Model model) {
		model.addAttribute("question",questionRepository.getOne(idx));
		return "qna/detail";
	}
	
	// 질문 수정 창 보기
	@GetMapping("{idx}/form")
	public String updateForm(@PathVariable String idx, Model model, HttpSession session) {
		try {
			Question question = questionRepository.getOne(idx);
			hasPermission(session, question);
			model.addAttribute("questions", question);
			return "qna/updateForm";
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "user/login";
		}
	}
	
	// 질문 수정 하기
	@PostMapping("{idx}")
	public String update(@PathVariable String idx, Question updatedQuestion, Model model, HttpSession session) {
		try {
			Question question = questionRepository.getOne(idx);
			hasPermission(session, question);
			question.update(updatedQuestion.getTitle(),updatedQuestion.getContents());
			questionRepository.save(question);
			return String.format("redirect:/questions/%s", idx);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "user/login";
		}
	}
	
	// 질문 삭제 하기
	@PostMapping("{idx}/delete")
	public String delete(@PathVariable String idx, HttpSession session, Model model) {
		try {
			Question question = questionRepository.getOne(idx);
			hasPermission(session, question);
			questionRepository.deleteById(idx);
			return "redirect:/";
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "user/login";
		}
	}
	
	// 로그인 유무 / 작성자와 로그인 id 확인
	private boolean hasPermission(HttpSession session, Question question) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			throw new IllegalStateException("로그인이 필요한 서비스 입니다.");
		}
		
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!question.isSameUser(loginUser)) {
			throw new IllegalStateException("자신의 글만 수정, 삭제가 가능합니다.");
		}
		return true;
	}
	
	private Result valid(HttpSession session, Question question) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return Result.fail("로그인이 필요한 서비스 입니다.");
		}
		
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!question.isSameUser(loginUser)) {
			return Result.fail("자신의 글만 수정, 삭제가 가능합니다.");
		}
		return Result.success();
	}
	
}
