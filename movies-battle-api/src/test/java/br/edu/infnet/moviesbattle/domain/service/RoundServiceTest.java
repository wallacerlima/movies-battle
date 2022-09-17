package br.edu.infnet.moviesbattle.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import br.edu.infnet.moviesbattle.domain.model.Round;
import br.edu.infnet.moviesbattle.domain.repository.MatchRepository;
import br.edu.infnet.moviesbattle.domain.repository.PlayerRepository;
import br.edu.infnet.moviesbattle.domain.repository.RoundRepository;
import br.edu.infnet.moviesbattle.fixtures.builders.match.MatchBuilder;
import br.edu.infnet.moviesbattle.fixtures.builders.player.PlayerBuilder;
import br.edu.infnet.moviesbattle.fixtures.builders.round.RoundBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoundServiceTest {

	@Mock
	private MatchRepository matchRepository;

	@Mock
	private PlayerRepository playerRepository;
	
	@Mock
	private RoundRepository roundRepository;
	
	@InjectMocks
	private RoundService roundService;
	
	private String playerId = "e15b98b5-a84b-4b95-ab67-2cf1f9086348";
	
	@Test
	public void givenARoundNotAnsweredShouldNotReturnANextRound() {
		var player = PlayerBuilder.aPlayer().build();
		var matchNotFinished = MatchBuilder.aMatch().build();
		var roundNotAnswered = RoundBuilder.aRound().notAnswered().build();
		
		when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));
		when(matchRepository.findByPlayerIdAndFinishedAtIsNull(playerId)).thenReturn(Optional.of(matchNotFinished));
		when(roundRepository.findByAnsweredFalseAndMatchId(matchNotFinished.getId())).thenReturn(Optional.of(roundNotAnswered));

		roundService.getNextRound(playerId);

		verify(roundRepository, times(0)).save(any(Round.class));
	}
	
}
