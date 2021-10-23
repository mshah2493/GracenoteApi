package com.gracenote.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * @author meets
 *
 */

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="movie_records")
public class MovieRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@CsvBindByName(column = "chain_id")
	private long chain_id;
	
	@CsvBindByName(column = "chain_name")
	@Column(length=32)
	@NonNull
	private String chain_name;
	
	@CsvBindByName(column = "theater_id")
	private long theater_id;
	
	@CsvBindByName(column = "theater_name")
	@Column(length=32)
	@NonNull
	private String theater_name;
	
	@CsvBindByName(column = "movie_id")
	private long movie_id;
	
	@CsvBindByName(column = "movie_title")
	@Column(length=32)
	@NonNull
	private String movie_title;
	
	@CsvBindByName(column = "date")
	@NonNull
	private String date;
	
	@CsvBindByName(column = "showtime")
	@NonNull
	private String showtime;
	
	@CsvBindByName(column = "attributes")
	@Column(length=64)
	private String attributes;
}
