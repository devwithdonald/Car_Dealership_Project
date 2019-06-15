package com.donald.sqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.donald.users.Car;
import com.donald.users.Customer;
import com.donald.users.Offer;
import com.donald.util.ConnectionFactory;
import com.donald.util.LoggingUtil;

public class OfferPostgresDAOImpl implements OfferSQLDAO {
	
	private static Connection conn = ConnectionFactory.getConnection();
	
	//TODO does this work?
	private static CarPostgresDAOImpl carDAO = new CarPostgresDAOImpl();
	
	private static CustomerPostGresDAOImpl customerDAO = new CustomerPostGresDAOImpl();

	@Override
	public void insertOffer(Offer offer) {
		String sql = "insert into offer(customer_id, car_id, status_id, offer_price) " + 
				" values (?,?,?,?,?);";

		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, offer.getOfferer().getCustomerID());
			pstmt.setInt(2, offer.getOfferCar().getCarID());
			pstmt.setInt(3, 2); // 2 = pending offer in status table
			pstmt.setInt(4, offer.getOfferPrice());
			int numberOfRows = pstmt.executeUpdate();

			LoggingUtil.debug(numberOfRows + " number of rows affected - insertOffer");

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}

	}

	//when employee is accepting or declining (should give 1 or 3 depending on rejected or accepted
	@Override
	public void updateOffer(Offer offer, int statusId) {
		String sql = "update offer " + 
				"set status_id = ?, employee_decision_maker = ? " + 
				"where offer_id = ?;";

		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, statusId);
			pstmt.setInt(2, 1); // default for employee
			pstmt.setInt(3, offer.getOfferID());
			int numberOfRows = pstmt.executeUpdate();

			LoggingUtil.debug(numberOfRows + " number of rows affected - updateOffer");

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}

	}
	
	public void updateOfferOnAcceptance(int offerId, Customer buyer) {
		String sql = "update offer " + 
				"set status_id = 3, employee_decision_maker = 1, customer_id = ? " + 
				"where offer_id = ?;";

		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, buyer.getCustomerID());
			pstmt.setInt(2, offerId);
			int numberOfRows = pstmt.executeUpdate();

			LoggingUtil.debug(numberOfRows + " number of rows affected - updateOffer");

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}

	}
	
	//ALL CALRS when employee is accepting or declining (should give 1 or 3 depending on rejected or accepted
	@Override
	public void updateOfferOnCarRemoval(int carId) {
		String sql = "update offer " + 
				"set status_id = ?, employee_decision_maker = ? " + 
				"where car_id = ?;";

		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, 1); // default for employee
			pstmt.setInt(3, carId);
			int numberOfRows = pstmt.executeUpdate();

			LoggingUtil.debug(numberOfRows + " number of rows affected - updateOffer");

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}

	}
	
	@Override
	public void updateOfferOnRejection(int offerId) {
		String sql = "update offer " + 
				"set status_id = ?, employee_decision_maker = ? " + 
				"where offer_id = ?;";

		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, 1); // default for employee
			pstmt.setInt(3, offerId);
			int numberOfRows = pstmt.executeUpdate();

			LoggingUtil.debug(numberOfRows + " number of rows affected - updateOffer");

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}
	}

	
	@Override
	public List<Offer> getAllOffers() {
		List<Offer> offerList = new ArrayList<>();

		String sql = "select * from offer where status_id = 2;";

		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				
				Offer offer = new Offer(carDAO.getCarById(rs.getInt("car_id")), customerDAO.getCustomerById(rs.getInt("customer_id")), rs.getInt("offer_price"), rs.getInt("offer_id"));

				offerList.add(offer);
			}

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}

		return offerList;
	}

	@Override
	public List<Offer> getOffersByCustomerId(int id){
		List<Offer> customerOfferList = new ArrayList<>();

		String sql = "select * from offer " + 
				"where customer_id = ?;";

		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Offer offer = new Offer(carDAO.getCarById(rs.getInt("car_id")), customerDAO.getCustomerById(rs.getInt("customer_id")), rs.getInt("offer_price"), rs.getInt("offer_id"));

				customerOfferList.add(offer);
			}

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}

		return customerOfferList;
	}

	@Override
	public List<Offer> getOffersByCarId(int id){
		List<Offer> carOfferList = new ArrayList<>();

		String sql = "select * from offer " + 
				"where car_id = ? and status_id = 2";
		
		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Offer offer = new Offer(carDAO.getCarById(rs.getInt("car_id")), customerDAO.getCustomerById(rs.getInt("customer_id")), rs.getInt("offer_price"), rs.getInt("offer_id"));
				
				carOfferList.add(offer);
			}

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}

		return carOfferList;
	}
	
	@Override
	public Offer getOfferById(int id) {
		Offer offer = null;

		String sql = "select * from offer " + "where offer_id = ?;";

		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				offer = new Offer(carDAO.getCarById(rs.getInt("car_id")), customerDAO.getCustomerById(rs.getInt("customer_id")), rs.getInt("offer_price"), rs.getInt("offer_id"));

			}

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}

		return offer;
	}

}
