package com.spring.cloud.account;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.cloud.loan.Loan;
import com.spring.cloud.loan.LoanType;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql({"/schema-test.sql", "/data-test.sql"})
public class AccountRepositoryTest {

	private static final AccountNumber ACCOUNT_NUMBER = new AccountNumber("198765432");

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testFindAccountByUsername() {
		this.entityManager.persist(new Account("nand", ACCOUNT_NUMBER));
		System.out.println(this.accountRepository.findAll());
		Account account = this.accountRepository.findAccountByUsername("nand");
		assertThat(account.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER);
		assertThat(account.getUsername()).isEqualTo("nand");
	}

	@Test
	public void testFindAccountByAccountNumber() {
		this.entityManager.persist(new Account("jack", ACCOUNT_NUMBER));
		Account account = this.accountRepository.findAccountByAccountNumber(ACCOUNT_NUMBER);
		assertThat(account.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER);
		assertThat(account.getUsername()).isEqualTo("jack");
	}
	
	@Test
	public void testAccountCreation() {
		Account account = new Account("nand", ACCOUNT_NUMBER);
		account.addLoan(new Loan("123456789012", LoanType.AUTO));
		this.entityManager.persist(account);
		Account result = this.accountRepository.findAccountByUsername("nand");
		assertThat(result.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER);
		assertThat(result.getUsername()).isEqualTo("nand");
	}

}
