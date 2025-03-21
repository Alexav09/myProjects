package com.lunes.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lunes.model.entities.Producao;



public interface ProducaoRepository extends JpaRepository<Producao, Integer> {


		public Iterable<Producao> findByDescricaoProducaoContainingIgnoreCase(String parteDescricaoProducao);
		public Iterable<Producao> findByNomeProdutoContainingIgnoreCase(String parteNomeProduto);



		@Query("SELECT p FROM Produto p WHERE p.nomeProduto LIKE %:nomeProduto")
		public Iterable<Producao>searchByNameLike(@Param("nomeProduto") String nomeProduto);

}
