package com.gracenote.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gracenote.api.model.Movie;

/**
 * @author meets
 *
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> { }
