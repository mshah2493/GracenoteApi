package com.gracenote.api.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovieTest {
	
	private Movie movie;
	
	@BeforeEach
	void setUp() throws Exception {
		movie = new Movie(1, "No Time To Die");
	}

	@AfterEach
	void tearDown() throws Exception {
		movie = null;
	}

	@Test
	void testSetMovie_id() {
		movie.setMovie_id(2);
		assertThat(2).isEqualTo(movie.getMovie_id());
	}

	@Test
	void testSetMovie_title() {
		movie.setMovie_title("Free Guy");
		assertThat("Free Guy").isEqualTo(movie.getMovie_title());
	}

	@Test
	void testGetMovie_id() {
		assertThat(1).isEqualTo(movie.getMovie_id());
	}

	@Test
	void testGetMovie_title() {
		assertThat("No Time To Die").isEqualTo(movie.getMovie_title());
	}
}
