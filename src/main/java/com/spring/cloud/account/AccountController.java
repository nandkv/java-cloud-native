package com.spring.cloud.account;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("private")
public class AccountController {
	   
	@Autowired
	private Environment env;
	
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping(value = "/account/{username}")
	public ResponseEntity<Account> getUserAccount(@PathVariable("username") String username) throws Exception {
		for(String p: env.getDefaultProfiles()){
			System.out.println(p);
		}
		for(String p: env.getActiveProfiles()){
			System.out.println(p);
		}	

		return Optional.ofNullable(accountService.getUserAccount(username))
				.map(a -> new ResponseEntity<Account>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Accounts for user do not exist"));
	}
	
	@RequestMapping(value = "/account/create/{username}/{loantype}")
	public ResponseEntity<Account> saveUserAccount(@PathVariable("username") String username, @PathVariable("loantype") String loantype) throws Exception {		
		return Optional.ofNullable(accountService.saveAccount(username, loantype))
				.map(a -> new ResponseEntity<Account>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Account could not be created"));
	}
	
	@RequestMapping(value = "/account/delete/{username}")
	public ResponseEntity<Void> deleteUserAccount(@PathVariable("username") String username) throws Exception {		
		accountService.deleteAccount(username);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
