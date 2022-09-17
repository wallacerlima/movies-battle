package br.edu.infnet.moviesbattle.domain.service;

import java.util.List;

import br.edu.infnet.moviesbattle.domain.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.moviesbattle.domain.model.Match;
import br.edu.infnet.moviesbattle.domain.model.Ranking;

@Service
public class RankingService {
	
	@Autowired
	private RankingRepository rankingRepository;
	
	public List<Ranking> getGameRanking() {
		return rankingRepository.findAllByOrderByTotalScoreDesc();
	}
	
	public void updateRankingPlayerBy(Match match) {
		var ranking = rankingRepository.findByPlayerId(match.getPlayer().getId())
				.orElse(new Ranking(match.getPlayer(), 0L));
		
		ranking.updateRanking(match);
		
		rankingRepository.save(ranking);
	}

}
