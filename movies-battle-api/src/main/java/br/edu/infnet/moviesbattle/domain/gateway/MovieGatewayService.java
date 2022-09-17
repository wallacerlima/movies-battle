package br.edu.infnet.moviesbattle.domain.gateway;

import java.util.List;

import br.edu.infnet.moviesbattle.domain.model.Movie;

public interface MovieGatewayService {

	public List<Movie> getTop50Movies();
}
