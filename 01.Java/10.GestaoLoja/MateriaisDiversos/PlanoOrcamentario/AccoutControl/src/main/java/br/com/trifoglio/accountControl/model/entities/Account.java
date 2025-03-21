package br.com.trifoglio.accountControl.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

/**
 * Class the represents an Account.
 * 
 * @author Veca
 * @version 1.0
 */

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountId;
	@NotNull
	private String accountName;
	@NotNull
	private String accountType;

	private String accountDescription;
	
	private String accountSapId;
	
	private String accountPpmId;

	public Account(@NotNull String accountName, @NotNull String accountType, String accountDescription,
			String accountSapId, String accountPpmId) {
		super();
		this.accountName = accountName;
		this.accountType = accountType;
		this.accountDescription = accountDescription;
		this.accountSapId = accountSapId;
		this.accountPpmId = accountPpmId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public Account() {

	}

	// Getters and Setters
	public int getAccountId() {
		return accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountDescription() {
		return accountDescription;
	}

	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}

	public String getAccountSapId() {
		return accountSapId;
	}

	public void setAccountSapId(String accountSapId) {
		this.accountSapId = accountSapId;
	}

	public String getAccountPpmId() {
		return accountPpmId;
	}

	public void setAccountPpmId(String accountPpmId) {
		this.accountPpmId = accountPpmId;
	}

}
