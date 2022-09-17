package br.edu.infnet.moviesbattle.fixtures.builders.movie;

import java.math.BigDecimal;

import br.edu.infnet.moviesbattle.domain.model.Movie;

public class MovieBuilder {
	
	private Movie movie;
	
	private MovieBuilder(){}
	
	public static MovieBuilder aMovie() {
		MovieBuilder builder = new MovieBuilder();
		builder.movie = new Movie();
		return builder;
	}
	
	public MovieBuilder One(){
		movie.setId("e15b98b5-a84b-4b95-ab67-2cf1f9086248");
		movie.setTitle("Title 1");
		movie.setYear(2021);
		movie.setGenre("Drama");
		movie.setImdbRating(new BigDecimal("8.9"));
		movie.setImdbVotes(15);
		return this;
	}
	
	public MovieBuilder Two(){
		movie.setId("e15b98b5-a84b-4b95-ab67-2cf1f9086247");
		movie.setTitle("Title 1");
		movie.setYear(2021);
		movie.setGenre("Drama");
		movie.setImdbRating(new BigDecimal("9.9"));
		movie.setImdbVotes(5);
		return this;
	}
	
	public Movie build(){
		return movie;
	}
}