package br.edu.infnet.moviesbattle.fixtures.builders.player;

import java.time.OffsetDateTime;

import br.edu.infnet.moviesbattle.domain.model.Player;

public class PlayerBuilder {
	
	private Player player;
	
	private PlayerBuilder(){}
	
	public static PlayerBuilder aPlayer(){
		PlayerBuilder builder = new PlayerBuilder();
		builder.player = new Player();
		builder.player.setId("e15b98b5-a84b-4b95-ab67-2cf1f9086348");
		builder.player.setUsername("playertest");
		builder.player.setPassword("testpass");
		builder.player.setName("Player Test");
		builder.player.setCreatedAt(OffsetDateTime.parse("2022-03-02T13:05:04.027111-03:00"));
		return builder;
	}
	
	public Player build(){
		return player;
	}
}
