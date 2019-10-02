package com.academy.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academy.model.Aluno;

public interface AlunoDao extends JpaRepository<Aluno, Integer> {
	
	@Query("select u from Aluno u")
	public Page<Aluno> AllAlunos(Pageable pagReq);
	
//	QUERY ABAIXO EQUIVALENTE AO ContainingIgnoreCase :p
//	@Query("select p from Aluno p where UPPER(p.nome) like UPPER(CONCAT('%', :nome, '%')) order by p.nome")
	public List<Aluno> findByNomeContainingIgnoreCase(String nome);
	
	@Query("select p from Aluno p where p.status = 'Concluido' ")
	public List<Aluno> findStatus();
	
	@Query("select p from Aluno p where p.status = 'Em-Andamento' ")
	public List<Aluno> findStatusAndamento();
	
	@Query("select p from Aluno p where p.status = 'Aguardando-Turma' ")
	public List<Aluno> findByStatusAguardando();
	
	@Query("select p from Aluno p where p.status = 'Desistente' ")
	public List<Aluno> findByStatusDesistentes();
}

