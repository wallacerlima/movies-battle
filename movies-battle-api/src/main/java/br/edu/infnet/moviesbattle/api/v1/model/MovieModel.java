package br.edu.infnet.moviesbattle.api.v1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieModel {
	private String movieId;
	private String title;
	private Integer year;
	private String genre;
}
