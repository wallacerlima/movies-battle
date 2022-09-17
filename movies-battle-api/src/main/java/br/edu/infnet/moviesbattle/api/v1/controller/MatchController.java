package br.edu.infnet.moviesbattle.api.v1.controller;

import br.edu.infnet.moviesbattle.core.security.ApiSecurity;
import br.edu.infnet.moviesbattle.domain.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.moviesbattle.api.v1.assembler.MatchModelAssembler;
import br.edu.infnet.moviesbattle.api.v1.model.MatchModel;

@RestController
@RequestMapping(value = "/v1/match", produces = MediaType.APPLICATION_JSON_VALUE)
public class MatchController {
	
	@Autowired
	private MatchService matchService;
	
	@Autowired
	private MatchModelAssembler matchModelAssembler;
	
	@Autowired
	private ApiSecurity apiSecurity;
	
	@GetMapping("/start")
    public MatchModel start() {
		
		var matchStarted = matchService.startWith(apiSecurity.getPlayerId());
		
        return matchModelAssembler.toModel(matchStarted);
    }
	
	@PostMapping("/finish")
    public MatchModel finish() {
		
		var matchFinished = matchService.finishWith(apiSecurity.getPlayerId());
		
        return matchModelAssembler.toModel(matchFinished);
    }
}
