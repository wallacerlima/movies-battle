package br.edu.infnet.moviesbattle.api.v1.model;

import br.edu.infnet.moviesbattle.domain.model.RoundResult;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoundResultAnswerModel {
    private RoundResult result;
    private String roundId;
    private Integer remainingAttempts;
}
