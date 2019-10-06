package com.academy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.academy.dao.AlunoDao;
import com.academy.dao.UserLoginDao;
import com.academy.model.Aluno;
import com.academy.model.UserLogin;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoDao aluno;
	
	@Autowired
	private UserLoginDao login;
	
	//Salvar Aluno no Bd
	public void inserirAluno(Aluno student){
		aluno.save(student);
	}
	
	//Listar Todos os Alunos 
	public Page<Aluno> allAlunos(Pageable pagReq){
		return aluno.AllAlunos(pagReq);
	}
	
	public List<Aluno> listarTodosAlunos(Sort sort){
		return aluno.findAll();
	}
	
	public List<Aluno> buscaAlunos(String nome){
		return aluno.findByNomeContainingIgnoreCase(nome);
	}
	
	public void excluirAluno(Integer id) {
		aluno.deleteById(id);
	}
	
	public void editarAluno(Integer id) {
		aluno.findById(id);
	}
	
	public List<Aluno> listaConcluidos(){
		return aluno.findStatus();
	}
	
	public List<Aluno> listaDesistentes(){
		return aluno.findByStatusDesistentes();
	}
	
	public List<Aluno> listaAguardando(){
		return aluno.findByStatusAguardando();
	}
	
	public List<Aluno> listaAndamento(){
		return aluno.findStatusAndamento();
	}
	
//	======== Fazer Login =======
	public UserLogin efetuarlogin(String email, String senha) throws ServiceException{
		UserLogin userlogin = this.login.findByLogin(email, senha);
		if(userlogin == null) {
			throw new ServiceException("Email ou senha inválidos!");
		}
		return userlogin;
	}
	
	public void salvarUsuario(UserLogin usuario) throws Exception{
		if(this.login.buscaEmail(usuario.getEmail()) != null) {
			throw new EmailException("email já existe no sistema");
		}else {
			this.login.save(usuario);
		}
	}
}
