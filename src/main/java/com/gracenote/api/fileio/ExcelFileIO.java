package com.gracenote.api.fileio;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.gracenote.api.model.Movie;
import com.gracenote.api.model.MovieRecord;
import com.gracenote.api.model.MovieShow;
import com.gracenote.api.model.Theater;
import com.gracenote.api.model.TheaterChain;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

/**
 * @author meets
 *
 */
public class ExcelFileIO {
	/**
	 * Reads CSV file content and gives list of movie records
	 * @param is
	 * @return List<MovieRecord>
	 * @throws IOException
	 */
	public List<MovieRecord> getMovieRecords(InputStream is) throws IOException {
		Reader reader = new InputStreamReader(is);
		List<MovieRecord> movieRecords = new CsvToBeanBuilder<MovieRecord>(reader)
				.withType(MovieRecord.class)
				.build()
				.parse();

		return movieRecords;
	}
	
	/**
	 * Reads CSV file content and gives list of objects (Movie, MovieShow, Theater, TheaterChain)
	 * @param is
	 * @return List<Object>
	 * @throws IOException
	 * @throws CsvException
	 */
	public List<Object> parse(InputStream is) throws IOException, CsvException {
		Reader reader = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(reader);
		List<String> headers = new ArrayList<>();
		List<Object> list = new ArrayList<>();
		String [] row;
		
		if ((row = csvReader.readNext()) != null) {
			for (String colName : row) {
				headers.add(colName);
			}
		}
		
		List<TheaterChain> chainList = new ArrayList<>();
		List<Theater> theaterList = new ArrayList<>();
		List<Movie> movieList = new ArrayList<>();
		List<MovieShow> showList = new ArrayList<>();
		
		while ((row = csvReader.readNext()) != null) {
			
			Movie movie = new Movie();
			MovieShow show = new MovieShow();
			Theater theater = new Theater();
			TheaterChain chain = new TheaterChain();
			
			for (int colIndex = 0; colIndex < row.length; ++colIndex) {
				
				String value = row[colIndex];
				
				switch (headers.get(colIndex)) {
					case "chain_id":
						chain.setChain_id(Long.parseLong(value));
			            break;
	
					case "chain_name":
						chain.setChain_name(value);
			            break;
	
					case "theater_id":
						theater.setTheater_id(Long.parseLong(value));
			            break;
	
					case "theater_name":
						theater.setTheater_name(value);
			            break;
			            
					case "movie_id":
						movie.setMovie_id(Long.parseLong(value));
			            break;
			            
					case "movie_title":
						movie.setMovie_title(value);
			            break;
			            
					case "date":
						show.setDate(value);
			            break;
			            
					case "showtime":
						show.setShowtime(value);
			            break;
			            
					case "attributes":
						show.setAttributes(value);
			            break;
	
					default:
						break;
				}
			}
			
			theater.setTheaterChain(chain);
			show.setTheater(theater);
			show.setMovie(movie);
			
			chainList.add(chain);
			theaterList.add(theater);
			movieList.add(movie);
			showList.add(show);
		}
		
		list.add(chainList);
		list.add(theaterList);
		list.add(movieList);
		list.add(showList);
		
		reader.close();
		csvReader.close();
		
		return list;
	}
}
