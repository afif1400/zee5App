package com.zee.zee5App.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import com.zee.zee5App.dto.Movie;
import com.zee.zee5App.exeptions.UnableToGenerateIdException;
import com.zee.zee5App.repo.MovieRepository;
import com.zee.zee5App.repo.MovieRepositoryImpl;

public class MovieServiceImpl implements MovieService {

	private MovieRepository movieRepository = MovieRepositoryImpl.getInstance();

	private static MovieService movieService;

	public static MovieService getInstance() {
		if (movieService == null) {
			movieService = new MovieServiceImpl();
		}

		return movieService;
	}

	@Override
	public Movie insertMovie(Movie movie) throws UnableToGenerateIdException, FileNotFoundException {
		// TODO Auto-generated method stub

		BufferedInputStream bufferedInputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
//		trailer file exists or not
		File file = new File(movie.getTrailer1());
		System.out.println(file.getName());
		byte[] buffer = new byte[2000];

		try {
			if (movie.getTrailer1() == null || movie.getTrailer1() == "" || !file.exists()) {
				throw new FileNotFoundException("file does not exists");
			} else {
//		shift that file to zee5App/trailer folder
//		provide the location to trailer field
				System.out.println("file exists");
				bufferedInputStream = new BufferedInputStream(new FileInputStream(file));

				bufferedOutputStream = new BufferedOutputStream(
						new FileOutputStream("D:\\zee5app\\trailer\\" + file.getName()));

				int numBytes;
				while((numBytes = bufferedInputStream.read(buffer)) != -1) {					
					bufferedOutputStream.write(buffer);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bufferedInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		then take the path and store it in db --> handled by REPO.

//		return movieRepository.insertMovie(movie);
		return null;
	}

	@Override
	public Movie updateMovie(String movieId, Movie movie) throws Exception {
		// TODO Auto-generated method stub
		return movieRepository.updateMovie(movieId, movie);
	}

	@Override
	public Optional<Movie> getMovieByMovieId(String movieId) {
		// TODO Auto-generated method stub
		return movieRepository.getMovieByMovieId(movieId);
	}

	@Override
	public Optional<List<Movie>> getAllMovies() {
		// TODO Auto-generated method stub
		return movieRepository.getAllMovies();
	}

	@Override
	public Optional<List<Movie>> getAllMoviesByGenre(String genre) {
		// TODO Auto-generated method stub
		return movieRepository.getAllMoviesByGenre(genre);
	}

	@Override
	public Optional<List<Movie>> getAllMoviesByName(String movieName) {
		// TODO Auto-generated method stub
		return movieRepository.getAllMoviesByName(movieName);
	}

	@Override
	public String deleteMovieByMovieId(String movieId) throws Exception {
		// TODO Auto-generated method stub
		return movieRepository.deleteMovieByMovieId(movieId);
	}

}
