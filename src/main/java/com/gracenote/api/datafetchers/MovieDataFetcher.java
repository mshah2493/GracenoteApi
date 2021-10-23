package com.gracenote.api.datafetchers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gracenote.api.model.MovieShow;
import com.gracenote.api.repository.MovieShowsRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author meets
 *
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
public class MovieDataFetcher implements DataFetcher<List<MovieShow>> {

	@Autowired
	private MovieShowsRepository repository;
	
	@Override
	public List<MovieShow> get(DataFetchingEnvironment environment) {
		return repository.findAll();
	}
	
	public DataFetcher<List<MovieShow>> getAllMovieShows() {
		return dataFetchingEnvironment -> {
			return repository.findAll();
		};
	}
	
	public DataFetcher<List<MovieShow>> getAllShows() {
		return dataFetchingEnvironment -> {
			return repository.findAll();
		};
	}
}