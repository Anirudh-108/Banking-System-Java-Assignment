package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.dto.AccountDetails;
import com.model.Account;

public interface AccountDao {
	int createAccount(Account account) throws SQLException;
	double getAccountBalance(int accountNumber) throws SQLException;
	int deposit(int accountNumber,double amount) throws SQLException;
	double withdraw(int accountNumber,double amount) throws SQLException;
	double transfer(int senderAccountNumber,int receiverAccountNumber,double transferAmount) throws SQLException;
	List<Account> getAllAccounts() throws SQLException;
	double calculateInterest() throws SQLException;
	List<AccountDetails> getAccountsDetails() throws SQLException;
	boolean findAccount(int accountNumber) throws SQLException;
}
