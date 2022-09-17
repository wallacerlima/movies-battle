package br.edu.infnet.moviesapi.api.v1.controller;

import br.edu.infnet.moviesapi.domain.model.Movie;
import br.edu.infnet.moviesapi.domain.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public Movie getMovieBy(@RequestParam String title) {
        return movieService.getMovieBy(title);
    }
}
