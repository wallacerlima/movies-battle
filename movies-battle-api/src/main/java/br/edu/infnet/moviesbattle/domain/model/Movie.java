package br.edu.infnet.moviesbattle.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Movie {
	
	@EqualsAndHashCode.Include
	@Id
    @GenericGenerator(name = "movieId", strategy = "uuid2")
    @GeneratedValue(generator = "movieId")
	private String id;
	
    @Column(name = "title")
    private String title;
    
    @Column(name = "year")
    private Integer year;
    
    @Column(name = "genre")
    private String genre;

    @Column(name = "imdb_rating")
    private BigDecimal imdbRating;
    
    @Column(name = "imdb_votes")
    private Integer imdbVotes;
    
    public BigDecimal getMoviePoints() {
    	return this.imdbRating.multiply(new BigDecimal(this.imdbVotes));
    }
}
