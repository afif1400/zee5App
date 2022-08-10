package com.zee.zee5App;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.zee.zee5App.dto.Movie;
import com.zee.zee5App.dto.Series;
import com.zee.zee5App.dto.User;
import com.zee.zee5App.enums.Genre;
import com.zee.zee5App.exeptions.UnableToGenerateIdException;
import com.zee.zee5App.service.MovieService;
import com.zee.zee5App.service.MovieServiceImpl;
import com.zee.zee5App.service.SeriesService;
import com.zee.zee5App.service.SeriesServiceImpl;
import com.zee.zee5App.service.UserService;
import com.zee.zee5App.service.UserServiceImpl;

public class Main {

	public static void main(String[] args) {
		UserService userService = UserServiceImpl.getInstance();
		MovieService movieService = MovieServiceImpl.getInstance();
		SeriesService seriesService = SeriesServiceImpl.getInstance();

		User newUser = new User("afif", "ahmed", "afif@gmail.com");
		newUser.setUserId(String.valueOf("123"));
		newUser.setDob(LocalDate.now());
		newUser.setDoj(LocalDate.now());
		newUser.setActive(false);
//		userService.insertUser(newUser);

		String[] actors = { "prabhas", "rana" };
//		String[] languages = { "HINDI", "ENGLISH" };
//		Movie movie = new Movie();
		
//	try {
//			movie.setMovieId("12345");
//			movie.setActors(actors);
//			movie.setDirector("SSR");
//			movie.setMovieName("bahubali");
//			movie.setLanguages(languages);
//			movie.setMovieLength(10);
//			movie.setGenre(Genre.ACTION);
//			movie.setProduction("dharma");
//			
//			Movie insertedMovie = movieService.insertMovie(movie);
//			System.out.println(insertedMovie);
//			
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		Optional<Movie> movie = movieService.getMovieByMovieId("12345");
//		System.out.println(movie.get());
//		Movie movie = new Movie();
//		movie.setTrailer1("C:\\Users\\mohammed.afif\\Downloads\\Baby Driver Opening Scene (2017) _ Movieclips Coming Soon.mp4");
//		try {
//			movieService.insertMovie(movie);
//		} catch (FileNotFoundException | UnableToGenerateIdException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String[] languages = { "HINDI", "ENGLISH" };
		Series series = new Series();
		
	try {
//			series.setMovieId("12345");
			series.setSeriesName("bahubali");
			series.setDirector("SSR");
			series.setNoOfSeasons(12);
			series.setLanguages(languages);
			series.setReleaseDate(LocalDate.now());
			series.setGenre(Genre.ACTION.toString());
			series.setProduction("dharma");
			
			Series insertedMovie = seriesService.insertSeries(series);
			System.out.println(insertedMovie);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
