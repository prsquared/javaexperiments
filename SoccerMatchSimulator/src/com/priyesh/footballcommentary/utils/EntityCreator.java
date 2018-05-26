package com.priyesh.footballcommentary.utils;

import java.util.Set;
import java.util.UUID;

import com.priyesh.footballcommentary.entity.Player;
import com.priyesh.footballcommentary.entity.Team;

public class EntityCreator {

	public static Team populateTeamEntity(UUID id, String name, Set<Player> players, int teamStrength) {
		Team team = new Team();
		team.setTeamId(id);
		team.setName(name);
		team.setAllPlayersSet(players);
		team.setTeamStrength(teamStrength);
		
		return team;
	}
	
	public static Player populatePlayerEntity(UUID id, String name, int overall, String position, boolean injured, int injuryLength) {
		
		Player player = new Player();
		player.setPlayerId(id);
		player.setName(name);
		player.setPosition(position);
		player.setInjured(injured);
		player.setInjuryLength(injuryLength);
		player.setOverall(overall);
		
		return player;
	}
}
