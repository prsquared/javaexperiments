package com.priyesh.footballcommentary.entity;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
public class Team {
	@Id
    @GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Type(type="pg-uuid")
	@Column(name = "teamId", unique = true)
	private UUID teamId;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="teamid")
	private Set<Player> allPlayersSet;
	
	
	@Column(name = "teamstrength")
	private int teamStrength;
	
	
	public Team() {
		
	}

	public UUID getTeamId() {
		return teamId;
	}


	public void setTeamId(UUID teamId) {
		this.teamId = teamId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Set<Player> getAllPlayersSet() {
		return allPlayersSet;
	}


	public void setAllPlayersSet(Set<Player> allPlayersSet) {
		this.allPlayersSet = allPlayersSet;
	}


	public int getTeamStrength() {
		return teamStrength;
	}


	public void setTeamStrength(int teamStrength) {
		this.teamStrength = teamStrength;
	}
	
	
}
