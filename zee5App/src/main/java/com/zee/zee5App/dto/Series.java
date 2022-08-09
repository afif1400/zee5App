package com.zee.zee5App.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Series {
	 private String SeriesId;
	    private String SeriesName;
	    private String Genre;
	    private int noOfSeasons;
	    private int[] noOfEpisodes;
	    private LocalDate releaseDate;
	    private String[] languages;
}
