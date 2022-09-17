package br.edu.infnet.moviesbattle.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.infnet.moviesbattle.domain.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, String> {

}
