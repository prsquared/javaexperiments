package com.priyesh.footballcommentary.utils;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ajbrown.namemachine.Gender;
import org.ajbrown.namemachine.Name;
import org.ajbrown.namemachine.NameGenerator;

import com.priyesh.footballcommentary.dao.FootballCommentaryDao;
import com.priyesh.footballcommentary.entity.Player;
import com.priyesh.footballcommentary.entity.Team;
import com.priyesh.footballcommentary.hibernate.HibernateUtil;



public class TeamUtil {
	
	private static FootballCommentaryDao footballDao;
	
	public static String[] teamNames= {"Man City","Man Utd","Spurs","Liverpool","Chelsea","Arsenal","Burnley",
			"Everton","Leicester", "Newcastle","Palace","Bournemouth","West Ham","Watford",
			"Brighton","Huddersfield","Southampton","Swansea","Stoke","West Brom"};
	public static int[] ratings = {95,95,93,92,89,85,81,83,83,81,82,79,82,80,79,77,81,81,82,79};
	public static double[] maxPotentialChanceArray = {0.18,0.15,0.15,0.14,0.15,0.18,0.20,0.12,0.12,0.14,0.15,0.11,0.11,0.11,0.15,0.16,0.13,0.10,0.09,0.09};
	
	public static void saveTeamUtility(Team team) {
		getFootballDao().saveTeam(team);
	}
	
	public static void deleteTeamUtility(List<String> teams) {
		getFootballDao().deleteTeam(teams);
		HibernateUtil.getSessionJavaConfigFactory().close();
	}
	
	private static FootballCommentaryDao getFootballDao(){
		if(footballDao == null)
			return new FootballCommentaryDao();
		return footballDao;
	}
	
	public static void deleteTeams() {
		deleteTeamUtility(Arrays.asList(teamNames));
	}
	public static void createTeamSeedData() {

		for(int i=0;i<teamNames.length;i++) {
			try {
				int teamRating = ratings[i];
				double maxPotentialChance = maxPotentialChanceArray[i];
				String teamName = teamNames[i];
				if(teamExists(teamName))
					throw new Exception(teamName + " already exists");
				NameGenerator generator = new NameGenerator();
				List<Name> forwardNames = generator.generateNames( 4, Gender.MALE );
				List<Name> midfielderNames = generator.generateNames( 8, Gender.MALE );
				List<Name> defendernames = generator.generateNames( 8, Gender.MALE );
				List<Name> goalkeeperNames = generator.generateNames( 3, Gender.MALE );
				Set<Player> players = new HashSet<>();
				
				for(Name name:forwardNames) {
					int rating = generateRandomRating(teamRating,maxPotentialChance);
					Player player = EntityCreator.populatePlayerEntity(null, name.toString(), rating, "ST", false, 0);
					players.add(player);
				}
				for(Name name:midfielderNames) {
					int rating = generateRandomRating(teamRating,maxPotentialChance);
					Player player = EntityCreator.populatePlayerEntity(null, name.toString(), rating, "MF", false, 0);
					players.add(player);
				}
				for(Name name:defendernames) {
					int rating = generateRandomRating(teamRating,maxPotentialChance);
					Player player = EntityCreator.populatePlayerEntity(null, name.toString(), rating, "DF", false, 0);
					players.add(player);
				}
				for(Name name:goalkeeperNames) {
					int rating = generateRandomRating(teamRating,maxPotentialChance);
					Player player = EntityCreator.populatePlayerEntity(null, name.toString(), rating, "GK", false, 0);
					players.add(player);
				}
				Team team = EntityCreator.populateTeamEntity(null, teamName, players, teamRating);
				
				saveTeamUtility(team);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		HibernateUtil.getSessionJavaConfigFactory().close();
		
	}
	private static boolean teamExists(String teamName) {
		Team team = getFootballDao().getTeam(teamName);
		
		return (team != null) ;
	}
	private static int generateRandomRating( int maxRating, double maxPotentialChance) {
		
		double random = Math.random();
		if(random<maxPotentialChance)
		{
			return ((int) (Math.random()*5) + (maxRating-5));
		}else if(random<2*maxPotentialChance){
			return ((int) (Math.random()*5) + (maxRating-10));
		}else {
			return ((int) (Math.random()*10) + maxRating-20);
		}
	}
}
