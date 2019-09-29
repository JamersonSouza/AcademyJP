package com.academy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nome;
	private String curso;
	private String idade;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataCadastro;
	@Column(name = "Turno")
	private String horario;
	@Enumerated(EnumType.STRING)
	private StatusAlunoEnum status;
	private String tel;
	@Column(name = "telefone_alternativo")
	private String telAlt;
	
//	Getters e Setters
	
	
	public Integer getId() {
		return id;
	}
	public StatusAlunoEnum getStatus() {
		return status;
	}
	public void setStatus(StatusAlunoEnum status) {
		this.status = status;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
		this.idade = idade;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTelAlt() {
		return telAlt;
	}
	public void setTelAlt(String telAlt) {
		this.telAlt = telAlt;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
}
