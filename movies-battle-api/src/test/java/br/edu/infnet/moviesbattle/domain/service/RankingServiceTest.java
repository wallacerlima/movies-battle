package br.edu.infnet.moviesbattle.domain.service;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import br.edu.infnet.moviesbattle.domain.model.Ranking;
import br.edu.infnet.moviesbattle.domain.repository.RankingRepository;
import br.edu.infnet.moviesbattle.fixtures.builders.match.MatchBuilder;
import br.edu.infnet.moviesbattle.fixtures.builders.player.PlayerBuilder;
import br.edu.infnet.moviesbattle.fixtures.builders.ranking.RankingBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RankingServiceTest {
	
	@Mock
	private RankingRepository rankingRepository;
	
	@InjectMocks
	private RankingService rankingService;
	
	private String playerId = "e15b98b5-a84b-4b95-ab67-2cf1f9086348";
	
	@Test
	public void shouldReturnAGameRanking() {
		rankingService.getGameRanking();
		verify(rankingRepository, times(1)).findAllByOrderByTotalScoreDesc();
	}
	
	@Test
	public void givenAMatch_shouldUpdateRanking() {
		
		var player = PlayerBuilder.aPlayer().build();
		var match = MatchBuilder.aMatch().finished().withPlayer(player).build();
		var ranking = RankingBuilder.aRanking().build();
		
		when(rankingRepository.findByPlayerId(playerId)).thenReturn(Optional.of(ranking));
		
		rankingService.updateRankingPlayerBy(match);
		verify(rankingRepository, times(1)).save(any(Ranking.class));
	}

}
