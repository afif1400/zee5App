package com.zee.zee5App.repo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.zee.zee5App.dto.Movie;
import com.zee.zee5App.dto.Series;
import com.zee.zee5App.enums.Genre;
import com.zee.zee5App.exeptions.InvalidNameException;
import com.zee.zee5App.exeptions.NoDataFoundException;
import com.zee.zee5App.exeptions.UnableToGenerateIdException;
import com.zee.zee5App.utils.DBUtils;

public class SeriesRepositoryImpl implements SeriesRepository {

	private DBUtils dbUtils = DBUtils.getInstance();

	private static SeriesRepository seriesRepository;

	public static SeriesRepository getInstance() {
		if (seriesRepository == null) {
			return new SeriesRepositoryImpl();
		}

		return seriesRepository;
	}

	@Override
	public Series insertSeries(Series series) throws UnableToGenerateIdException {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
//		connection object
		conn = dbUtils.getConnection();

		String insertStatement = "insert into series_table (seriesid, seriesname,"
				+ " genre,director, production, noofseasons, releasedate, languages)" + "values (?,?,?,?,?,?,?,?)";
		try {
			preparedStatement = conn.prepareStatement(insertStatement);
			preparedStatement.setString(1,
					dbUtils.idGenerator(series.getSeriesName(), series.getSeriesName().substring(1)));
//			actor array to comma separated string
			String languages = "";

			languages = String.join(",", series.getLanguages());
			preparedStatement.setString(2, series.getSeriesName());
			preparedStatement.setString(3, series.getGenre().toString());
			preparedStatement.setString(4, series.getDirector());
			preparedStatement.setString(5, series.getProduction());
			preparedStatement.setInt(6, series.getNoOfSeasons());
			preparedStatement.setDate(7, Date.valueOf(series.getReleaseDate()));
			preparedStatement.setString(8, languages);

			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				return series;
			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Series updateSeries(String seriesId, Series series) throws Exception {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
//		connection object
		conn = dbUtils.getConnection();

		String updateStatement = "update series_table set"
				+ "seriesname=?, genre=?, director=?, production=?, noofseasons=?," + "releasedate=?, languages=?";

		try {
			preparedStatement = conn.prepareStatement(updateStatement);
			preparedStatement.setString(1,
					dbUtils.idGenerator(series.getSeriesName(), series.getSeriesName().substring(1)));
//			actor array to comma separated string
			String languages = "";

			languages = String.join(",", series.getLanguages());
			preparedStatement.setString(2, series.getSeriesName());
			preparedStatement.setString(3, series.getGenre().toString());
			preparedStatement.setString(4, series.getDirector());
			preparedStatement.setString(5, series.getProduction());
			preparedStatement.setInt(6, series.getNoOfSeasons());
			preparedStatement.setDate(7, Date.valueOf(series.getReleaseDate()));
			preparedStatement.setString(8, languages);

			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				return series;
			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Optional<Series> getseriesBySeriesId(String seriesId) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

//		connection object
		conn = dbUtils.getConnection();

		String query = "select * from movie_table where seriesid=?";

		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, seriesId);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
//				record exits
//				inside the ResultSet object
//				User object from resultSet data.
				Series series = new Series();
				series.setSeriesName(resultSet.getString("seriesname"));
				series.setDirector(resultSet.getString("director"));
				series.setGenre(resultSet.getString("genre"));
				series.setLanguages(resultSet.getString("languages").split(","));
				series.setNoOfSeasons(resultSet.getInt("noofseasons"));
				series.setProduction(resultSet.getString("production"));
				series.setReleaseDate(resultSet.getDate("releasedate").toLocalDate());

				return Optional.of(series);
			} else {
				return Optional.empty();
			}
		} catch (SQLException | InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(conn);
		}

		return Optional.empty();
	}

	@Override
	public Optional<List<Series>> getAllSeries() {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

//		connection object
		conn = dbUtils.getConnection();

		String query = "select * from movie_table";

		try {
			preparedStatement = conn.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			List<Series> result = new ArrayList<>();

			while (resultSet.next()) {
//				record exits
//				inside the ResultSet object
//				User object from resultSet data.
				Series series = new Series();
				series.setSeriesName(resultSet.getString("seriesname"));
				series.setDirector(resultSet.getString("director"));
				series.setGenre(resultSet.getString("genre"));
				series.setLanguages(resultSet.getString("languages").split(","));
				series.setNoOfSeasons(resultSet.getInt("noofseasons"));
				series.setProduction(resultSet.getString("production"));
				series.setReleaseDate(resultSet.getDate("releasedate").toLocalDate());
				result.add(series);

			}
			return Optional.of(result);
		} catch (SQLException | InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(conn);
		}

		return Optional.empty();
	}

	@Override
	public Optional<List<Series>> getAllSeriesByGenre(String genre) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

//		connection object
		conn = dbUtils.getConnection();

		String query = "select * from movie_table where genre=?";

		try {
			preparedStatement = conn.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			preparedStatement.setString(1, genre);
			List<Series> result = new ArrayList<>();

			while (resultSet.next()) {
//				record exits
//				inside the ResultSet object
//				User object from resultSet data.
				Series series = new Series();
				series.setSeriesName(resultSet.getString("seriesname"));
				series.setDirector(resultSet.getString("director"));
				series.setGenre(resultSet.getString("genre"));
				series.setLanguages(resultSet.getString("languages").split(","));
				series.setNoOfSeasons(resultSet.getInt("noofseasons"));
				series.setProduction(resultSet.getString("production"));
				series.setReleaseDate(resultSet.getDate("releasedate").toLocalDate());
				result.add(series);

			}
			return Optional.of(result);
		} catch (SQLException | InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(conn);
		}

		return Optional.empty();
	}

	@Override
	public Optional<List<Series>> getAllSeriesByName(String seriesName) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

//		connection object
		conn = dbUtils.getConnection();

		String query = "select * from movie_table where seriesname=?";

		try {
			preparedStatement = conn.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			preparedStatement.setString(1, seriesName);
			List<Series> result = new ArrayList<>();

			while (resultSet.next()) {
//				record exits
//				inside the ResultSet object
//				User object from resultSet data.
				Series series = new Series();
				series.setSeriesName(resultSet.getString("seriesname"));
				series.setDirector(resultSet.getString("director"));
				series.setGenre(resultSet.getString("genre"));
				series.setLanguages(resultSet.getString("languages").split(","));
				series.setNoOfSeasons(resultSet.getInt("noofseasons"));
				series.setProduction(resultSet.getString("production"));
				series.setReleaseDate(resultSet.getDate("releasedate").toLocalDate());
				result.add(series);

			}
			return Optional.of(result);
		} catch (SQLException | InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(conn);
		}

		return Optional.empty();
	}

	@Override
	public String deleteSeriesBySeriesId(String SeriesId) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement preparedStatement = null;

//		connection object
		conn = dbUtils.getConnection();
		String deleteStatement = "delete from series_table where seriesid=?";

		preparedStatement = conn.prepareStatement(deleteStatement);
		preparedStatement.setString(1, SeriesId);
		int result = preparedStatement.executeUpdate();

		if (result > 0) {
			return "success";
		} else {
			throw new NoDataFoundException("seriesid not present");
		}
	}

}
