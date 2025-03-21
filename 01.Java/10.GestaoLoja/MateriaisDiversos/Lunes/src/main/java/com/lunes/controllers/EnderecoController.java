package com.lunes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lunes.model.entities.Endereco;
import com.lunes.services.EnderecoService;





@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    //Não é possível listar todos os CEP's dessa forma
//    @GetMapping
//    public List<Endereco> getAllEnderecos() {
//        return enderecoService.findAll();
//    }

    @PostMapping
    public Endereco createEndereco(@RequestBody Endereco endereco) {
        return enderecoService.save(endereco);
    }

    @GetMapping("/cep/{cep}")
    public Endereco getEnderecoPorCep(@PathVariable String cep) {
        return enderecoService.buscarEnderecoPorCep(cep);
    }

//    @PostMapping("/salvar-por-ceps")
//    public void salvarEnderecosPorCeps(@RequestBody List<String> ceps) {
//        enderecoService.salvarEnderecosPorCeps(ceps);
//    }
//
//    @GetMapping("/ceps")
//    public List<String> listarTodosOsCeps() {
//        return enderecoService.listarTodosOsCeps();
//    }
//
//    @GetMapping("/completos")
//    public List<Endereco> listarTodosEnderecosCompletos() {
//        return enderecoService.buscarTodosEnderecos();
//    }



    // Outros endpoints
}

