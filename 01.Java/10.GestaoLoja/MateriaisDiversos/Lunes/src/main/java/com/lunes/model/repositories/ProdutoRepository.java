package com.lunes.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lunes.model.entities.Produto;



public interface ProdutoRepository extends JpaRepository<Produto, Integer> {


	public Iterable<Produto> findByNomeProdutoContainingIgnoreCase(String parteNomeProduto);



	@Query("SELECT p FROM Produto p WHERE p.nomeProduto LIKE %:nomeProduto")
	public Iterable<Produto>searchByNameLike(@Param("nomeProduto") String nomeProduto);

	boolean existsByNomeProduto(String nomeProduto);





}
//

// Variáveis da Base Produto::
//
//
//	private int idProduto;
//	private String nomeProduto;
//	private double precoProduto;
//	private double precoTecido;
//	private double precoMaoObra;
//	private double precoEncargos;
//	private double precoCorteMolde;
//	private double descontoProduto;
//	private LocalDate dataCriacaoProduto;