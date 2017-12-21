package com.spring.cloud.account;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.cloud.loan.Loan;
import com.spring.cloud.loan.LoanType;

@Service
public class AccountService {

	private final AccountRepository accountRepository;

	@Autowired
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Transactional(readOnly = true)
	public Account getUserAccount(final String username) {
		return accountRepository.findAccountByUsername(username);
	}
	
	@Transactional(readOnly = false)
	public Account saveAccount(final String username, final String loantype) {
		Account account = accountRepository.findAccountByUsername(username);
		if (null == account){
			account = new Account(username, String.valueOf(new Random().nextInt(90000000) + 100000000));
		}
		account.addLoan(new Loan(String.valueOf(new Random().nextInt(900000000) + 100000000000L), LoanType.valueOf(loantype)));
		return accountRepository.save(account);
	}
	
	@Transactional(readOnly = false)
	public void deleteAccount(final String username) {
		Optional<Account> optionalAccount = Optional.ofNullable(accountRepository.findAccountByUsername(username));
		if(optionalAccount.isPresent()){
			accountRepository.delete(optionalAccount.get());
		}
	}
}