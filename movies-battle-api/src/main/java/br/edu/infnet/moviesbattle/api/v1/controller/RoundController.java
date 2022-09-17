package br.edu.infnet.moviesbattle.api.v1.controller;

import javax.validation.Valid;

import br.edu.infnet.moviesbattle.core.security.ApiSecurity;
import br.edu.infnet.moviesbattle.domain.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.moviesbattle.api.v1.assembler.RoundModelAssembler;
import br.edu.infnet.moviesbattle.api.v1.assembler.RoundResultAnswerModelAssembler;
import br.edu.infnet.moviesbattle.api.v1.model.RoundModel;
import br.edu.infnet.moviesbattle.api.v1.model.RoundResultAnswerModel;
import br.edu.infnet.moviesbattle.api.v1.model.input.RoundAnswerInput;

@RestController
@RequestMapping(value = "/v1/round", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoundController {

	@Autowired
	private ApiSecurity apiSecurity;
	
	@Autowired
	private RoundService roundService;
	
	@Autowired
	private RoundModelAssembler roundModelAssembler;
	
	@Autowired
	private RoundResultAnswerModelAssembler roundResultAnswerModelAssembler;
	
    @GetMapping
    public RoundModel getNextRound() {
		var nextRound = 
				roundService.getNextRound(apiSecurity.getPlayerId());
		
        return roundModelAssembler.toModel(nextRound);
    }
    
    @PostMapping("/answer")
    public RoundResultAnswerModel roundAnswer(@RequestBody @Valid RoundAnswerInput roundAnswerInput) {
    	
    	var round = roundService.saveRoundAnswer(roundAnswerInput.getMovieId(), apiSecurity.getPlayerId());

        return roundResultAnswerModelAssembler.toModel(round);
    }
	
}
