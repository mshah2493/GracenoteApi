package com.gracenote.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gracenote.api.model.Theater;

/**
 * @author meets
 *
 */
public interface TheaterRepository extends JpaRepository<Theater, Long> { }