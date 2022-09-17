package br.edu.infnet.moviesbattle.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Player {
	
	@EqualsAndHashCode.Include
	@Id
    @GenericGenerator(name = "playerId", strategy = "uuid2")
    @GeneratedValue(generator = "playerId")
	private String id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;
    
	@Column(name = "created_at", nullable = false, updatable = false)
	@CreationTimestamp
    private OffsetDateTime createdAt;
}
