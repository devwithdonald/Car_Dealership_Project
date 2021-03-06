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

	private static CarPostgresDAOImpl carDAO = new CarPostgresDAOImpl();
	private static PaymentPostgresDAOImpl paymentDAO = new PaymentPostgresDAOImpl();
	private static OfferPostgresDAOImpl offerDAO = new OfferPostgresDAOImpl();

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
	public void registerCustomer(String username, String password) {
		String sql = "insert into customer(username, password) " + "values(?, ?);";

		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
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

			}

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}

		return customer;
	}

	public Customer getCustomerByUsername(String username) {

		Customer customer = null;

		String sql = "select * from customer " + "where username = ?;";

		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				customer = new Customer(rs.getString("username"), rs.getString("password"));
				customer.setCustomerID(rs.getInt("customer_id"));
				customer.setMakingPayments(rs.getBoolean("making_payments"));
				customer.setBalance(rs.getInt("balance"));
				customer.setMonthlyPayment(rs.getDouble("monthly_payment"));

			}

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}

		return customer;
	}

}
