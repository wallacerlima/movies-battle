package br.edu.infnet.moviesbattle.api.v1.assembler;

import br.edu.infnet.moviesbattle.domain.model.Match;
import org.springframework.stereotype.Component;

import br.edu.infnet.moviesbattle.api.v1.model.MatchModel;
import br.edu.infnet.moviesbattle.api.v1.model.PlayerModel;

@Component
public class MatchModelAssembler {
	
	public MatchModel toModel(Match match) {
		
		PlayerModel playerModel = new PlayerModel();
		playerModel.setName(match.getPlayer().getName());
		playerModel.setUsername(match.getPlayer().getUsername());
		
		MatchModel matchModel = new MatchModel();
		matchModel.setMatchId(match.getId());
		matchModel.setPlayer(playerModel);
		matchModel.setScore(match.getScore());
		matchModel.setStartedAt(match.getStartedAt());
		
		return matchModel;
	}

}
