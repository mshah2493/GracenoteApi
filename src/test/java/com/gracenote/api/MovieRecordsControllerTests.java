package com.gracenote.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.gracenote.api.controller.MovieRecordsController;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MovieRecordsControllerTests {
	@Autowired
	private MovieRecordsController controller;
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void getRecordsShouldReturnResponseEntiryWithMovieRecordList() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/gracenote/api/movies/allmovies", String.class))
				.contains(
						"[[\"AMC\",\"AMC Kips Bay 15\",\"The Lion King\",\"7/19/2019\",\"7:25 PM\",\"\"],"
						+ "[\"AMC\",\"AMC Kips Bay 15\",\"The Lion King\",\"7/20/2019\",\"9:10 PM\",\"\"],"
						+ "[\"Bowtie\",\"Criterion Cinemas 11\",\"The Lion King\",\"7/19/2019\",\"7:15 PM\",\"\"],"
						+ "[\"Bowtie\",\"Criterion Cinemas 11\",\"The Lion King\",\"7/20/2019\",\"10:00 PM\",\"\"],"
						+ "[\"Regal\",\"Regal Crossgates\",\"The Lion King\",\"7/18/2019\",\"9:00 PM\",\"EARLY_SHOWING\"],"
						+ "[\"Regal\",\"Regal Crossgates\",\"The Lion King\",\"7/19/2019\",\"7:15 PM\",\"\"],"
						+ "[\"Regal\",\"Regal Crossgates\",\"The Lion King\",\"7/19/2019\",\"9:15 PM\",\"\"],"
						+ "[\"Regal\",\"Regal Aviation Mall\",\"The Lion King\",\"7/19/2019\",\"6:00 PM\",\"\"],"
						+ "[\"Regal\",\"Regal Aviation Mall\",\"The Lion King\",\"7/19/2019\",\"9:00 PM\",\"IMAX\"],"
						+ "[\"AMC\",\"AMC Kips Bay 15\",\"Spiderman: Far From Home\",\"7/19/2019\",\"6:15 PM\",\"\"],"
						+ "[\"AMC\",\"AMC Kips Bay 15\",\"Spiderman: Far From Home\",\"7/20/2019\",\"8:00 PM\",\"\"],"
						+ "[\"Bowtie\",\"Criterion Cinemas 11\",\"Spiderman: Far From Home\",\"7/19/2019\",\"7:15 PM\",\"\"],"
						+ "[\"Bowtie\",\"Criterion Cinemas 11\",\"Spiderman: Far From Home\",\"7/20/2019\",\"9:00 PM\",\"\"],"
						+ "[\"Regal\",\"Regal Crossgates\",\"Spiderman: Far From Home\",\"7/19/2019\",\"7:05 PM\",\"\"],"
						+ "[\"Regal\",\"Regal Crossgates\",\"Spiderman: Far From Home\",\"7/19/2019\",\"9:45 PM\",\"IMAX\"],"
						+ "[\"Regal\",\"Regal Aviation Mall\",\"Spiderman: Far From Home\",\"7/19/2019\",\"6:00 PM\",\"\"],"
						+ "[\"Regal\",\"Regal Aviation Mall\",\"Spiderman: Far From Home\",\"7/19/2019\",\"9:00 PM\",\"IMAX\"]]");
	}
}
