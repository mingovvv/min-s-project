package com.minproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	@GetMapping("/helloWorld")
	public String welcome(String email, Model model, int age) {
		System.out.println("email : " + email);
		model.addAttribute("email", email);
		model.addAttribute("age", age);
		return "welcome";
	}
}
