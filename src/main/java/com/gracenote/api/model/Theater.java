package com.gracenote.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name="theater")
public class Theater {
	@Id
	private long theater_id;
	
	@Column(length=32)
	@NonNull private String theater_name;
	
	@ManyToOne
    @JoinColumn(name = "chain_id")
	TheaterChain theaterChain;
}