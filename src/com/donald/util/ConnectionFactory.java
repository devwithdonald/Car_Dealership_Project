package com.donald.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private static String url;
	private static String user;
	private static String password;
	private static final String PROPERTIES_FILE = "src/database.properties";
	private static ConnectionFactory cf;

	public static synchronized Connection getConnection() {

		if (cf == null) {
			LoggingUtil.debug("null Connection");
			cf = new ConnectionFactory();
		}
		LoggingUtil.debug("NOT null connection");
		return cf.createConnection();

	}

	private ConnectionFactory() {
		Properties prop = new Properties();
		try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE);) {

			prop.load(fis);
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");

		} catch (FileNotFoundException e) {

			LoggingUtil.error(e.getMessage());
		} catch (IOException e) {

			LoggingUtil.error(e.getMessage());
		}
	}

	private Connection createConnection() {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);
			LoggingUtil.info("successfully connected to DB");
			System.out.println("successfully connected!!!");
		} catch (SQLException e) {
			// log
			LoggingUtil.error("Failed to make DB connection");
			System.out.println("Failed to make DB Connection");
		}

		return conn;

	}
}
