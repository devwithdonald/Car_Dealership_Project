package com.donald.sqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.donald.users.Customer;
import com.donald.util.ConnectionFactory;
import com.donald.util.LoggingUtil;

public class CustomerPostGresDAOImpl implements CustomerSQLDAO {

	private static Connection conn = ConnectionFactory.getConnection();
	//TODO does this work?
	private static CarPostgresDAOImpl carDAO;
	private static PaymentPostgresDAOImpl paymentDAO;
	private static OfferPostgresDAOImpl offerDAO;
	
	// private connection
	// CAN CREATE CARDAO
	// private static CarDao carDao = osfpwo
	// carDao

	// in getAllCustomers
	// select * from customer
	// in the while loop
	// -- // basic stuff in customer table
	// -- create Customer c = new Customer(rs.getInt(1), rs.getString(2);
	// -- // now get list of cars
	// -- c.setCars(CarDao.getCarsByCustomerID(c.getID));

	@Override
	public void insertCustomer(Customer cust) {
		String sql = "insert into customer(username, password) " + "values('?', '?');";

		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cust.getUsername());
			pstmt.setString(2, cust.getPassword());
			int numberOfRows = pstmt.executeUpdate();

			LoggingUtil.debug(numberOfRows + " number of rows affected - insertCustomer");

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}

	}

	@Override
	public void updateCustomerOnAcceptedOffer(Customer cust) {

		String sql = "update customer " + "set making_payments = ?, balance = ?, monthly_payment = ? "
				+ "where customer_id = ?;";

		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setBoolean(1, cust.isMakingPayments());
			pstmt.setInt(2, cust.getBalance());
			pstmt.setDouble(3, cust.getMonthlyPayment());
			pstmt.setInt(4, cust.getCustomerID());
			int numberOfRows = pstmt.executeUpdate();

			LoggingUtil.debug(numberOfRows + " number of rows affected");

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}

	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customerList = new ArrayList<>();

		String sql = "select * from customer;";

		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// create customer
				Customer customer = new Customer(rs.getString(2), rs.getString(3));
				customer.setCustomerID(rs.getInt(1));
				customer.setMakingPayments(rs.getBoolean(4));
				customer.setBalance(rs.getInt(5));
				customer.setMonthlyPayment(rs.getDouble(6));

				// get carList
				customer.setCarsOwned(carDAO.getCarsByCustomerId(customer.getCustomerID()));

				// get pending offers
				customer.setPendingOffers(offerDAO.getOffersByCustomerId(customer.getCustomerID()));

				// get local payment list
				customer.setLocalPaymentList(paymentDAO.getPaymentsByCustomerId(customer.getCustomerID()));

				customerList.add(customer);
			}

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}

		return customerList;
	}


	@Override
	public Customer getCustomerById(int id) {
		Customer customer = null;

		String sql = "select * from customer " + "where customer_id = ?;";

		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				customer = new Customer(rs.getString(2), rs.getString(3));
				customer.setCustomerID(rs.getInt(1));
				customer.setMakingPayments(rs.getBoolean(4));
				customer.setBalance(rs.getInt(5));
				customer.setMonthlyPayment(rs.getDouble(6));

				// get carList
				//customer.setCarsOwned(carDAO.getCarsByCustomerId(customer.getCustomerID()));

				// get pending offers
				//customer.setPendingOffers(offerDAO.getOffersByCustomerId(customer.getCustomerID()));

				// get local payment list
				//customer.setLocalPaymentList(paymentDAO.getPaymentsByCustomerId(customer.getCustomerID()));
			}

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}

		return customer;
	}

}
