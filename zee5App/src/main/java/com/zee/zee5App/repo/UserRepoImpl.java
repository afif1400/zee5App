package com.zee.zee5App.repo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.zee.zee5App.dto.User;
import com.zee.zee5App.exeptions.NoDataFoundException;
import com.zee.zee5App.exeptions.UnableToGenerateIdException;
import com.zee.zee5App.utils.DBUtils;

public class UserRepoImpl implements UserRepo {

	private DBUtils dbUtils = DBUtils.getInstance();
	private static UserRepo userRepo;

	private UserRepoImpl() {

	}

	public static UserRepo getInstance() {
		if (userRepo == null) {
			return new UserRepoImpl();
		}

		return userRepo;
	}

	@Override
	public User insertUser(User user) throws UnableToGenerateIdException {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
//		connection object
		conn = dbUtils.getConnection();

//		statement object(Prepared)
		String insertStatement = "insert into user_table (userid, firstname,"
				+ " lastname, email, doj, dob, active) values(?,?,?,?,?,?,?)";
		try {
			preparedStatement = conn.prepareStatement(insertStatement);
			preparedStatement.setString(1, dbUtils.idGenerator(user.getFirstName(), user.getLastName()));
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setDate(5, Date.valueOf(user.getDoj()));
			preparedStatement.setDate(6, Date.valueOf(user.getDob()));
			preparedStatement.setBoolean(7, user.isActive());

//		we should get the result based on that we will return the result.
			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				return user;
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(conn);
		}

		return null;
	}

	@Override
	public Optional<User> updateUser(String userId, User user) throws Exception {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
//		connection object
		conn = dbUtils.getConnection();

//		statement object(Prepared)
		String updateStatement = "update user_table set firstname=?, lastname=?, " + "email=?, dob=?, doj=?, active=? where userid=?";

		try {
			preparedStatement = conn.prepareStatement(updateStatement);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setDate(4, Date.valueOf(user.getDoj()));
			preparedStatement.setDate(5, Date.valueOf(user.getDob()));
			preparedStatement.setBoolean(6, user.isActive());
			preparedStatement.setString(7, userId);
//		we should get the result based on that we will return the result.
			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				return Optional.of(user);
			} else {
				return Optional.empty();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(conn);
		}

		return null;
	}

	@Override
	public String deleteUser(String userId) throws Exception {
		Connection conn = null;
		PreparedStatement preparedStatement = null;

//		connection object
		conn = dbUtils.getConnection();
		String deleteStatement = "delete from user_table where userid=?";

		preparedStatement = conn.prepareStatement(deleteStatement);
		preparedStatement.setString(1, userId);
		int result = preparedStatement.executeUpdate();

		if (result > 0) {
			return "success";
		} else {
			throw new NoDataFoundException("userid not present");
		}

	}

	@Override
	public Optional<List<User>> getAllUsers() {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<User> result = new ArrayList<>();

//		connection object
		conn = dbUtils.getConnection();

		String query = "select * from user_table";

		try {
			preparedStatement = conn.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
//				record exits
//				inside the ResultSet object
//				User object from resultSet data.
				User user = new User();
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				user.setDob(resultSet.getDate("dob").toLocalDate());
				user.setDoj(resultSet.getDate("doj").toLocalDate());
				user.setActive(resultSet.getBoolean("active"));
				result.add(user);
			}

			return Optional.of(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(conn);
		}

		return Optional.empty();
	}

	@Override
	public Optional<User> getUserById(String userId) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

//		connection object
		conn = dbUtils.getConnection();

		String query = "select * from user_table where userid=?";

		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
//				record exits
//				inside the ResultSet object
//				User object from resultSet data.
				User user = new User();
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				user.setDob(resultSet.getDate("dob").toLocalDate());
				user.setDoj(resultSet.getDate("doj").toLocalDate());
				user.setActive(resultSet.getBoolean("active"));

				return Optional.of(user);
			} else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(conn);
		}

		return null;
	}

}
