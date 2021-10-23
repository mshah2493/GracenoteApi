package com.gracenote.api.datafetchers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gracenote.api.model.MovieRecord;
import com.gracenote.api.repository.MovieRecordsRepository;

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
public class MovieRecordsDataFetcher implements DataFetcher<List<MovieRecord>> {

	@Autowired
	private MovieRecordsRepository repository;
	
	@Override
	public List<MovieRecord> get(DataFetchingEnvironment environment) {
		return repository.findAll();
	}
	
	public DataFetcher<List<MovieRecord>> getAllMovieRecords() {
		return dataFetchingEnvironment -> {
			return repository.findAll();
		};
	}
}