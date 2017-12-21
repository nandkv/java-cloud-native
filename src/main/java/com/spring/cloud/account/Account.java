package com.spring.cloud.account;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.spring.cloud.data.BaseEntity;
import com.spring.cloud.loan.Loan;

@Entity
public class Account extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String username;

	private AccountNumber accountNumber;

	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinTable(name = "account_loan", 
		joinColumns = { @JoinColumn(name = "account_id") }, 
		inverseJoinColumns = {@JoinColumn(name = "loan_id") }
	)
	private Set<Loan> loans = new HashSet<>();
	 
	public Account() {
	}

	public Account(String username, AccountNumber accountNumber) {
		this.username = username;
		this.accountNumber = accountNumber;
	}
	
	public Account(String username, String accountNumber) {
		this.username = username;
		this.accountNumber = new AccountNumber(accountNumber);
	 }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public AccountNumber getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(AccountNumber accountNumber) {
		this.accountNumber = accountNumber;
	}

	//@JsonIgnore
	public Set<Loan> getLoans() {
		return loans;
	}

	public void setLoans(Set<Loan> loans) {
		this.loans = loans;
	}
	
	public void addLoan(Loan loan){
		loans.add(loan);
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", accountNumber=" + accountNumber + ", loans=" + loans
				+ "]";
	}
	
}
