package com.zee.zee5App.service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import com.zee.zee5App.dto.Movie;
import com.zee.zee5App.exeptions.UnableToGenerateIdException;

public interface MovieService {
	public Movie insertMovie(Movie movie) throws UnableToGenerateIdException, FileNotFoundException;
	
	public Movie updateMovie(String movieId, Movie movie) throws Exception;
	
	public Optional<Movie> getMovieByMovieId(String movieId);
	
	public Optional<List<Movie>> getAllMovies();
	public Optional<List<Movie>> getAllMoviesByGenre(String genre);
	public Optional<List<Movie>> getAllMoviesByName(String movieName);
	
	public String deleteMovieByMovieId(String movieId) throws Exception;
}
