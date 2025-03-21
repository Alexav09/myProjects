package com.lunes.model.entities;

import java.time.LocalDate;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity

public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProduto;
	@Nonnull
	private String nomeProduto;
	@Min(0)
	private double precoProduto;
	@Min(0)
	private double precoTecido;
	@Min(0)
	private double precoMaoObra;
	@Min(0)
	private double precoEncargos;
	@Min(0)
	private double precoCorteMolde;
	@Min(0)
	private double descontoProduto;
	private LocalDate dataCriacaoProduto;

	public Produto(@NotBlank String nomeProduto, @Min(0) double precoProduto, @Min(0) double precoTecido,
			@Min(0) double precoMaoObra, @Min(0) double precoEncargos, @Min(0) double precoCorteMolde,
			@Min(0) @Max(1) double descontoProduto, LocalDate dataCriacaoProduto) {
		super();
		this.nomeProduto = nomeProduto;
		this.precoProduto = precoProduto;
		this.precoTecido = precoTecido;
		this.precoMaoObra = precoMaoObra;
		this.precoEncargos = precoEncargos;
		this.precoCorteMolde = precoCorteMolde;
		this.descontoProduto = descontoProduto;
		this.dataCriacaoProduto = dataCriacaoProduto;
	}

	public Produto() {

	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public double getPrecoProduto() {
		return precoProduto;
	}

	public void setPrecoProduto(double precoProduto) {
		this.precoProduto = precoProduto;
	}

	public double getPrecoTecido() {
		return precoTecido;
	}

	public void setPrecoTecido(double precoTecido) {
		this.precoTecido = precoTecido;
	}

	public double getPrecoMaoObra() {
		return precoMaoObra;
	}

	public void setPrecoMaoObra(double precoMaoObra) {
		this.precoMaoObra = precoMaoObra;
	}

	public double getPrecoEncargos() {
		return precoEncargos;
	}

	public void setPrecoEncargos(double precoEncargos) {
		this.precoEncargos = precoEncargos;
	}

	public double getPrecoCorteMolde() {
		return precoCorteMolde;
	}

	public void setPrecoCorteMolde(double precoCorteMolde) {
		this.precoCorteMolde = precoCorteMolde;
	}

	public double getDescontoProduto() {
		return descontoProduto;
	}

	public void setDescontoProduto(double descontoProduto) {
		this.descontoProduto = descontoProduto;
	}

	public LocalDate getDataCriacaoProduto() {
		return dataCriacaoProduto;
	}

	public void setDataCriacaoProduto(LocalDate dataCriacaoProduto) {
		this.dataCriacaoProduto = dataCriacaoProduto;
	}

	public void calcularPrecoProduto() {
        this.precoProduto = ((this.precoTecido + this.precoMaoObra + this.precoEncargos + this.precoCorteMolde) * 1.2) - this.descontoProduto;
    }

//	public @Min(0) double somaProduto() {
//
//		precoProduto = precoTecido+precoCorteMolde+precoMaoObra+precoEncargos;
//
//		return precoProduto;
//	}

}