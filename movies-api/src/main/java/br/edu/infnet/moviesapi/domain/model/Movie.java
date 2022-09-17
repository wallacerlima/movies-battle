package br.edu.infnet.moviesapi.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {

	@JsonProperty("Title")
    private String title;
	
	@JsonProperty("Year")
    private Integer year;
	
	@JsonProperty("Genre")
    private String genre;
	
    private String imdbRating;
    
    private String imdbVotes;
}
