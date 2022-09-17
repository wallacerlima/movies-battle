package br.edu.infnet.moviesbattle.infrastructure.client.moviesapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.infnet.moviesbattle.core.properties.MoviesApiProperties;
import br.edu.infnet.moviesbattle.infrastructure.client.moviesapi.response.MovieResponse;

@Component
public class MoviesApiClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MoviesApiProperties moviesApiProperties;
	
	public MovieResponse getMovieBy(String title) {
		
		String urlTemplate = UriComponentsBuilder.fromHttpUrl(moviesApiProperties.getHost() + "/v1/movies")
		        .queryParam("title", title)
		        .build()
		        .toUriString();
		        
		return restTemplate.exchange(
				urlTemplate, 
				HttpMethod.GET,
				null,
				MovieResponse.class
				).getBody();
	}
}
