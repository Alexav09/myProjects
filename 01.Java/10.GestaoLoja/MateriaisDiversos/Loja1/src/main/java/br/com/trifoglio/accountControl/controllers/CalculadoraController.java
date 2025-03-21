package br.com.trifoglio.accountControl.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/calculadora")
public class CalculadoraController {

	// /calculadora/somar/10/20
	@GetMapping(path = "/somar/{a}/{b}")
	public int somar(@PathVariable int a, @PathVariable int b) {
		return a + b;

	}
	// /calculadora/subtrair?a=100&b=39

//	Para usar esse método precisa ser enviado o valor no navegador da forma abaixo
//	http://localhost:8080/calculadora/subtrair?a=100&b=30

	@GetMapping(path = "/subtrair")
	public int subtrair(@RequestParam(name = "a") int a, @RequestParam(name = "b") int b) {
		return a - b;
	}

}
