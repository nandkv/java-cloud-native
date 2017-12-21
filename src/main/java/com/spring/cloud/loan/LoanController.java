package com.spring.cloud.loan;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("public")
public class LoanController {

	private LoanService loanService;

	public LoanController(LoanService loanService) {
		this.loanService = loanService;
	}

	@RequestMapping(value = "/loan/{id}")
	public ResponseEntity<Loan> getLoan(@PathVariable("id") String loanId) throws Exception {
		return Optional.ofNullable(loanService.getLoanById(loanId)).map(a -> new ResponseEntity<Loan>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Loan for id do not exist"));
	}
}
