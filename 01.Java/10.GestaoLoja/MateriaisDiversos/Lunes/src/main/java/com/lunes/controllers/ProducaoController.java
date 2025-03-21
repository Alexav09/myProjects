package com.lunes.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lunes.model.entities.Producao;
import com.lunes.model.repositories.ProducaoRepository;
import com.lunes.model.repositories.ProdutoRepository;



@RestController
@RequestMapping("/producao")
public class ProducaoController {

    @Autowired
    private ProducaoRepository producaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarProducao(@RequestBody Producao producao) {
        // Verifique se o produto existe
        if (!produtoRepository.existsByNomeProduto(producao.getNomeProduto())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto não cadastrado.");
        }
        producaoRepository.save(producao);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produção salva com sucesso!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producao> obterProducaoPorId(@PathVariable Integer id) {
        Optional<Producao> producao = producaoRepository.findById(id);
        return producao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public Iterable<Producao> obterProducoes() {
        return producaoRepository.findAll();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarProducao(@PathVariable Integer id, @RequestBody Producao producaoDetails) {
        Optional<Producao> optionalProducao = producaoRepository.findById(id);
        if (optionalProducao.isPresent()) {
            Producao producao = optionalProducao.get();
            producao.setNomeProduto(producaoDetails.getNomeProduto());
            producao.setQtdProducao(producaoDetails.getQtdProducao());
            producao.setTamanhoProducao(producaoDetails.getTamanhoProducao());
            producao.setDescricaoProducao(producaoDetails.getDescricaoProducao());
            producao.setDataProducao(producaoDetails.getDataProducao());
            producao.setSituacaoProducao(producaoDetails.isSituacaoProducao());
            producao.setPerdaProducao(producaoDetails.getPerdaProducao());
            producao.setQtdParaEstoqueProducao(producaoDetails.getQtdParaEstoqueProducao());
            producaoRepository.save(producao);
            return ResponseEntity.ok("Produção atualizada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produção não encontrada.");
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirProducao(@PathVariable Integer id) {
        if (producaoRepository.existsById(id)) {
            producaoRepository.deleteById(id);
            return ResponseEntity.ok("Produção excluída com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produção não encontrada.");
        }
    }
}
