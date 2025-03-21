package br.com.trifoglio.accountControl.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.trifoglio.accountControl.model.entities.Produto;
import br.com.trifoglio.accountControl.model.repositories.ProdutoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	

//	public @ResponseBody Produto novoProduto(
//			@RequestParam String nome,
//			@RequestParam double preco,
//			@RequestParam double desconto
//			) {
//		Produto produto = new Produto(nome,preco,desconto);
//		
//	pode ser substituído por, onde fica muito mais simples
	
	
	
	@PostMapping
	public @ResponseBody Produto novoProduto(@Valid Produto produto) {
		produtoRepository.save(produto);
		

		return produto;
	}
	
	@GetMapping
	public Iterable<Produto> obterProdutos() { 
//		ponto de atenção aqui trará 100% dos produtos o que pode degradar 
//		a aplicação em casos que exista muitos produtos, somente utilizar 
//		esse modelo para base de dados controlada com poucos dados 
		return produtoRepository.findAll();
	}
	
	//consulta para um id específico 
	@GetMapping(path = "/{id}") 
	public Optional<Produto> obterProdutoPorId(@PathVariable int id) {
		return produtoRepository.findById(id);
	}
	
	//consulta para parte do nome 
		@GetMapping(path = "/nome/{parteNome}") 
		public Iterable<Produto> obterProdutoPorNome(@PathVariable String parteNome) {
			return produtoRepository.findByNomeContainingIgnoreCase(parteNome);
//			return produtoRepository.searchByNameLike(parteNome); // funciona exatamente igual ao comando acima
		}
		
	//para realizarmos uma alteração 
	@PutMapping
	public Produto alterarProduto(@Valid Produto produto) {
		produtoRepository.save(produto);
		return produto;
	}
	
	
	//para excluir produto por id
	
//	Uma alternativa interessante para deletar itens no banco de dados 
//	é usar o softDelete, é igual ao Marcado para eliminacao, ou seja, 
//	não apaga realmente, nesse caso é importante garantir que ao exibir 
//	esses dados seja realizado um filtro sempre excluindo esses itens
	
	@DeleteMapping(path = "/{id}")
	public void excluirProdutoPorId(@PathVariable int id) {
		produtoRepository.deleteById(id);
	}
	
	
	//http://localhost:8080/api/produtos/pagina/2/3 - nesse caso vai exibir a pagina 3 pois inicia em 0 e 3 elementos
	
	@GetMapping(path = "/pagina/{numeroPagina}/{qtdPagina}")
	public Iterable<Produto> obterProdutosPorPagina(
			@PathVariable int numeroPagina, @PathVariable int qtdPagina){
		if(qtdPagina >=5) qtdPagina = 5;
		Pageable page = PageRequest.of(numeroPagina, qtdPagina);
		return produtoRepository.findAll(page);
		}
	
	
	
	
	
	
}
