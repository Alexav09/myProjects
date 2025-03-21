package com.lunes.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunes.controllers.ConsultaCEP;
import com.lunes.model.entities.Endereco;
import com.lunes.model.repositories.EnderecoRepository;



@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco buscarEnderecoPorCep(String cep) {
        String json = ConsultaCEP.buscarCep(cep);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, String> mapa = objectMapper.readValue(json, new TypeReference<Map<String, String>>() {});
            Endereco endereco = new Endereco();
            endereco.setCep(mapa.getOrDefault("cep", ""));
            endereco.setLogradouro(mapa.getOrDefault("logradouro", ""));
            endereco.setComplemento(mapa.getOrDefault("complemento", ""));
            endereco.setUnidade(mapa.getOrDefault("unidade", ""));
            endereco.setBairro(mapa.getOrDefault("bairro", ""));
            endereco.setLocalidade(mapa.getOrDefault("localidade", ""));
            endereco.setUf(mapa.getOrDefault("uf", ""));
            endereco.setEstado(mapa.getOrDefault("estado", ""));
            endereco.setRegiao(mapa.getOrDefault("regiao", ""));
            endereco.setIbge(mapa.getOrDefault("ibge", ""));
            endereco.setGia(mapa.getOrDefault("gia", ""));
            endereco.setDdd(mapa.getOrDefault("ddd", ""));
            endereco.setSiafi(mapa.getOrDefault("siafi", ""));
            return endereco;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter JSON para Endereco: " + e.getMessage(), e);
        }
    }

    public void salvarEnderecosPorCeps(List<String> ceps) {
        for (String cep : ceps) {
            try {
                Endereco endereco = buscarEnderecoPorCep(cep);
                save(endereco);
            } catch (Exception e) {
                System.err.println("Erro ao salvar o endereço para o CEP " + cep + ": " + e.getMessage());
            }
        }
    }

    public List<Endereco> buscarTodosEnderecos() {
        List<String> ceps = enderecoRepository.findAll().stream()
                .map(Endereco::getCep)
                .collect(Collectors.toList());

        return ceps.stream()
                .map(this::buscarEnderecoPorCep)
                .collect(Collectors.toList());
    }

    public List<String> listarTodosOsCeps() {
        return enderecoRepository.findAll().stream()
                .map(Endereco::getCep)
                .collect(Collectors.toList());
    }
}