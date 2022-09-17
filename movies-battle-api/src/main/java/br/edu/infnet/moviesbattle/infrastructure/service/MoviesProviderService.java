package br.edu.infnet.moviesbattle.infrastructure.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import br.edu.infnet.moviesbattle.domain.gateway.MovieGatewayService;
import br.edu.infnet.moviesbattle.domain.model.Movie;
import br.edu.infnet.moviesbattle.infrastructure.client.moviesapi.MoviesApiClient;
import br.edu.infnet.moviesbattle.infrastructure.client.moviesapi.response.MovieResponse;

@Component
public class MoviesProviderService implements MovieGatewayService {

	@Autowired
	private MoviesApiClient moviesApiClient;

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Override
	public List<Movie> getTop50Movies() {
		List<String> moviesTitles = getTop50MovieTitlesFromFile();
		
		var imdbMovies = moviesTitles
				.stream()
				.map(title -> moviesApiClient.getMovieBy(title))
        .collect(Collectors.toList());

		
		return toCollectionDomain(imdbMovies);
	}
	
	private List<String> getTop50MovieTitlesFromFile() {
		
		var fileResource = resourceLoader.getResource("classpath:movies/top50.txt");
		try {
			return IOUtils.readLines(fileResource.getInputStream(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException("An error ocorred when trying to get top50 movies titles");
		}
	}
	
	public Movie toDomainObject(MovieResponse omdbMovie) {
		Movie movie = new Movie();
		movie.setTitle(omdbMovie.getTitle());
		movie.setYear(omdbMovie.getYear());
		movie.setGenre(omdbMovie.getGenre());
		movie.setImdbRating(new BigDecimal(omdbMovie.getImdbRating()));
		movie.setImdbVotes(Integer.valueOf(omdbMovie.getImdbVotes().replaceAll(",", "")));
		
		return movie;
	}
	
	private List<Movie> toCollectionDomain(List<MovieResponse> omdbMovies) {
		return omdbMovies.stream()
				.map(m -> toDomainObject(m))
				.collect(Collectors.toList());
	}

}
