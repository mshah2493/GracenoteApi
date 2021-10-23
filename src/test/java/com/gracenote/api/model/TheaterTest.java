package com.gracenote.api.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TheaterTest {

	private Theater theater;
	@BeforeEach
	void setUp() throws Exception {
		theater = new Theater(1, "Regal Crossgates", new TheaterChain());
	}

	@AfterEach
	void tearDown() throws Exception {
		theater = null;
	}

	@Test
	void testSetTheater_id() {
		theater.setTheater_id(2);
		assertThat(2).isEqualTo(theater.getTheater_id());
	}

	@Test
	void testSetTheater_name() {
		theater.setTheater_name("AMC");
		assertThat("AMC").isEqualTo(theater.getTheater_name());
	}

	@Test
	void testGetTheater_id() {
		assertThat(1).isEqualTo(theater.getTheater_id());
	}

	@Test
	void testGetTheater_name() {
		assertThat("Regal Crossgates").isEqualTo(theater.getTheater_name());
	}
}
