package com.gracenote.api.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.gracenote.api.fileio.ExcelFileIO;
import com.gracenote.api.model.Movie;
import com.gracenote.api.model.MovieRecord;
import com.gracenote.api.model.MovieShow;
import com.gracenote.api.model.Theater;
import com.gracenote.api.model.TheaterChain;
import com.gracenote.api.repository.MovieRecordsRepository;
import com.gracenote.api.repository.MovieRepository;
import com.gracenote.api.repository.MovieShowsRepository;
import com.gracenote.api.repository.TheaterChainRepository;
import com.gracenote.api.repository.TheaterRepository;
import com.opencsv.exceptions.CsvException;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author meets
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Service
public class MovieRecordsService {
	@Autowired
	private MovieRecordsRepository repository;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private MovieShowsRepository showsRepository;
	@Autowired
	private TheaterRepository theaterRepository;
	@Autowired
	private TheaterChainRepository chainRepository;
	
	/**
	 * Gets records CSV file using openCSV library and saves them into the database (single table)
	 * @param file
	 */
	public void save(MultipartFile file) {
		try {
			ExcelFileIO fileIO = new ExcelFileIO();
			List<MovieRecord> records = fileIO.getMovieRecords(file.getInputStream());
			repository.saveAll(records);
	    } catch (IOException e) {
	    	throw new RuntimeException("fail to store excel data: " + e.getMessage());
	    }
	}
	
	/**
	 * Gets records CSV file using openCSV library and saves them into the relational tables
	 * @param file
	 * @throws CsvException
	 */
	@SuppressWarnings("unchecked")
	public void saveMovies(MultipartFile file) throws CsvException {
		try {
			ExcelFileIO fileIO = new ExcelFileIO();
			List<Object> records = fileIO.parse(file.getInputStream());
			
		    for(int i = 0; i < records.size(); i++){
		    	Object item = records.get(i);
		    	
		    	if(item instanceof List<?>) {
		    		Object var = ((List<?>) item).get(0);
		    		if (var instanceof TheaterChain) {
		    			List<TheaterChain> theaterChains = (List<TheaterChain>) (Object) item;
		    			chainRepository.saveAll(theaterChains);
		    		} else if (var instanceof Theater) {
						List<Theater> theaters = (List<Theater>) (Object) item;
		    			theaterRepository.saveAll(theaters);
		    		} else if (var instanceof Movie) {
		    			List<Movie> movies = (List<Movie>) (Object) item;
		    			movieRepository.saveAll(movies);
		    		} else if (var instanceof MovieShow) {
		    			List<MovieShow> shows = (List<MovieShow>) (Object) item;
		    			showsRepository.saveAll(shows);
		    		}
		    	}
		    }
	    } catch (IOException e) {
	    	throw new RuntimeException("fail to store excel data: " + e.getMessage());
	    }
	}

	/**
	 * Get movie records from the movie_records table (single table)
	 * @return
	 */
	@RequestMapping("getmovierecords.do")
	public List<Object> getAllRecords() {
		return repository.getMovieRecords();
	}
	
	/**
	 * Get movie records from all the tables on matching movie ids, theater ids and chain ids.
	 * @return
	 */
	@RequestMapping("getMovieRecords.do")
	public List<Object> getAllRecordss() {
		return showsRepository.getMovieRecords();
	}
}
