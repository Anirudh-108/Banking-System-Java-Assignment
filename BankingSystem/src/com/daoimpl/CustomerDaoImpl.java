package com.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dao.CustomerDao;
import com.dto.AccountDetails;
import com.model.Customer;
import com.utility.DBConnection;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public int createCustomer(Customer customer) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "insert into customer (customer_id,first_name, last_name, email, phone_number, address) values"
				+ "(?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, customer.getCustomerId());
		pstmt.setString(2, customer.getFirstName());
		pstmt.setString(3, customer.getLastName());
		pstmt.setString(4, customer.getEmail());
		pstmt.setString(5, customer.getPhoneNumber());
		pstmt.setString(6, customer.getAddress());
		int status = pstmt.executeUpdate();
		DBConnection.dbClose();
		return status;
	}

	@Override
	public int deposit(long accountNumber, double amount) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int transfer(long fromAccountNumber, long toAccountNumber, double amount) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AccountDetails getAccountDetails(long accountNumber) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
