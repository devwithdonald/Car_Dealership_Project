package com.donald.sqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.donald.users.Customer;
import com.donald.users.Offer;
import com.donald.users.Payment;
import com.donald.util.ConnectionFactory;
import com.donald.util.LoggingUtil;

public class PaymentPostgresDAOImpl implements PaymentSQLDAO {

	private static Connection conn = ConnectionFactory.getConnection();
	//TODO does this work?
	private static CarPostgresDAOImpl carDAO = new CarPostgresDAOImpl();
	
	@Override
	public void insertPayment(Payment payment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePayment(Payment payment, Customer customer) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Payment> getAllPayments() {
		List<Payment> paymentList = new ArrayList<>();

		String sql = "select * from payment;";

		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				
				Payment payment = new Payment(rs.getInt("amount_paid"), carDAO.getCarById(rs.getInt("car_id")));

				paymentList.add(payment);
			}

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}

		return paymentList;

	}

	@Override
	public List<Payment> getPaymentsByCustomerId(int id) {
		List<Payment> customerPaymentList = new ArrayList<>();

		String sql = "select * from payment " + 
				"where customer_id = ?;";

		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// create customer
				Payment payment = new Payment(rs.getInt("amount_paid"), carDAO.getCarById(rs.getInt("car_id")));

				customerPaymentList.add(payment);
			}

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}

		return customerPaymentList;
	}

}
