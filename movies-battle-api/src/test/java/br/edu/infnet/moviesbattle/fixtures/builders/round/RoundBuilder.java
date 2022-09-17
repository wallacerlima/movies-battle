package br.edu.infnet.moviesbattle.fixtures.builders.round;

import br.edu.infnet.moviesbattle.domain.model.Match;
import br.edu.infnet.moviesbattle.domain.model.Round;
import br.edu.infnet.moviesbattle.domain.model.RoundResult;
import br.edu.infnet.moviesbattle.fixtures.builders.movie.MovieBuilder;

public class RoundBuilder {
	
	private Round round;
	
	private RoundBuilder(){}
	
	public static RoundBuilder aRound() {
		RoundBuilder builder = new RoundBuilder();
		builder.round = new Round();
		builder.round.setId("e15b98b5-a84b-4b95-ab67-2cf1f9086248");
		builder.round.setFirstMovie(MovieBuilder.aMovie().One().build());
		builder.round.setSecondMovie(MovieBuilder.aMovie().Two().build());
		return builder;
	}
	
	public RoundBuilder answeredCorrect(){
		round.setAnswered(true);
		round.setResult(RoundResult.CORRECT);
		return this;
	}
	
	public RoundBuilder answeredWrong(){
		round.setAnswered(true);
		round.setResult(RoundResult.WRONG);
		return this;
	}
	
	public RoundBuilder notAnswered(){
		round.setAnswered(false);
		return this;
	}
	
	public RoundBuilder withMatch(Match match){
		round.setMatch(match);
		return this;
	}
	
	public Round build(){
		return round;
	}
}
