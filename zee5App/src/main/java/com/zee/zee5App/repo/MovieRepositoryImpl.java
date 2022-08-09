package com.zee.zee5App.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.zee.zee5App.dto.Movie;
import com.zee.zee5App.dto.User;
import com.zee.zee5App.enums.Genre;
import com.zee.zee5App.exeptions.InvalidNameException;
import com.zee.zee5App.exeptions.NoDataFoundException;
import com.zee.zee5App.exeptions.UnableToGenerateIdException;
import com.zee.zee5App.utils.DBUtils;

public class MovieRepositoryImpl implements MovieRepository {

	private DBUtils dbUtils = DBUtils.getInstance();

	private static MovieRepository movieRepository;

	public static MovieRepository getInstance() {
		if (movieRepository == null) {
			return new MovieRepositoryImpl();
		}

		return movieRepository;
	}

	@Override
	public Movie insertMovie(Movie movie) throws UnableToGenerateIdException {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
//		connection object
		conn = dbUtils.getConnection();

		String insertStatement = "insert into movie_table (movieid, actors, moviename, director,"
				+ " genre, production, languages, movielength) " + "values (?,?,?,?,?,?,?,?)";
		try {
			preparedStatement = conn.prepareStatement(insertStatement);
			preparedStatement.setString(1, dbUtils.idGenerator(movie.getMovieName(),  movie.getMovieName().substring(1)));
//			actor array to comma separated string
			String actors = "";
			String languages = "";

			actors = String.join(",", movie.getActors());
			languages = String.join(",", movie.getLanguages());
			preparedStatement.setString(2, actors);
			preparedStatement.setString(3, movie.getMovieName());
			preparedStatement.setString(4, movie.getDirector());
			preparedStatement.setString(5, movie.getGenre().toString());
			preparedStatement.setString(6, movie.getProduction());
			preparedStatement.setString(7, languages);
			preparedStatement.setFloat(8, movie.getMovieLength());

			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				return movie;
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
	public Movie updateMovie(String movieId, Movie movie) throws Exception {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
//		connection object
		conn = dbUtils.getConnection();

		String updateStatement = "update movie_table set"
				+ "moviename=?, actors=?, director=?, genre=?, production=?, languages=?, movielength=?";

		try {
			preparedStatement = conn.prepareStatement(updateStatement);
//			actor array to comma separated string
			String actors = "";
			String languages = "";

			actors = String.join(",", movie.getActors());
			languages = String.join(",", movie.getLanguages());
			preparedStatement.setString(3, actors);
			preparedStatement.setString(2, movie.getMovieName());
			preparedStatement.setString(4, movie.getDirector());
			preparedStatement.setString(5, movie.getGenre().toString());
			preparedStatement.setString(6, movie.getProduction());
			preparedStatement.setString(7, languages);
			preparedStatement.setFloat(8, movie.getMovieLength());

			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				return movie;
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
	public Optional<Movie> getMovieByMovieId(String movieId) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

//		connection object
		conn = dbUtils.getConnection();

		String query = "select * from movie_table where movieid=?";

		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, movieId);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
//				record exits
//				inside the ResultSet object
//				User object from resultSet data.
				Movie movie = new Movie();
				movie.setActors(resultSet.getString("actors").split(","));
				movie.setMovieName(resultSet.getString("moviename"));
				movie.setDirector(resultSet.getString("director"));
				movie.setGenre(Genre.valueOf(resultSet.getString("genre")));
				movie.setLanguages(resultSet.getString("languages").split(","));
				movie.setMovieLength(resultSet.getFloat("movielength"));
				movie.setProduction(resultSet.getString("production"));

				return Optional.of(movie);
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
	public Optional<List<Movie>> getAllMovies() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Movie> result = new ArrayList<>();

//		connection object
		conn = dbUtils.getConnection();

		String query = "select * from movie_table";

		try {
			preparedStatement = conn.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
//				record exits
//				inside the ResultSet object
//				User object from resultSet data.
				Movie movie = new Movie();
				movie.setActors(resultSet.getString("actors").split(","));
				movie.setMovieName(resultSet.getString("moviename"));
				movie.setDirector(resultSet.getString("director"));
				movie.setGenre(Genre.valueOf(resultSet.getString("genre")));
				movie.setLanguages(resultSet.getString("languages").split(","));
				movie.setMovieLength(resultSet.getFloat("movielength"));
				movie.setProduction(resultSet.getString("production"));
				result.add(movie);
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
	public Optional<List<Movie>> getAllMoviesByGenre(String genre) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Movie> result = new ArrayList<>();

//		connection object
		conn = dbUtils.getConnection();

		String query = "select * from movie_table where genre=?";

		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, genre);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
//				record exits
//				inside the ResultSet object
//				User object from resultSet data.
				Movie movie = new Movie();
				movie.setActors(resultSet.getString("actors").split(","));
				movie.setMovieName(resultSet.getString("moviename"));
				movie.setDirector(resultSet.getString("director"));
				movie.setGenre(Genre.valueOf(resultSet.getString("genre")));
				movie.setLanguages(resultSet.getString("languages").split(","));
				movie.setMovieLength(resultSet.getFloat("movielength"));
				movie.setProduction(resultSet.getString("production"));
				result.add(movie);
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
	public Optional<List<Movie>> getAllMoviesByName(String movieName) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Movie> result = new ArrayList<>();

//		connection object
		conn = dbUtils.getConnection();

		String query = "select * from movie_table where moviename=?";

		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, movieName);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
//				record exits
//				inside the ResultSet object
//				User object from resultSet data.
				Movie movie = new Movie();
				movie.setActors(resultSet.getString("actors").split(","));
				movie.setMovieName(resultSet.getString("moviename"));
				movie.setDirector(resultSet.getString("director"));
				movie.setGenre(Genre.valueOf(resultSet.getString("genre")));
				movie.setLanguages(resultSet.getString("languages").split(","));
				movie.setMovieLength(resultSet.getFloat("movielength"));
				movie.setProduction(resultSet.getString("production"));
				result.add(movie);
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
	public List<Movie> findByOrderByMovieNameDsc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteMovieByMovieId(String movieId) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement preparedStatement = null;

//		connection object
		conn = dbUtils.getConnection();
		String deleteStatement = "delete from movie_table where movieid=?";

		preparedStatement = conn.prepareStatement(deleteStatement);
		preparedStatement.setString(1, movieId);
		int result = preparedStatement.executeUpdate();

		if (result > 0) {
			return "success";
		} else {
			throw new NoDataFoundException("userid not present");
		}
	}

}
