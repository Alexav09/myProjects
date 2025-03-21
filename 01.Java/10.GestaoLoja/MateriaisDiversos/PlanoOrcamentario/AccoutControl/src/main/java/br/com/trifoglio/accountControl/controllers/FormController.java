package br.com.trifoglio.accountControl.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {
	
	
	@GetMapping("/processForm")
	public String get() {
		return "resultado";
	}
	//Metodo para pegar os dados do formulario 
    @PostMapping("/processForm")
    public String processForm(@RequestParam("nome") String nome,
                              @RequestParam("idade") String idade,
                              @RequestParam("numero") Integer numero,
                              
                              Model model) {
    	nome = nome + " Veca";
    	//int soma = 0;
    	//int soma = Integer.parseInt(idade) + numero;
    	
    	// Adiciona os parâmetros ao modelo que é a pagina abaixo resultado.html 
        model.addAttribute("nome", nome);
        model.addAttribute("idade", idade);
        model.addAttribute("numero", numero);
        //model.addAttribute("soma", soma);

        // Retorna o nome da página HTML (sem a extensão .html)
        return "resultado";
    }
    
    
    
    
    
    
    
}