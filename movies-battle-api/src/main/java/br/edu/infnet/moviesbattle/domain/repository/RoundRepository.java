package br.edu.infnet.moviesbattle.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.moviesbattle.domain.model.Round;

@Repository
public interface RoundRepository extends JpaRepository<Round, String>{
	
	public Optional<Round> findByAnsweredFalseAndMatchId(String matchId);
	
	public Optional<Round> findByMatchIdAndFirstMovieIdAndSecondMovieId(String matchId, String firstMovieId, String secondMovieId);
	
	public List<Round> findAllByMatchId(String matchId);

}
