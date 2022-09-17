package br.edu.infnet.moviesapi.domain.service;

import br.edu.infnet.moviesapi.domain.model.Movie;
import br.edu.infnet.moviesapi.infrastructure.client.omdb.OmdbClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private OmdbClient omdbClient;

    public Movie getMovieBy(String title) {
        return omdbClient.getMovieBy(title);
    }
}
