package com.gracenote.api;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieRecordsControllerTestsWithMock {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void shouldReturnDefaultResponseEntiry() throws Exception {
		
		this.mockMvc.perform(get("http://localhost:8080/gracenote/api/movies/allmovies")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("[[\"AMC\",\"AMC Kips Bay 15\",\"The Lion King\",\"7/19/2019\",\"7:25 PM\",\"\"]"
						+ ",[\"AMC\",\"AMC Kips Bay 15\",\"The Lion King\",\"7/20/2019\",\"9:10 PM\",\"\"]"
						+ ",[\"Bowtie\",\"Criterion Cinemas 11\",\"The Lion King\",\"7/19/2019\",\"7:15 PM\",\"\"]"
						+ ",[\"Bowtie\",\"Criterion Cinemas 11\",\"The Lion King\",\"7/20/2019\",\"10:00 PM\",\"\"]"
						+ ",[\"Regal\",\"Regal Crossgates\",\"The Lion King\",\"7/18/2019\",\"9:00 PM\",\"EARLY_SHOWING\"]"
						+ ",[\"Regal\",\"Regal Crossgates\",\"The Lion King\",\"7/19/2019\",\"7:15 PM\",\"\"]"
						+ ",[\"Regal\",\"Regal Crossgates\",\"The Lion King\",\"7/19/2019\",\"9:15 PM\",\"\"]"
						+ ",[\"Regal\",\"Regal Aviation Mall\",\"The Lion King\",\"7/19/2019\",\"6:00 PM\",\"\"]"
						+ ",[\"Regal\",\"Regal Aviation Mall\",\"The Lion King\",\"7/19/2019\",\"9:00 PM\",\"IMAX\"]"
						+ ",[\"AMC\",\"AMC Kips Bay 15\",\"Spiderman: Far From Home\",\"7/19/2019\",\"6:15 PM\",\"\"]"
						+ ",[\"AMC\",\"AMC Kips Bay 15\",\"Spiderman: Far From Home\",\"7/20/2019\",\"8:00 PM\",\"\"]"
						+ ",[\"Bowtie\",\"Criterion Cinemas 11\",\"Spiderman: Far From Home\",\"7/19/2019\",\"7:15 PM\",\"\"]"
						+ ",[\"Bowtie\",\"Criterion Cinemas 11\",\"Spiderman: Far From Home\",\"7/20/2019\",\"9:00 PM\",\"\"]"
						+ ",[\"Regal\",\"Regal Crossgates\",\"Spiderman: Far From Home\",\"7/19/2019\",\"7:05 PM\",\"\"]"
						+ ",[\"Regal\",\"Regal Crossgates\",\"Spiderman: Far From Home\",\"7/19/2019\",\"9:45 PM\",\"IMAX\"]"
						+ ",[\"Regal\",\"Regal Aviation Mall\",\"Spiderman: Far From Home\",\"7/19/2019\",\"6:00 PM\",\"\"]"
						+ ",[\"Regal\",\"Regal Aviation Mall\",\"Spiderman: Far From Home\",\"7/19/2019\",\"9:00 PM\",\"IMAX\"]]")));
	}
}
