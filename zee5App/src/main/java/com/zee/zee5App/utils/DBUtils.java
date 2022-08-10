package com.zee.zee5App.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.zee.zee5App.exeptions.UnableToGenerateIdException;

public class DBUtils {
	private DBUtils() {

	}

	private static DBUtils dbUtils;

	public static DBUtils getInstance() {
		if (dbUtils == null) {
			return new DBUtils();
		}

		return dbUtils;
	}

	public Connection getConnection() {
//		to provide the connection
		Properties properties = loadProperties();

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(properties.getProperty("db.url"),
					properties.getProperty("db.username"), properties.getProperty("db.password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}

	public void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Properties loadProperties() {
		Properties properties = new Properties();

		InputStream inputStream = null;
		try {
			inputStream = DBUtils.class.getClassLoader().getResourceAsStream("application.properties");
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return properties;
	}

	public String idGenerator(String firstName, String lastName) throws UnableToGenerateIdException {
//		it is responsible to generate the userid for user entity
//		retrieve the value (db stored from idgen table)
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int updateResult = 0;
		int id = 0;
		String newId = "";

		String query = "select id from useridgenerator";

		connection = this.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				id = resultSet.getInt(1);

//		take the 1st char from firstName and lstName
				id++;
				newId = id + "" + firstName.charAt(0) + lastName.charAt(0);

//		then increment the number (id which is retrieved from DB)
				String updateQuery = "update useridgenerator set id=?";
				preparedStatement = connection.prepareStatement(updateQuery);
				preparedStatement.setInt(1, id);
				updateResult = preparedStatement.executeUpdate();

				if (updateResult == 0) {
					throw new UnableToGenerateIdException("unable to generate id since there is some SQL exception");
				}

				return newId;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UnableToGenerateIdException(
					"unable to generate id since there is some SQL exception" + e.getMessage());
		} finally {
			this.closeConnection(connection);
		}
		return newId;
	}

}
