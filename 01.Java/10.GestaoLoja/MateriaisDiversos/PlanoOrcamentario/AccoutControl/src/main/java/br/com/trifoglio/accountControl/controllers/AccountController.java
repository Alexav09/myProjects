package br.com.trifoglio.accountControl.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.trifoglio.accountControl.model.entities.Account;
import br.com.trifoglio.accountControl.model.repositories.AccountRepository;

@RestController
public class AccountController {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @PostMapping("/salvar")
    public String salvarConta(@RequestParam String accountName, 
                            @RequestParam String accountDescription,
                            @RequestParam String accountPpmId,
                            @RequestParam String accountSapId,
                            @RequestParam String accountType) {
        
        System.out.println("account_Name: " + accountName);
        System.out.println("accountDescription: " + accountDescription);
        System.out.println("accountPpmId: " + accountPpmId);
        System.out.println("accountSapId: " + accountSapId);
        System.out.println("accountType: " + accountType);
        
        // Verifica se uma conta com os mesmos IDs já existe
        Optional<Account> existingAccount = accountRepository.findByAccountPpmIdAndAccountSapId(accountPpmId, accountSapId);
        if (existingAccount.isPresent()) {
            return "Conta já existe. Por favor, insira dados diferentes.";
        }
        
        Account account = new Account(accountName, accountType, accountDescription, accountSapId, accountPpmId);
        accountRepository.save(account);
        return "Conta salva com sucesso!";
    }
    
    @GetMapping(path = "/contas")
    public Iterable<Account> obterContas() {
        return accountRepository.findAll();
    }
    
    @GetMapping(path = "/conta/id/{id}")
    public ResponseEntity<Account> obterContaPorId(@PathVariable int id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @GetMapping(path = "/conta/sap/{sapId}")
    public ResponseEntity<Account> obterContaPorSapId(@PathVariable String sapId) {
        Optional<Account> account = accountRepository.findByAccountSapId(sapId);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @GetMapping(path = "/conta/{parteAccountName}")
    public Iterable<Account> obterContaPorNome(@PathVariable String parteAccountName) {
        return accountRepository.findByAccountNameContainingIgnoreCase(parteAccountName);
    }
    
    @PutMapping(path = "/conta/atualizar/{id}")
    public ResponseEntity<String> atualizarConta(@PathVariable int id, @RequestBody Account accountDetails) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setAccountName(accountDetails.getAccountName());
            account.setAccountDescription(accountDetails.getAccountDescription());
            account.setAccountPpmId(accountDetails.getAccountPpmId());
            account.setAccountSapId(accountDetails.getAccountSapId());
            account.setAccountType(accountDetails.getAccountType());
            accountRepository.save(account);
            return ResponseEntity.ok("Conta atualizada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
        }
    }
    
    @PutMapping(path = "/conta/atualizarSap/{sapId}")
    public ResponseEntity<String> atualizarContaPorSapId(@PathVariable String sapId, @RequestBody Account accountDetails) {
        Optional<Account> optionalAccount = accountRepository.findByAccountSapId(sapId);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setAccountName(accountDetails.getAccountName());
            account.setAccountDescription(accountDetails.getAccountDescription());
            account.setAccountPpmId(accountDetails.getAccountPpmId());
            account.setAccountSapId(accountDetails.getAccountSapId());
            account.setAccountType(accountDetails.getAccountType());
            accountRepository.save(account);
            return ResponseEntity.ok("Conta atualizada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
        }
    }
    
    @DeleteMapping(path = "/conta/excluirId/{id}")
    public ResponseEntity<String> excluirContaPorId(@PathVariable int id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return ResponseEntity.ok("Conta deletada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
        }
    }
    
    @DeleteMapping(path = "/conta/excluirSap/{sapId}")
    public ResponseEntity<String> excluirContaPorSapId(@PathVariable String sapId) {
        Optional<Account> optionalAccount = accountRepository.findByAccountSapId(sapId);
        if (optionalAccount.isPresent()) {
            accountRepository.delete(optionalAccount.get());
            return ResponseEntity.ok("Conta deletada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
        }
    }
    
    @GetMapping(path = "/conta/pagina/{numeroPagina}/{qtdPagina}")
    public Iterable<Account> obterContasPorPagina(@PathVariable int numeroPagina, @PathVariable int qtdPagina) {
        if (qtdPagina >= 6) qtdPagina = 6;
        PageRequest page = PageRequest.of(numeroPagina, qtdPagina);
        return accountRepository.findAll(page);
    }
}
