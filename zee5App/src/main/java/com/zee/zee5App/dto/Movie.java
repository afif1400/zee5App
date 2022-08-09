package com.zee.zee5App.dto;

import com.zee.zee5App.enums.Genre;
import com.zee.zee5App.enums.Languages;
import com.zee.zee5App.exeptions.InvalidIdException;
import com.zee.zee5App.exeptions.InvalidNameException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Movie {
	private String movieId;
	private String actors[];
	private String movieName;
	private String director;
	private String production;
	private String languages[];
	private float movieLength;
	private Genre genre;
	private String trailer1;
//	private byte[] trailer2;

	public Movie() {

	}

	public Movie(String movieId, String[] actors, String movieName, String director, String production,
			String[] languages, float movieLength, Genre genre) throws InvalidIdException, InvalidNameException {
		super();
		setMovieId(movieId);
		this.actors = actors;
		setMovieName(movieName);
		this.director = director;
		this.production = production;
		setLanguages(languages);
		this.movieLength = movieLength;
		this.genre = genre;
	}

	public void setMovieId(String movieId) throws InvalidIdException {
		// movie id should be minimum 5 chars and max 7
		int length = movieId.length();
		if (length >= 5 && length <= 7) {
			this.movieId = movieId;
		} else {
			// raise the exception
			// data is not validated
			throw new InvalidIdException("Invalid movie id");
		}
	}

	public void setActors(String[] actors) {
		this.actors = actors;
	}

	public void setMovieName(String movieName) throws InvalidNameException {
		int length = movieName.length();
		if (length == 0 || length < 1 || movieName == null) {
			// raise the exception
			// data is not validated
			throw new InvalidNameException("Invalid movie name");
		} else {
			this.movieName = movieName;
		}
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public void setLanguages(String[] languages) throws InvalidNameException {
		for(String language: languages) {
			
			try {
				if(Languages.valueOf(language) == null) {
					throw new InvalidNameException("Invalid language name");
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				throw new InvalidNameException("Invalid language name");
			}
		}
		this.languages = languages;
	}

	public void setMovieLength(float movieLength) {
		this.movieLength = movieLength;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	public void setTrailer1(String trailerpath) {
		this.trailer1 = trailerpath;
	}

}
