package com.service;

import java.sql.SQLException;
import com.dao.CustomerDao;
import com.daoimpl.CustomerDaoImpl;
import com.model.Customer;

public class CustomerService {
	CustomerDao customerDao = new CustomerDaoImpl();

	public int save(Customer customer) throws SQLException {
		return customerDao.createCustomer(customer);
	}
}
