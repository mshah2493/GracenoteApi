package com.gracenote.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gracenote.api.model.MovieRecord;

/**
 * @author meets
 *
 */
public interface MovieRecordsRepository extends JpaRepository<MovieRecord, Long> { 
	/**
	 * Gets records based on a query provided
	 * @return List<Object>
	 */
	@Query(value = "select chain_name,  theater_name, movie_title, date, showtime, attributes from movie_records", nativeQuery = true)
    List<Object> getMovieRecords();
}
