package com.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.AccountDao;
import com.dto.AccountDetails;
import com.model.Account;
import com.utility.DBConnection;

public class AccountDaoImpl implements AccountDao {

	@Override
	public int createAccount(Account account) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "insert into account (account_id,account_number, account_type, account_balance, customer_id) values (?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, account.getAccountId());
		pstmt.setLong(2, account.getAccountNumber());
		pstmt.setString(3, account.getAccountType());
		pstmt.setDouble(4, account.getAccountBalance());
		pstmt.setInt(5, account.getCustomerId());
		int status = pstmt.executeUpdate();
		DBConnection.dbClose();
		return status;
	}

	@Override
	public double getAccountBalance(int accountNumber) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select account_balance from account where account_number=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, accountNumber);
		ResultSet rs = pstmt.executeQuery();
		double accountBalance = -1;
		if (rs.next())
			accountBalance = rs.getDouble("account_balance");
		DBConnection.dbClose();
		return accountBalance;
	}

	@Override
	public int deposit(int accountNumber, double amount) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select account_balance from account where account_number=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, accountNumber);
		ResultSet rs = pstmt.executeQuery();
		double accountBalance = -1;
		if (rs.next())
			accountBalance = rs.getDouble("account_balance");
		accountBalance += amount;
		sql = "update account set account_balance=? where account_number=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setDouble(1, accountBalance);
		pstmt.setInt(2, accountNumber);
		int status = pstmt.executeUpdate();
		DBConnection.dbClose();
		return status;
	}

	@Override
	public double withdraw(int accountNumber, double amount) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select account_balance from account where account_number=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, accountNumber);
		ResultSet rs = pstmt.executeQuery();
		double accountBalance = -1;
		if (rs.next())
			accountBalance = rs.getDouble("account_balance");
		accountBalance -= amount;
		sql = "update account set account_balance=? where account_number=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setDouble(1, accountBalance);
		pstmt.setInt(2, accountNumber);
		pstmt.executeUpdate();
		DBConnection.dbClose();
		return amount;
	}

	@Override
	public double transfer(int senderAccountNumber, int receiverAccountNumber, double transferAmount)
			throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select account_balance from account where account_number=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, senderAccountNumber);
		ResultSet rs = pstmt.executeQuery();

		double senderAccountBalance = -1;
		if (rs.next())
			senderAccountBalance = rs.getDouble("account_balance");
		senderAccountBalance -= transferAmount;
		sql = "update account set account_balance=? where account_number=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setDouble(1, senderAccountBalance);
		pstmt.setInt(2, senderAccountNumber);
		pstmt.executeUpdate();

		sql = "select account_balance from account where account_number=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, receiverAccountNumber);
		rs = pstmt.executeQuery();
		double receiverAccountBalance = -1;
		if (rs.next())
			receiverAccountBalance = rs.getDouble("account_balance");
		receiverAccountBalance += transferAmount;
		sql = "update account set account_balance=? where account_number=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setDouble(1, receiverAccountBalance);
		pstmt.setInt(2, receiverAccountNumber);
		pstmt.executeUpdate();
		DBConnection.dbClose();
		return transferAmount;
	}

	@Override
	public List<Account> getAllAccounts() throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select * from account";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<Account> list = new ArrayList<>();
		while (rs.next()) {
			int accountId = rs.getInt("account_id");
			int accountNumber = rs.getInt("account_number");
			String accountType = rs.getString("account_type");
			double accountBalance = rs.getDouble("account_balance");
			int customerId = rs.getInt("customer_id");
			Account account = new Account(accountId, accountNumber, accountType, accountBalance, customerId);
			list.add(account);
		}
		DBConnection.dbClose();
		return list;
	}

	@Override
	public double calculateInterest() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AccountDetails> getAccountsDetails() throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select * from customer c JOIN account a ON c.customer_id=a.customer_id";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<AccountDetails> list = new ArrayList<>();
		while (rs.next()) {
			int customerId = rs.getInt("customer_id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String email = rs.getString("email");
			String phoneNumber = rs.getString("phone_number");
			String address = rs.getString("address");
			int accountId = rs.getInt("account_id");
			int accountNumber = rs.getInt("account_number");
			String accountType = rs.getString("account_type");
			double accountBalance = rs.getDouble("account_balance");
			AccountDetails accountDetails = new AccountDetails(customerId, firstName, lastName, email, phoneNumber,
					address, accountId, accountNumber, accountType, accountBalance);
			list.add(accountDetails);
		}
		DBConnection.dbClose();
		return list;
	}

	@Override
	public boolean findAccount(int accountNumber) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select account_number from account where account_number=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, accountNumber);
		ResultSet rs = pstmt.executeQuery();
		boolean status = rs.next();
		DBConnection.dbClose();
		return status;
	}
}
