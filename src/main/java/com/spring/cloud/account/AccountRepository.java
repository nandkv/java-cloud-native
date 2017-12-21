package com.spring.cloud.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

	Account findAccountByUsername(String username);

	Account findAccountByAccountNumber(@Param("accountNumber") AccountNumber accountNumber);
		
}
