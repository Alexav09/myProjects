package com.lunes.model.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Producao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProducao;
	@NotBlank
	private String nomeProduto;
	private int qtdProducao;
	private String tamanhoProducao;
	private String descricaoProducao;
	private LocalDate dataProducao;
	private boolean situacaoProducao;
	private int perdaProducao;
	private int qtdParaEstoqueProducao;

	public Producao() {

	}
	public Producao(@NotBlank String nomeProduto, int qtdProducao, String tamanhoProducao, String descricaoProducao,
			LocalDate dataProducao, boolean situacaoProducao, int perdaProducao, int qtdParaEstoqueProducao) {
		super();
		this.nomeProduto = nomeProduto;
		this.qtdProducao = qtdProducao;
		this.tamanhoProducao = tamanhoProducao;
		this.descricaoProducao = descricaoProducao;
		this.dataProducao = dataProducao;
		this.situacaoProducao = situacaoProducao;
		this.perdaProducao = perdaProducao;
		this.qtdParaEstoqueProducao = qtdParaEstoqueProducao;
	}
	public int getIdProducao() {
		return idProducao;
	}
	public void setIdProducao(int idProducao) {
		this.idProducao = idProducao;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public int getQtdProducao() {
		return qtdProducao;
	}
	public void setQtdProducao(int qtdProducao) {
		this.qtdProducao = qtdProducao;
	}
	public String getTamanhoProducao() {
		return tamanhoProducao;
	}
	public void setTamanhoProducao(String tamanhoProducao) {
		this.tamanhoProducao = tamanhoProducao;
	}
	public String getDescricaoProducao() {
		return descricaoProducao;
	}
	public void setDescricaoProducao(String descricaoProducao) {
		this.descricaoProducao = descricaoProducao;
	}
	public LocalDate getDataProducao() {
		return dataProducao;
	}
	public void setDataProducao(LocalDate dataProducao) {
		this.dataProducao = dataProducao;
	}
	public boolean isSituacaoProducao() {
		return situacaoProducao;
	}
	public void setSituacaoProducao(boolean situacaoProducao) {
		this.situacaoProducao = situacaoProducao;
	}
	public int getPerdaProducao() {
		return perdaProducao;
	}
	public void setPerdaProducao(int perdaProducao) {
		this.perdaProducao = perdaProducao;
	}
	public int getQtdParaEstoqueProducao() {
		return qtdParaEstoqueProducao;
	}
	public void setQtdParaEstoqueProducao(int qtdParaEstoqueProducao) {
		this.qtdParaEstoqueProducao = qtdParaEstoqueProducao;
	}






}
