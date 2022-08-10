package com.zee.zee5App.dto;

import java.time.LocalDate;

import com.zee.zee5App.enums.Languages;
import com.zee.zee5App.exeptions.InvalidNameException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Series {
	private String seriesId;
	private String seriesName;
	private String genre;
	private String director;
	private String production;
	private int noOfSeasons;
//	private int[] noOfEpisodes;
	private LocalDate releaseDate;
	private String[] languages;
	
	
	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}
	public void setSeriesName(String seriesName) throws InvalidNameException {
		int length = seriesName.length();
		if (length == 0 || length < 1 || seriesName == null) {
			// raise the exception
			// data is not validated
			throw new InvalidNameException("Invalid movie name");
		} else {
			this.seriesName = seriesName;
		}
		
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public void setProduction(String production) {
		this.production = production;
	}
	public void setNoOfSeasons(int noOfSeasons) {
		this.noOfSeasons = noOfSeasons;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
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
	
	
}
