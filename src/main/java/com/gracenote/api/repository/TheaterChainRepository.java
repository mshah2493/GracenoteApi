package com.gracenote.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gracenote.api.model.TheaterChain;

/**
 * @author meets
 *
 */
public interface TheaterChainRepository extends JpaRepository<TheaterChain, Long> { }