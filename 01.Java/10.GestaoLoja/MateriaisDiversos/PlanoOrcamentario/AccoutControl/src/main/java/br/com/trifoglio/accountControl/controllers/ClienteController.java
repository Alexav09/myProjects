package br.com.trifoglio.accountControl.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.trifoglio.accountControl.model.entities.Cliente;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

	@GetMapping(path = "/qualquer" )
	public Cliente obterCliente() {
		
		return new Cliente(28, "Pedro", "123.456.789-00");
		
	}
	
	@GetMapping("/{id}") 
//	neste caso estamos passando diretamente pela url o id
//	Para testar esse item como exemplo podemos - 
	
//	http://localhost:8080/clientes/10
	
//	Logo teremos o seguinte resultado:
//		{
//		  "id": 10,
//		  "nome": "Maria",
//		  "cpf": "987.654.321-00"
//		}
	
	public Cliente obterClientePorId1(@PathVariable int id) {
		return new Cliente(id, "Maria", "987.654.321-00");
	}
	
	@GetMapping
	public Cliente obterClientePorID2(
			@RequestParam(name = "id", defaultValue = "1") int id) {
		return new Cliente(id,"João Augusto", "111.222.333-44");
		
//		Para essa utilização temos 
//		http://localhost:8080/clientes?id=10
		
//		teremos como resultado
//		
//		{
//		  "id": 10,
//		  "nome": "oão Augusto",
//		  "cpf": "111.222.333-44"
//		}
		
			
			
	}
}
