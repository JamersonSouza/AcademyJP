package com.academy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.model.Aluno;
import com.academy.service.AlunoService;

@Controller
public class AlunoController {
	
	@Autowired
	private AlunoService alunoservice;
	
	@GetMapping("/index")
	public String viewTeste() {
		return "index";
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
	@GetMapping("/cadastro")
	public ModelAndView cadastroAluno(@ModelAttribute Aluno aluno) {
		ModelAndView mv = new ModelAndView("Cadastro");
		mv.addObject("aluno", aluno);
		return mv;
	}
	
	@PostMapping("/cadastrocompleto")
	public ModelAndView cadastroConcluido(@Valid @ModelAttribute Aluno aluno, Errors errors) {
		ModelAndView mv = new ModelAndView("Cadastro");
		mv.addObject("aluno", new Aluno());
		if(errors.hasErrors()) {
			mv.addObject("erro", "erro");
			return mv;
		}
		mv.addObject("cadastroenviado", "enviado com sucesso");
		alunoservice.inserirAluno(aluno);
		System.out.println("Aluno foi inserido com sucesso " + aluno.getNome());
		return mv;
	}
	
}
