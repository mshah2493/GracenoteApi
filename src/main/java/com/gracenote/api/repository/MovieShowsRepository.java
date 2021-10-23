package com.gracenote.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gracenote.api.model.MovieShow;

/**
 * @author meets
 *
 */
public interface MovieShowsRepository extends JpaRepository<MovieShow, Long> { 
	final String query = "select tc.chain_name, t.theater_name, m.movie_title, ms.date, ms.showtime, ms.attributes from movie_shows as ms "
			+ "	inner join theater t on ms.theater_id = t.theater_id "
			+ "	inner join movies m on ms.movie_id = m.movie_id "
			+ "	inner join theater_chain tc on tc.chain_id = t.chain_id";
	
	/**
	 * Gets records based on a query provided
	 * @return List<Object>
	 */
	@Query(value = query, nativeQuery = true)
    List<Object> getMovieRecords();
}