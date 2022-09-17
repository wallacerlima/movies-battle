package br.edu.infnet.moviesapi.infrastructure.client.omdb;

import br.edu.infnet.moviesapi.core.properties.OmdbProperties;
import br.edu.infnet.moviesapi.domain.gateway.MovieGatewayService;
import br.edu.infnet.moviesapi.domain.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class OmdbClient implements MovieGatewayService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private OmdbProperties omdbProperties;
	
	public Movie getMovieBy(String title) {
		
		String urlTemplate = UriComponentsBuilder.fromHttpUrl(omdbProperties.getHost())
		        .queryParam("apikey", omdbProperties.getKey())
		        .queryParam("t", title)
		        .build()
		        .toUriString();
		        
		return restTemplate.exchange(
				urlTemplate, 
				HttpMethod.GET,
				null,
				Movie.class
				).getBody();
	}
}
