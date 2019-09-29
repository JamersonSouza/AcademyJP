package com.academy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.dao.AlunoDao;
import com.academy.model.Aluno;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoDao aluno;
	
	public void inserirAluno(Aluno student){
		aluno.save(student);
	}
}
