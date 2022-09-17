package br.edu.infnet.moviesbattle.api.v1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoundModel {
	private String roundId;
	private MovieModel firstMovie;
	private MovieModel secondMovie;
}
