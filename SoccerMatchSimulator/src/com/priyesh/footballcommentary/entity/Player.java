package com.priyesh.footballcommentary.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
public class Player {
	@Id
    @GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Type(type="pg-uuid")
	@Column(name = "playerid", unique = true)
	private UUID playerId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "overall")
	private int overall;
	
	@Column(name = "position")
	private String position;
	
	@Column(name = "injured")
	private boolean injured;
	
	@Column(name = "injuryLength")
	private int injuryLength;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teamid")
	private Team team;
	
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}

	public boolean isInjured() {
		return injured;
	}
	public void setInjured(boolean injured) {
		this.injured = injured;
	}
	public int getInjuryLength() {
		return injuryLength;
	}
	public void setInjuryLength(int injuryLength) {
		this.injuryLength = injuryLength;
	}
	
	public UUID getPlayerId() {
		return playerId;
	}
	public void setPlayerId(UUID playerId) {
		this.playerId = playerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOverall() {
		return overall;
	}
	public void setOverall(int overall) {
		this.overall = overall;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
}
