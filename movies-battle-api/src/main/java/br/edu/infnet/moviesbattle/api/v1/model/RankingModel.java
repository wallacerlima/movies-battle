package br.edu.infnet.moviesbattle.api.v1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankingModel {
	private Integer position;
	private String userName;
	private String name;
	private Long totalScore;
}
