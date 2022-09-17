package br.edu.infnet.moviesbattle.api.v1.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchModel {
	private String matchId;
	private PlayerModel player;
	private OffsetDateTime startedAt;
	private Long score;
}
