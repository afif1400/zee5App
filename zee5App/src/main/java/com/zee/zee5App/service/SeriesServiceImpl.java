package com.zee.zee5App.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5App.dto.Series;
import com.zee.zee5App.exeptions.UnableToGenerateIdException;
import com.zee.zee5App.repo.MovieRepository;
import com.zee.zee5App.repo.MovieRepositoryImpl;
import com.zee.zee5App.repo.SeriesRepository;
import com.zee.zee5App.repo.SeriesRepositoryImpl;

public class SeriesServiceImpl implements SeriesService {

	private SeriesRepository seriesRepository = SeriesRepositoryImpl.getInstance();

	private static SeriesService seriesService;

	public static SeriesService getInstance() {
		if (seriesService == null) {
			seriesService = new SeriesServiceImpl();
		}

		return seriesService;
	}
	@Override
	public Series insertSeries(Series series) throws UnableToGenerateIdException {
		// TODO Auto-generated method stub
		return seriesRepository.insertSeries(series);
	}

	@Override
	public Series updateSeries(String seriesId, Series series) throws Exception {
		// TODO Auto-generated method stub
		return seriesRepository.updateSeries(seriesId, series);
	}

	@Override
	public Optional<Series> getseriesBySeriesId(String seriesId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<List<Series>> getAllSeries() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<List<Series>> getAllSeriesByGenre(String genre) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<List<Series>> getAllSeriesByName(String seriesName) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public String deleteSeriesBySeriesId(String SeriesId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
