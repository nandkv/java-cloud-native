package com.spring.cloud.loan;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql({"/schema-test.sql", "/data-test.sql"})
public class LoanRepositoryTest {
	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testFindLoanById() {
		Loan autoLoan = new Loan("123456789012", LoanType.AUTO);
		this.entityManager.persist(autoLoan);
		Loan loan = this.loanRepository.findOne(autoLoan.getId());
		assertThat(loan.getType()).isEqualTo(LoanType.AUTO);
		assertThat(loan.getLoanNumber()).isEqualTo("123456789012");
	}

}
