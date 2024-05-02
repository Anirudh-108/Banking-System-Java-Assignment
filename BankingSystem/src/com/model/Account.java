package com.model;

public class Account {
	private int accountId;
	private long accountNumber;
	private String accountType;
	private double accountBalance;
	private int customerId;

	public Account() {
		super();
	}

	public Account(int accountId, long accountNumber, String accountType, double accountBalance, int customerId) {
		super();
		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.customerId = customerId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountNumber=" + accountNumber + ", accountType=" + accountType
				+ ", accountBalance=" + accountBalance + ", customerId=" + customerId + "]";
	}
}
