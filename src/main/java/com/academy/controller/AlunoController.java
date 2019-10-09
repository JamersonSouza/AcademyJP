package com.academy.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.academy.dao.AlunoDao;
import com.academy.model.Aluno;
import com.academy.model.UserLogin;
import com.academy.service.AlunoService;
import com.academy.service.ServiceException;

@Controller
public class AlunoController {

	@Autowired
	private AlunoService alunoservice;

	@Autowired
	private AlunoDao alunodao;

	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}

	@GetMapping("/")
	public ModelAndView login(@ModelAttribute UserLogin user) {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("user", new UserLogin());
		return mv;
	}

//	 ============== MÉTODOS PARA CADASTRO & CADASTRO-EDIT ==============
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
		if (errors.hasErrors()) {
			mv.addObject("erro", "erro");
			return mv;
		}
		mv.addObject("cadastroenviado", "enviado com sucesso");
		alunoservice.inserirAluno(aluno);
		System.out.println("Aluno foi inserido com sucesso " + aluno.getNome());
		return mv;
	}

	@PostMapping("/cadastro-completo")
	public ModelAndView CadastroConcluido(@Valid @ModelAttribute Aluno aluno, Errors errors) {
		ModelAndView mv = new ModelAndView("Cadastro-edit");
		mv.addObject("aluno", new Aluno());
		if (errors.hasErrors()) {
			mv.addObject("erro", "erro");
			return mv;
		}
		mv.addObject("msgEdit", "enviado com sucesso");
		alunoservice.inserirAluno(aluno);
		System.out.println("Aluno foi inserido com sucesso " + aluno.getNome());
		return mv;
	}

//	============= Métodos para Pesquisa ==========

	@GetMapping("/search")
	public ModelAndView search() {
		ModelAndView mv = new ModelAndView("search");
		mv.addObject("aluno", new Aluno());
		return mv;
	}

	@GetMapping("/search-concluidos")
	public ModelAndView alunosConcluidos() {
		ModelAndView mv = new ModelAndView("search-concluidos");
		mv.addObject("aluno", new Aluno());
		mv.addObject("AlunosConcluidos", this.alunoservice.listaConcluidos());
		return mv;
	}

	@GetMapping("/search-desistentes")
	public ModelAndView alunosDesistentes() {
		ModelAndView mv = new ModelAndView("search-desistentes");
		mv.addObject("aluno", new Aluno());
		mv.addObject("AlunosDesistentes", this.alunoservice.listaDesistentes());
		return mv;
	}

	@GetMapping("/search-aguardando-turma")
	public ModelAndView alunosAguardandoTurma() {
		ModelAndView mv = new ModelAndView("search-aguardando-turma");
		mv.addObject("aluno", new Aluno());
		mv.addObject("AlunosAguardandoTurma", this.alunoservice.listaAguardando());
		return mv;
	}

	@GetMapping("/search-em-andamento")
	public ModelAndView alunosEmAndamento() {
		ModelAndView mv = new ModelAndView("search-em-andamento");
		mv.addObject("aluno", new Aluno());
		mv.addObject("AlunosEmAndamento", this.alunoservice.listaAndamento());
		return mv;
	}

	@GetMapping("/search-All")
	public ModelAndView searchAll(@RequestParam(defaultValue = "1") int page) {
		ModelAndView mv = new ModelAndView("search-all");
		mv.addObject("aluno", new Aluno());
		Pageable pagreq = PageRequest.of(page - 1, 6, Sort.by("nome"));
		Page<Aluno> paginaResult = this.alunoservice.allAlunos(pagreq);
		mv.addObject("allAlunos", paginaResult);
		return mv;
	}

	@PostMapping("search-result")
	public ModelAndView resultPesquisa(@RequestParam(required = false) String nome) {
		ModelAndView mv = new ModelAndView("search-result");
		List<Aluno> nomeAlunos;

		if (nome == null || nome.trim().isEmpty()) {
			nomeAlunos = this.alunoservice.listarTodosAlunos(Sort.by("nome"));
		} else {
			nomeAlunos = this.alunoservice.buscaAlunos(nome);
		}

		mv.addObject("nomeAlunos", nomeAlunos);
		return mv;
	}

//	============ MÉTODO PARA REMOÇÃO DO ALUNO ==========

	@GetMapping("excluir-aluno")
	public ModelAndView removerAluno(@RequestParam Integer id, @RequestParam(defaultValue = "1") int page) {
		ModelAndView mv = new ModelAndView("search-all");
		mv.addObject("aluno", new Aluno());
		mv.addObject("msgExclusao", "Aluno Excluido");
		Pageable pagreq = PageRequest.of(page - 1, 6, Sort.by("nome"));
		Page<Aluno> paginaResult = this.alunoservice.allAlunos(pagreq);
		mv.addObject("allAlunos", paginaResult);
		this.alunoservice.excluirAluno(id);
		return mv;
	}

//	============ MÉTODO PARA EDIÇÃO DO ALUNO ==========

	@GetMapping("editar-aluno")
	public ModelAndView editarAluno(@RequestParam Integer id) {
		ModelAndView mv = new ModelAndView("Cadastro-edit");
		mv.addObject("aluno", new Aluno());
		this.alunoservice.editarAluno(id);
		this.alunoservice.excluirAluno(id);
		mv.addObject("msgedit", "editado com sucesso");
		return mv;
	}

//	============ EFETUAR LOGIN ============
	@PostMapping("/index")
	public ModelAndView login(@ModelAttribute UserLogin user, HttpSession session) {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("user", new UserLogin());
			UserLogin loginuser;
			try {
				loginuser = this.alunoservice.efetuarlogin(user.getEmail(), user.getSenha());
				session.setAttribute("userLogado", loginuser);
				session.getAttribute("userLogado");
			} catch (ServiceException e) {
				mv.addObject("msgLoginErro", e.getMessage());
				return mv;
			}
	
			
		return index();
	}
	
//	====== INSIRIR USUÁRIO =====
	@GetMapping("/cadastro-usuario")
	public ModelAndView usercadastro(@ModelAttribute UserLogin usuario) {
		ModelAndView mv = new ModelAndView("user-cadastro");
		mv.addObject("usuario", new UserLogin());
		return mv;
	}
	
	@PostMapping("user-cadastro")
	public ModelAndView cadastrousuario(@ModelAttribute UserLogin usuario) {
		ModelAndView mv = new ModelAndView("user-cadastro");
		mv.addObject("usuario", usuario);
		try {
			this.alunoservice.salvarUsuario(usuario);
		} catch (Exception e) {
			mv.addObject("erro", e.getMessage());
		}
		mv.addObject("conta", "contacriada");
		return mv;
	}
//	======= FINALIZAR SESSÃO ========= 
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}