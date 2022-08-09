package com.zee.zee5App.repo;

import java.util.List;
import java.util.Optional;

import com.zee.zee5App.dto.Movie;
import com.zee.zee5App.exeptions.UnableToGenerateIdException;

public interface MovieRepository {
	public Movie insertMovie(Movie movie) throws UnableToGenerateIdException;
	
	public Movie updateMovie(String movieId, Movie movie) throws Exception;
	
	public Optional<Movie> getMovieByMovieId(String movieId);
	
	public Optional<List<Movie>> getAllMovies();
	public Optional<List<Movie>> getAllMoviesByGenre(String genre);
	public Optional<List<Movie>> getAllMoviesByName(String movieName);
	
	public List<Movie> findByOrderByMovieNameDsc();
	
	public String deleteMovieByMovieId(String movieId) throws Exception;
}
