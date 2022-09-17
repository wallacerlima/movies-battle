package br.edu.infnet.moviesbattle.api.v1.assembler;

import br.edu.infnet.moviesbattle.domain.model.Round;
import org.springframework.stereotype.Component;

import br.edu.infnet.moviesbattle.api.v1.model.MovieModel;
import br.edu.infnet.moviesbattle.api.v1.model.RoundModel;

@Component
public class RoundModelAssembler {
	
	public RoundModel toModel(Round round) {
		
		MovieModel firstMovieModel = new MovieModel();
		firstMovieModel.setMovieId(round.getFirstMovie().getId());
		firstMovieModel.setTitle(round.getFirstMovie().getTitle());
		firstMovieModel.setYear(round.getFirstMovie().getYear());
		firstMovieModel.setGenre(round.getFirstMovie().getGenre());
		
		MovieModel secondMovieModel = new MovieModel();
		secondMovieModel.setMovieId(round.getSecondMovie().getId());
		secondMovieModel.setTitle(round.getSecondMovie().getTitle());
		secondMovieModel.setYear(round.getSecondMovie().getYear());
		secondMovieModel.setGenre(round.getSecondMovie().getGenre());
		
		RoundModel roundModel = new RoundModel();
		roundModel.setRoundId(round.getId());
		roundModel.setFirstMovie(firstMovieModel);
		roundModel.setSecondMovie(secondMovieModel);
		
		return roundModel;
	}

}
