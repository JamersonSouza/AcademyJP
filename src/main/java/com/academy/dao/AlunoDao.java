package com.academy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academy.model.Aluno;

public interface AlunoDao extends JpaRepository<Aluno, Integer> {

}
