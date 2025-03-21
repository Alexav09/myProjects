package com.lunes.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lunes.model.entities.Cliente;



public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    // Método para encontrar clientes pelo nome, ignorando maiúsculas e minúsculas
    public Iterable<Cliente> findByNomeClienteContainingIgnoreCase(String parteNomeCliente);

    // Métodos para encontrar cliente pelo CPF e pelo nome e CPF
    Optional<Cliente> findByCpfCliente(String cpfCliente);
    Optional<Cliente> findByNomeClienteAndCpfCliente(String nomeCliente, String cpfCliente);

    // Consulta personalizada para buscar clientes pelo nome
    @Query("SELECT p FROM Cliente p WHERE p.nomeCliente LIKE %:nomeCliente%")
    public Iterable<Cliente> searchByNameLike(@Param("nomeCliente") String nomeCliente);
}
