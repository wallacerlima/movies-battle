package br.edu.infnet.moviesbattle.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.moviesbattle.domain.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
	public Optional<Player> findByUsername(String username);
}
