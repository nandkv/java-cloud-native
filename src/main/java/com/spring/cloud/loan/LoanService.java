package com.spring.cloud.loan;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanService {

	private final LoanRepository loanRepository;

	@Autowired
	public LoanService(LoanRepository lr) {
		this.loanRepository = lr;
	}

	@Transactional(readOnly = true)
	public Loan getLoanById(final String loanId) {
		return loanRepository.findOne(new Long(loanId));
	}
}