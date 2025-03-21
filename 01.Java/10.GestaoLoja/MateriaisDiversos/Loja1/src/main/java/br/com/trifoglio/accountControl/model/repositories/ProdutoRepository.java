package br.com.trifoglio.accountControl.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.trifoglio.accountControl.model.entities.Produto;

public interface ProdutoRepository extends
		// CrudRepository<Produto, Integer> {
		JpaRepository<Produto, Integer> {

	public Iterable<Produto> findByNomeContainingIgnoreCase(String parteNome);

//	findByNomeContaining
//	findByNomeIsContaining
//	findByNomeContains
//	
//	findByNomeStartsWith
//	findByNomeEndsWith

//	findByNomeNotContaining

	@Query("SELECT p FROM Produto p WHERE p.nome LIKE %:nome")
	public Iterable<Produto> searchByNameLike(@Param("nome") String nome);

}
