package com.spring.cloud.account;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonValue;

public class AccountNumber {
	private String accountNumber;

	public AccountNumber(String accountNumber) {
		Assert.notNull(accountNumber, "Account Number must not be null");
		Assert.isTrue(accountNumber.length() == 9, "Account Number must be exactly 9 characters");
		this.accountNumber = accountNumber;
	}

	@JsonValue
	public String getAccountNumber() {
		return accountNumber;
	}

	@Override
	public String toString() {
		return accountNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountNumber other = (AccountNumber) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		return true;
	}
	
	
}
