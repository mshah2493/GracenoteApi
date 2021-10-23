package com.gracenote.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gracenote.api.message.ResponseMessage;
import com.gracenote.api.service.GraphQLService;
import com.gracenote.api.service.MovieRecordsService;

import graphql.ExecutionResult;

/**
 * @author meets
 *
 */
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("gracenote/api/movies")
public class MovieController {
	@Autowired
	private MovieRecordsService service;
	
	@Autowired
	private GraphQLService glService;
	
	/*
	 * Get all movie shows (multiple relational tables) with a query injected manually to the method in MovieShowsRepository 
	 */
	@GetMapping("/allmovies")
	public ResponseEntity<List<Object>> getRecordss()
	{
		try {
			List<Object> records = service.getAllRecordss();

			if (records.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(records, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * Get all movies from relational tables with graphql
	 */
	@GetMapping("/movies")
	public ResponseEntity<Object> getAllRecords(@RequestBody String query)
	{
		ExecutionResult result = glService.getGraph().execute(query);
		
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}
	
	/*	 * Upload data from csv file to multiple relational tables
	 */
	@PostMapping("/upload-movies")
	public ResponseEntity<ResponseMessage> save(@RequestParam("file") MultipartFile file) {
		String message = "";
	    
		try {
			service.saveMovies(file);
			message += "Uploaded the file successfully: " + file.getOriginalFilename();
		      
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	      } catch (Exception e) {
	    	  message += "Could not upload the file: " + file.getOriginalFilename() + "!";
	    	  message += "\n";
	    	  message += "ERROR: " + e.getMessage();

	    	  return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	      }
	}
}
