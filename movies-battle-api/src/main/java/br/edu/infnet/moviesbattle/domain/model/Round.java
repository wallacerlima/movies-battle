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

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Round {
	
	@EqualsAndHashCode.Include
	@Id
    @GenericGenerator(name = "roundId", strategy = "uuid2")
    @GeneratedValue(generator = "roundId")
	private String id;
	
    @ManyToOne
    @JoinColumn(name="first_movie_id", nullable = false, referencedColumnName = "id")
    private Movie firstMovie;

    @ManyToOne
    @JoinColumn(name="second_movie_id", nullable = false, referencedColumnName = "id")
    private Movie secondMovie;
    
    @Column(name = "answered")
    private Boolean answered = false;

    @Column(name = "result")
    private RoundResult result;
    
    @ManyToOne
    @JoinColumn(name="match_id", nullable = false, referencedColumnName = "id")
    private Match match;
    
    public void finishRound(Boolean answeredCorrectly) {
    	this.answered = true;
    	
    	if(answeredCorrectly == true) {
    		result = RoundResult.CORRECT;
    	} else {
    		result = RoundResult.WRONG;
    		this.getMatch().setRemainingAttempts(this.getMatch().getRemainingAttempts() - 1);
    	}	
    }

}
