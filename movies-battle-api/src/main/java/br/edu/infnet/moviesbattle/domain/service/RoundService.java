package br.edu.infnet.moviesbattle.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.edu.infnet.moviesbattle.domain.repository.MatchRepository;
import br.edu.infnet.moviesbattle.domain.repository.MovieRepository;
import br.edu.infnet.moviesbattle.domain.repository.PlayerRepository;
import br.edu.infnet.moviesbattle.domain.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.moviesbattle.domain.model.Match;
import br.edu.infnet.moviesbattle.domain.model.Movie;
import br.edu.infnet.moviesbattle.domain.model.Player;
import br.edu.infnet.moviesbattle.domain.model.Round;

@Service
public class RoundService {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private MatchRepository matchRepository;
	
	@Autowired
	private RoundRepository roundRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	public Round getNextRound(String playerId) {
		
		var player = playerRepository.findById(playerId)
				.orElseThrow(() -> new RuntimeException("Player not found!"));
		
		var match = matchRepository.findByPlayerIdAndFinishedAtIsNull(playerId)
				.orElseThrow(() -> new RuntimeException("Match not found!"));
		
		var roundNotAswered = roundRepository.findByAnsweredFalseAndMatchId(match.getId());
		
		if(roundNotAswered.isPresent())
			return roundNotAswered.get();
		
		return createNewRound(player, match);
	}
	
	public Round saveRoundAnswer(String movieId, String playerId) {
		
		var match = matchRepository.findByPlayerIdAndFinishedAtIsNull(playerId)
				.orElseThrow(() -> new RuntimeException("Match not found!"));
		
		var round = roundRepository.findByAnsweredFalseAndMatchId(match.getId()).get();
		
		if(validateRoundAnswer(round, movieId)) {
			round.finishRound(true);
		} else {
			round.finishRound(false);
		}
		
		return roundRepository.save(round);
	}
	
	private Round createNewRound(Player player, Match match) {
		
		var moviePair = createNewMoviePair();
		
		while(!isValidMoviePair(moviePair, match))
			moviePair = createNewMoviePair();
		
		Round round = new Round();
		round.setFirstMovie(moviePair.get(0));
		round.setSecondMovie(moviePair.get(1));
		round.setMatch(match);
		
		return roundRepository.save(round);
	}
	
	private List<Movie> createNewMoviePair() {
		List<Movie> moviePair = new ArrayList<>();
		
		moviePair.add(getRandomMovie());
		moviePair.add(getRandomMovie());
		
		return moviePair;
	}
	
	private Movie getRandomMovie() {
		var movies = movieRepository.findAll();
		return movies.get(new Random().nextInt(movies.size()));
	}
	
	private Boolean isValidMoviePair(List<Movie> moviePair, Match match) {
		
		var firstMovie = moviePair.get(0);
		var secondMovie = moviePair.get(1);
		
		
		if (firstMovie.getId().equals(secondMovie.getId())) {
			return false;
		}
			
        var existPairAB = 
        		roundRepository.findByMatchIdAndFirstMovieIdAndSecondMovieId(match.getId(), firstMovie.getId(), secondMovie.getId());
        var existPairBA = 
        		roundRepository.findByMatchIdAndFirstMovieIdAndSecondMovieId(match.getId(), secondMovie.getId(), firstMovie.getId());

        if (existPairAB.isPresent() || existPairBA.isPresent()) {
        	return false;
        }

        return true;
	}
	
	private Boolean validateRoundAnswer(Round round, String movieId) {
		if((round.getFirstMovie().getMoviePoints().compareTo(round.getSecondMovie().getMoviePoints()) == 1) 
				&& round.getFirstMovie().getId().equals(movieId)) {
			return true;
		}
		
		if((round.getSecondMovie().getMoviePoints().compareTo(round.getFirstMovie().getMoviePoints()) == 1) 
				&& round.getSecondMovie().getId().equals(movieId)) {
			return true;
		}
		
		return false;
	}
}
