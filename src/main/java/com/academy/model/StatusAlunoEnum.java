package com.academy.model;

public enum StatusAlunoEnum {
	
	
	AGUARDANDOTURMA("AGUARDANDOTURMA"),
	DESISTENTE("DESISTENTE"),
	CONCLUIDO("CONCLUIDO");
	
	
	private final String status;
	StatusAlunoEnum(String status){
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	
}
