package com.academy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.academy.dao.AlunoDao;
import com.academy.model.Aluno;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoDao aluno;
	
	//Salvar Aluno no Bd
	public void inserirAluno(Aluno student){
		aluno.save(student);
	}
	
	//Listar Todos os Alunos 
	public Page<Aluno> allAlunos(Pageable pagReq){
		return aluno.AllAlunos(pagReq);
	}
}
