package com.spring.cloud.loan;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.cloud.account.Account;
import com.spring.cloud.data.BaseEntity;

@Entity
public class Loan extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(min = 12, max = 12, message = "Loan number must be an 12-digit string")
	@NotEmpty(message = "Loan number may not be empty")
	private String loanNumber;
	
	@Enumerated(EnumType.STRING)
	private LoanType type;

	@ManyToMany(mappedBy="loans", fetch=FetchType.LAZY, cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})  
	private Set<Account> accounts = new HashSet<>();

	public Loan() {
	}

	public Loan(String loanNumber, LoanType type) {
		this.loanNumber = loanNumber;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}

	public LoanType getType() {
		return type;
	}

	public void setType(LoanType type) {
		this.type = type;
	}

	@JsonIgnore
	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}	
	
	public void addAccount(Account account){
		this.accounts.add(account);
	}
	
}
