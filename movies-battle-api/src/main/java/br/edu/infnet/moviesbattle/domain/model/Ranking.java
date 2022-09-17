package br.edu.infnet.moviesbattle.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Ranking {
	
	public Ranking(Player player, Long totalScore) {
		this.player = player;
		this.totalScore = totalScore;
	}

	@EqualsAndHashCode.Include
	@Id
    @GenericGenerator(name = "rankingId", strategy = "uuid2")
    @GeneratedValue(generator = "rankingId")
	private String id;
	
    @ManyToOne
    @JoinColumn(name="player_id", referencedColumnName = "id")
    private Player player;
    
	@Column(name = "total_score")
	private Long totalScore;
	
	public void updateRanking(Match match) {
		this.totalScore = this.totalScore + match.getScore();
	}
}
