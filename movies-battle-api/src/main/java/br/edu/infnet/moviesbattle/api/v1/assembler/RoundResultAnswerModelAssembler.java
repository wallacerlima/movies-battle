package br.edu.infnet.moviesbattle.api.v1.assembler;

import br.edu.infnet.moviesbattle.domain.model.Round;
import org.springframework.stereotype.Component;

import br.edu.infnet.moviesbattle.api.v1.model.RoundResultAnswerModel;

@Component
public class RoundResultAnswerModelAssembler {
	
	public RoundResultAnswerModel toModel(Round round) {
		
		RoundResultAnswerModel resultRoundModel = new RoundResultAnswerModel();
		resultRoundModel.setRoundId(round.getId());
		resultRoundModel.setResult(round.getResult());
		resultRoundModel.setRemainingAttempts(round.getMatch().getRemainingAttempts());

		return resultRoundModel;
	}

}
