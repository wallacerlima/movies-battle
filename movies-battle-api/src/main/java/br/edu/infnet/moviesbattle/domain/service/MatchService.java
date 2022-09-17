package br.edu.infnet.moviesbattle.domain.service;

import java.util.List;
import java.util.Optional;

import br.edu.infnet.moviesbattle.domain.repository.MatchRepository;
import br.edu.infnet.moviesbattle.domain.repository.PlayerRepository;
import br.edu.infnet.moviesbattle.domain.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.moviesbattle.domain.model.Match;
import br.edu.infnet.moviesbattle.domain.model.Player;
import br.edu.infnet.moviesbattle.domain.model.Round;

@Service
public class MatchService {

	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private MatchRepository matchRepository;
	
	@Autowired
	private RoundRepository roundRepository;
	
	@Autowired 
	private RankingService rankingService;
	
	public Match startWith(String playerId) {
		var player = findPlayer(playerId);
		
		if(checkIfHaveAMatchInProgress(playerId).isPresent()) {
			throw new RuntimeException("The user already has a match in progress.");
		}
		
		return createANewMatch(player);
	}
	
	public Match finishWith(String playerId) {
		var player = findPlayer(playerId);
		var match = checkIfHaveAMatchInProgress(player.getId());
		
		if(!match.isPresent()) {
			throw new RuntimeException("The player does not have a match in progress to end.");
		}
		
		return finishMatch(match.get());
	}
	
	private Match createANewMatch(Player player) {
		var match = new Match();
		match.addPlayerToMatch(player);
		
		return matchRepository.save(match);
	}
	
	private Match finishMatch(Match match) {
		var rounds = getAllRoundsByMatch(match.getId());
		match.mustEndAMatch(rounds);
		
		rankingService.updateRankingPlayerBy(match);
		
		return matchRepository.save(match);
	}
	
	private List<Round> getAllRoundsByMatch(String matchId) {
		return roundRepository.findAllByMatchId(matchId);
	}
	
	private Optional<Match> checkIfHaveAMatchInProgress(String playerId) {
		return matchRepository.findByPlayerIdAndFinishedAtIsNull(playerId);
	}
	
	private Player findPlayer(String playerId) {
		return playerRepository.findById(playerId)
				.orElseThrow(() -> new RuntimeException("Player not found!"));
	}

}
