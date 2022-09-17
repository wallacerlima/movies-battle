package br.edu.infnet.moviesbattle.fixtures.builders.ranking;

import br.edu.infnet.moviesbattle.domain.model.Ranking;
import br.edu.infnet.moviesbattle.fixtures.builders.player.PlayerBuilder;

public class RankingBuilder {
	
	private Ranking ranking;
	
	private RankingBuilder(){}
	
	public static RankingBuilder aRanking() {
		RankingBuilder builder = new RankingBuilder();
		builder.ranking = new Ranking(PlayerBuilder.aPlayer().build(), 100L);
		builder.ranking.setId("e15b98b5-a84b-4b95-ab67-2cf1f9086248");
		return builder;
	}
	
	public Ranking build(){
		return ranking;
	}
}
