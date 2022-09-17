package br.edu.infnet.moviesbattle.infrastructure.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.moviesbattle.domain.gateway.MovieGatewayService;
import br.edu.infnet.moviesbattle.domain.repository.MovieRepository;

@Component
public class MovieDatabaseLoader implements ApplicationRunner {

	@Autowired
	private MovieGatewayService movieGatewayService;
	
	@Autowired
	private MovieRepository movieRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		var movies = movieGatewayService.getTop50Movies();
		movieRepository.saveAll(movies);
	}

}
