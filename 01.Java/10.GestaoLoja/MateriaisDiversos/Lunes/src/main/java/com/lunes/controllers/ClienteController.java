package com.lunes.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lunes.model.entities.Cliente;
import com.lunes.model.repositories.ClienteRepository;


@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {


    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/salvar")


    public String salvarConta(@RequestParam String nomeCliente,
                            @RequestParam String cpfCliente,
                            @RequestParam String telefoneCliente,
                            @RequestParam String cepCliente) {

        System.out.println("nomeCliente: " + nomeCliente);
        System.out.println("cpfCliente: " + cpfCliente);
        System.out.println("telefoneCliente: " + telefoneCliente);
        System.out.println("cepCliente: " + cepCliente);

        // Verifica se uma conta com os mesmos IDs já existe
        Optional<Cliente> existingCliente = clienteRepository.findByNomeClienteAndCpfCliente(nomeCliente, cpfCliente);
        if (existingCliente.isPresent()) {
            return "Cliente já existe. Por favor, insira dados diferentes.";
        }

        Cliente cliente = new Cliente(nomeCliente, cpfCliente, telefoneCliente, cepCliente);
        clienteRepository.save(cliente);
        return "Cliente salva com sucesso!";
    }

    @GetMapping(path = "/clientes")
    public Iterable<Cliente> obterClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<Cliente> obterClientePorId(@PathVariable int id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping(path = "/cpf/{cpf}")
    public ResponseEntity<Cliente> obterContaPorSapId(@PathVariable String cpf) {
        Optional<Cliente> account = clienteRepository.findByCpfCliente(cpf);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping(path = "/nome/{parteNomeCliente}")
    public Iterable<Cliente> obterContaPorNome(@PathVariable String parteNomeCliente) {
        return clienteRepository.findByNomeClienteContainingIgnoreCase(parteNomeCliente);
    }

    @PutMapping(path = "/atualizar/{id}")
    public ResponseEntity<String> atualizarCliente(@PathVariable int id, @RequestBody Cliente clienteDetails) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            cliente.setNomeCliente(clienteDetails.getNomeCliente());
            cliente.setCpfCliente(clienteDetails.getCpfCliente());
            cliente.setCepCliente(clienteDetails.getCepCliente());
            cliente.setTelefoneCliente(clienteDetails.getTelefoneCliente());
            clienteRepository.save(cliente);
            return ResponseEntity.ok("Cliente atualizado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
    }

    @PutMapping(path = "/atualizarCpf/{cpf}")
    public ResponseEntity<String> atualizarClientePorCpf(@PathVariable String cpf, @RequestBody Cliente clienteDetails) {
        Optional<Cliente> optionalCliente = clienteRepository.findByCpfCliente(cpf);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            cliente.setNomeCliente(clienteDetails.getNomeCliente());
            cliente.setCpfCliente(clienteDetails.getCpfCliente());
            cliente.setCepCliente(clienteDetails.getCepCliente());
            cliente.setTelefoneCliente(clienteDetails.getTelefoneCliente());
            clienteRepository.save(cliente);
            return ResponseEntity.ok("Cliente atualizado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
    }

    @DeleteMapping(path = "/excluirId/{id}")
    public ResponseEntity<String> excluirContaPorId(@PathVariable int id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return ResponseEntity.ok("Cliente excluído com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
    }

    @DeleteMapping(path = "/excluirCpf/{cpf}")
    public ResponseEntity<String> excluirContaPorSapId(@PathVariable String cpf) {
        Optional<Cliente> optionalCliente = clienteRepository.findByCpfCliente(cpf);
        if (optionalCliente.isPresent()) {
            clienteRepository.delete(optionalCliente.get());
            return ResponseEntity.ok("Cliente excluído com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
    }

    @GetMapping(path = "/pagina/{numeroPagina}/{qtdPagina}")
    public Iterable<Cliente> obterContasPorPagina(@PathVariable int numeroPagina, @PathVariable int qtdPagina) {
        if (qtdPagina >= 6) {
			qtdPagina = 6;
		}
        PageRequest page = PageRequest.of(numeroPagina, qtdPagina);
        return clienteRepository.findAll(page);
    }
}
