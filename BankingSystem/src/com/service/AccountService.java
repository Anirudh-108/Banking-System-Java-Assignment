package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.AccountDao;
import com.daoimpl.AccountDaoImpl;
import com.dto.AccountDetails;
import com.exception.InsufficientFundException;
import com.exception.InvalidAccountException;
import com.model.Account;

public class AccountService {
	AccountDao accountDao = new AccountDaoImpl();

	public int save(Account account) throws SQLException {
		return accountDao.createAccount(account);
	}

	public double getAccountBalance(int accountNumber) throws InvalidAccountException, SQLException {
		boolean isValid = accountDao.findAccount(accountNumber);
		if (!isValid)
			throw new InvalidAccountException("Account number given is invalid!!!");
		return accountDao.getAccountBalance(accountNumber);
	}

	public int deposit(int accountNumber, double amount) throws InvalidAccountException, SQLException {
		boolean isValid = accountDao.findAccount(accountNumber);
		if (!isValid)
			throw new InvalidAccountException("Account number given is invalid!!!");
		return accountDao.deposit(accountNumber, amount);
	}

	public double withdraw(int accountNumber, double amount)
			throws InsufficientFundException, InvalidAccountException, SQLException {
		boolean isValid = accountDao.findAccount(accountNumber);
		if (!isValid)
			throw new InvalidAccountException("Account number given is invalid!!!");
		double availableAmount = accountDao.getAccountBalance(accountNumber);
		if (availableAmount < amount)
			throw new InsufficientFundException("Insufficient funds: Withdrawal amount exceeds available balance");
		return accountDao.withdraw(accountNumber, amount);
	}

	public double transfer(int senderAccountNumber, int receiverAccountNumber, double transferAmount)
			throws InsufficientFundException, InvalidAccountException, SQLException {
		boolean isValidSender = accountDao.findAccount(senderAccountNumber);
		boolean isValidReceiver = accountDao.findAccount(receiverAccountNumber);
		if (!isValidSender)
			throw new InvalidAccountException("Sender's account number given is invalid!!!");
		if (!isValidReceiver)
			throw new InvalidAccountException("Receiver's account number given is invalid!!!");
		double senderAccountBalance = accountDao.getAccountBalance(senderAccountNumber);
		if (senderAccountBalance < transferAmount)
			throw new InsufficientFundException("Insufficient funds: Tranfer amount exceeds available balance");
		return accountDao.transfer(senderAccountNumber, receiverAccountNumber, transferAmount);
	}

	public List<Account> getAllAccounts() throws SQLException {
		return accountDao.getAllAccounts();
	}

	public List<AccountDetails> getAccountsDetails() throws SQLException {
		return accountDao.getAccountsDetails();
	}

}