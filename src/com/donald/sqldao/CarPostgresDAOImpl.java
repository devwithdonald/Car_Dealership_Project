package com.donald.sqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.donald.users.Car;
import com.donald.users.Customer;
import com.donald.util.ConnectionFactory;
import com.donald.util.LoggingUtil;

public class CarPostgresDAOImpl implements CarSQLDAO {

	private static Connection conn = ConnectionFactory.getConnection();

	@Override
	public void insertCar(Car car) {
		String sql = "insert into car(type, price) " + "values('?', '?');";

		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, car.getCarType());
			pstmt.setString(2, car.getPrice());
			int numberOfRows = pstmt.executeUpdate();

			LoggingUtil.debug(numberOfRows + " number of rows affected - insertCar");

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}

	}

	// only happens when car is bought
	@Override
	public void updateCar(Car car, Customer customer) {
		String sql = "update car "
				+ "set customer_id = ?, owner_username = '?', price = '?', type = '?', for_sale = ?, purchased_price = '?' "
				+ "where car_id = ?;";

		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, customer.getCustomerID());
			pstmt.setString(2, customer.getUsername());
			pstmt.setString(3, car.getPrice());
			pstmt.setString(4, car.getCarType());
			pstmt.setBoolean(5, car.getForSale());
			pstmt.setString(6, car.getPurchasedPrice());
			pstmt.setInt(7, car.getCarID());
			int numberOfRows = pstmt.executeUpdate();

			LoggingUtil.debug(numberOfRows + " number of rows affected - updateCar");

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}

	}

	@Override
	public List<Car> getAllCars() {
		List<Car> carList = new ArrayList<>();

		String sql = "select * from car;";

		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// create customer
				Car car = new Car(rs.getString("owner_username"), rs.getString("price"), rs.getString("type"),
						rs.getBoolean("for_sale"), rs.getInt("car_id"));

				car.setPurchasedPrice(rs.getString("purchased_price"));

				car.setCarOfferList(OfferPostgresDAOimpl.getOffersByCarID(car.getCarID()));

				carList.add(car);
			}

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}

		return carList;
	}

	@Override
	public List<Car> getCarsByCustomerId(int id) {
		List<Car> customerCarList = new ArrayList<>();

		String sql = "select * from car " + 
				"where customer_id = ?;";

		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Car car = new Car(rs.getString("owner_username"), rs.getString("price"), rs.getString("type"),
						rs.getBoolean("for_sale"), rs.getInt("car_id"));

				car.setPurchasedPrice(rs.getString("purchased_price"));

				car.setCarOfferList(OfferPostgresDAOimpl.getOffersByCarID(car.getCarID()));

				customerCarList.add(car);
			}

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}

		return customerCarList;
	}
	
	@Override
	public Car getCarById(int id) {
		Car car = null;

		String sql = "select * from car " + "where car_id = ?;";

		PreparedStatement pstmt;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				car = new Car(rs.getString("owner_username"), rs.getString("price"), rs.getString("type"),
						rs.getBoolean("for_sale"), rs.getInt("car_id"));

				car.setPurchasedPrice(rs.getString("purchased_price"));

				car.setCarOfferList(OfferPostgresDAOimpl.getOffersByCarID(car.getCarID()));
			}

		} catch (SQLException e) {
			LoggingUtil.error(e.getMessage());
		}

		return car;
	}

}
