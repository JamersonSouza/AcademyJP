package com.academy.model;

public enum StatusAlunoEnum {
	
	
	AGUARDANDOTURMA("Aguardando Turma"),
	DESISTENTE("Aluno Desistente"),
	CONCLUIDO("Concluido");
	
	
	private final String status;
	StatusAlunoEnum(String status){
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	
}
