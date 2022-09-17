package br.edu.infnet.moviesapi.domain.gateway;

import br.edu.infnet.moviesapi.domain.model.Movie;

public interface MovieGatewayService {

	public Movie getMovieBy(String title);
}
