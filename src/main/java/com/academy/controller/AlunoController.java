package com.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlunoController {

	@GetMapping("/index")
	public String viewTeste() {
		return "index";
	}
	
}
