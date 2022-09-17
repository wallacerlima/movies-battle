package br.edu.infnet.moviesbattle.fixtures.builders.match;

import java.time.OffsetDateTime;

import br.edu.infnet.moviesbattle.domain.model.Match;
import br.edu.infnet.moviesbattle.domain.model.Player;

public class MatchBuilder {
	
	private Match match;
	
	private MatchBuilder(){}
	
	public static MatchBuilder aMatch() {
		MatchBuilder builder = new MatchBuilder();
		builder.match = new Match();
		builder.match.setId("e15b98b5-a84b-4b95-ab67-2cf1f9086248");
		builder.match.setRemainingAttempts(3);
		builder.match.setStartedAt(OffsetDateTime.parse("2022-03-02T13:05:04.027111-03:00"));
		builder.match.setScore(0L);
		return builder;
	}
	
	public MatchBuilder finished(){
		match.setFinishedAt(OffsetDateTime.parse("2022-03-02T13:05:04.027111-03:00"));
		return this;
	}
	
	public MatchBuilder withPlayer(Player player){
		match.setPlayer(player);
		return this;
	}
	
	public Match build(){
		return match;
	}
}
