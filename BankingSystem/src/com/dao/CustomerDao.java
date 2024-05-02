package com.dao;

import java.sql.SQLException;

import com.dto.AccountDetails;
import com.model.Customer;

public interface CustomerDao {
	int createCustomer(Customer customer) throws SQLException;
	int deposit(long accountNumber,double amount) throws SQLException;
	int transfer(long fromAccountNumber,long toAccountNumber,double amount) throws SQLException;
	AccountDetails getAccountDetails(long accountNumber) throws SQLException;
}
