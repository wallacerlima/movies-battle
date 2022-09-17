package br.edu.infnet.moviesbattle.core.security;

import java.util.Optional;

import br.edu.infnet.moviesbattle.domain.model.Player;
import br.edu.infnet.moviesbattle.domain.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private PlayerRepository playerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Player> playerOptional = playerRepository.findByUsername(username);
		var player = playerOptional.orElseThrow(() -> new UsernameNotFoundException("User and/or password are incorrect"));
		
		return User.withUsername(player.getUsername())
				.password(player.getPassword())
				.authorities("USER_PLAYER")
				.build();
	}

}