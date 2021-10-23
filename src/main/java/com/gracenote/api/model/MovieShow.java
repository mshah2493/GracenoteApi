package com.gracenote.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name="movie_shows")
public class MovieShow {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long show_id;
	
	@NonNull private String date;
	@NonNull private String Showtime;
	
	@Column(length=64)
	private String attributes;
	
	@ManyToOne
    @JoinColumn(name = "movie_id")
	Movie movie;
	
	@ManyToOne
    @JoinColumn(name = "theater_id")
	Theater theater;
}