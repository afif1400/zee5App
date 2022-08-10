package com.zee.zee5App.repo;

import java.util.List;
import java.util.Optional;

import com.zee.zee5App.dto.Series;
import com.zee.zee5App.exeptions.UnableToGenerateIdException;

public interface SeriesRepository {
	public Series insertSeries(Series series) throws UnableToGenerateIdException;
	
	public Series updateSeries(String seriesId, Series series) throws Exception;
	
	public Optional<Series> getseriesBySeriesId(String seriesId);
	
	public Optional<List<Series>> getAllSeries();
	public Optional<List<Series>> getAllSeriesByGenre(String genre);
	public Optional<List<Series>> getAllSeriesByName(String seriesName);
	
//	public List<Movie> findByOrderByMovieNameDsc();
	
	public String deleteSeriesBySeriesId(String SeriesId) throws Exception;
}
