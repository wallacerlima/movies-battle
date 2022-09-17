package br.edu.infnet.moviesbattle.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.edu.infnet.moviesbattle.domain.model.Match;
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
public class MatchServiceTest {

	@Mock
	private MatchRepository matchRepository;

	@Mock
	private PlayerRepository playerRepository;
	
	@Mock
	private RoundRepository roundRepository;
	
	@Mock
	private RankingService rankingService;

	@InjectMocks
	private MatchService matchService;

	private String playerId = "e15b98b5-a84b-4b95-ab67-2cf1f9086348";

	@Test
	public void givenAnPlayerIdShouldStartsNewMatch_whenPlayeDontHaveAStartedMatch() {
		var player = PlayerBuilder.aPlayer().build();

		when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));
		when(matchRepository.findByPlayerIdAndFinishedAtIsNull(playerId)).thenReturn(Optional.empty());

		matchService.startWith(playerId);

		verify(matchRepository, times(1)).save(any(Match.class));
	}

	@Test
	public void givenAnPlayerIdShouldThrowAnException_whenPlayeHaveAStartedMatch() {
		var player = PlayerBuilder.aPlayer().build();
		var matchStarted = MatchBuilder.aMatch().withPlayer(player).build();

		when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));
		when(matchRepository.findByPlayerIdAndFinishedAtIsNull(playerId)).thenReturn(Optional.of(matchStarted));

		assertThrows(RuntimeException.class, () -> {
			matchService.startWith(playerId);
		});
	}
	
	@Test
	public void givenAnPlayerIdShouldThrowAnException_whenPlayerNotFound() {
		when(playerRepository.findById(playerId)).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, () -> {
			matchService.startWith(playerId);
		});
	}
	
	@Test
	public void givenAnPlayerIdShouldFinishAMatch_whenPlayerHaveAStartedMatch() {
		var player = PlayerBuilder.aPlayer().build();
		var match = MatchBuilder.aMatch().build();
		
		List<Round> rounds = new ArrayList<>();
		rounds.add(RoundBuilder.aRound().answeredCorrect().withMatch(match).build());

		when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));
		when(matchRepository.findByPlayerIdAndFinishedAtIsNull(playerId)).thenReturn(Optional.of(match));
		when(roundRepository.findAllByMatchId(match.getId())).thenReturn(rounds);

		matchService.finishWith(playerId);

		verify(rankingService, times(1)).updateRankingPlayerBy(any(Match.class));
		verify(matchRepository, times(1)).save(any(Match.class));
	}
	
	@Test
	public void givenAnPlayerIdShouldThrowAnException_whenPlayerDontHaveAStartedMatch() {
		var player = PlayerBuilder.aPlayer().build();
		
		when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));
		when(matchRepository.findByPlayerIdAndFinishedAtIsNull(playerId)).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, () -> {
			matchService.finishWith(playerId);
		});
	}

}
