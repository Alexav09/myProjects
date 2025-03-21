package com.lunes.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity

/**
 * @author Veca
 * @version 1.0
 */
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int usuarioId;
	@NotNull
	private String usuarioName;

	private String usuarioAcesso;

	private String usuarioFuncao;

	private String usuarioMatricula;
	@NotNull
	private String usuarioLogin;




	public Usuario(@NotNull String usuarioName, String usuarioAcesso, String usuarioFuncao, String usuarioMatricula,
			@NotNull String usuarioLogin) {
		super();
		this.usuarioName = usuarioName;
		this.usuarioAcesso = usuarioAcesso;
		this.usuarioFuncao = usuarioFuncao;
		this.usuarioMatricula = usuarioMatricula;
		this.usuarioLogin = usuarioLogin;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getUsuarioName() {
		return usuarioName;
	}

	public void setUsuarioName(String usuarioName) {
		this.usuarioName = usuarioName;
	}

	public String getUsuarioAcesso() {
		return usuarioAcesso;
	}

	public void setUsuarioAcesso(String usuarioAcesso) {
		this.usuarioAcesso = usuarioAcesso;
	}

	public String getUsuarioFuncao() {
		return usuarioFuncao;
	}

	public void setUsuarioFuncao(String usuarioFuncao) {
		this.usuarioFuncao = usuarioFuncao;
	}

	public String getUsuarioMatricula() {
		return usuarioMatricula;
	}

	public void setUsuarioMatricula(String usuarioMatricula) {
		this.usuarioMatricula = usuarioMatricula;
	}

	public String getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}








}
