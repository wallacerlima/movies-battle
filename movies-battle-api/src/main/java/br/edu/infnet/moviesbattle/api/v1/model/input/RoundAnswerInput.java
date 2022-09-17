package br.edu.infnet.moviesbattle.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoundAnswerInput {
	@NotBlank
	private String movieId;
}
