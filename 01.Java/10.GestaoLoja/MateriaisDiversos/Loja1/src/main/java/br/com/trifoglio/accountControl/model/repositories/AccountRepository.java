package br.com.trifoglio.accountControl.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.trifoglio.accountControl.model.entities.Account;

public interface AccountRepository
		extends JpaRepository<Account, Integer>, PagingAndSortingRepository<Account, Integer> {

	public Iterable<Account> findByAccountNameContainingIgnoreCase(String parteAccountName);

	Optional<Account> findByAccountSapId(String accountSapId);

	Optional<Account> findByAccountPpmIdAndAccountSapId(String accountPpmId, String accountSapId);
	// Iterable<Account> findByAccountNameContainingIgnoreCase(String accountName);

	@Query("SELECT p FROM Account p WHERE p.accountName LIKE %:accountName")
	public Iterable<Account> searchByNameLike(@Param("accountName") String accountName);

}
