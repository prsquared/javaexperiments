package com.priyesh.footballcommentary.engine;
import java.util.ArrayList;
import java.util.List;

public class GameCommentary {
	
	public static int homeTeam = 0;
	public static int awayTeam = 0;
	public static int time = 0;
	
	public final static long TINY_INTERVAL=0;
	public final static long SMALL_INTERVAL=0;
	public final static long LARGE_INTERVAL=0;

	public static void playGame() {
		int totalTime = 90 + (int)(getRandomDecimal()*5);
		int halfTime = 45 + (int)(getRandomDecimal()*5);
		boolean homePossession = true; //Flag that dictates which team is in possession
		boolean inProgress = true;
		boolean secondHalf = false;
		
		
		while (inProgress) {
			try {
				time++;
				homePossession = getHomePossession();
				if (homePossession) {
					usePossession(homePossession);
				} else {
					usePossession(homePossession);
				}
				if (!secondHalf && time >= halfTime){//Stop match at Half Time
					System.out.println("\nThe Referee has blown the Half-time whistle.");
					System.out.println("HT : Score: Home " + homeTeam + " - " + awayTeam + " Away\n");
					time=45; //Reset Half-Time;
					secondHalf = true;
					Thread.sleep(LARGE_INTERVAL);
				}
				if (time >= totalTime){ //End match
					System.out.println("\nThe Referee has blown the final whistle.");
					System.out.println("FT : Final Score: Home " + homeTeam + " - " + awayTeam + " Away");
					inProgress = false;
				}
			} catch(InterruptedException e) {
				inProgress = false;
				System.out.println("\nThe match has been abandoned");
				System.out.println(" Score: Home " + homeTeam + " - " + awayTeam + " Away");
			}
		}

	}
	
	public static void usePossession(boolean homePossession) throws InterruptedException {
		double probability = getRandomDecimal();
		String inPossessionTeam = getInPossessionTeam(homePossession);
		
		if (probability < 0.05) {
			printAllActions(inPossessionTeam);
			Thread.sleep(SMALL_INTERVAL);
			getRandomMissedChanceAction(inPossessionTeam);
		} else if (probability<0.10) {
			printAllActions(inPossessionTeam);
			if(homePossession)
				homeTeam++;
			else
				awayTeam++;
			Thread.sleep(SMALL_INTERVAL);
			getRandomScoringAction(inPossessionTeam);
			Thread.sleep(LARGE_INTERVAL);
			System.out.println(time +"': Home " + homeTeam + " - " + awayTeam + " Away \n");
			
		} else if (probability<0.25) {
			Thread.sleep(SMALL_INTERVAL);
			getRandomLoseBallAction(inPossessionTeam);
			time++;
			usePossession(!homePossession);
		}
		else {
			// Nothing is happening
			Thread.sleep(TINY_INTERVAL);
		}
		
	}
	
	public static double getRandomDecimal() {
		return Math.random();
	}
	
	public static boolean getHomePossession() {
		return (getRandomDecimal() <= 0.53); //Weighted coin toss to give slight advantage to home team;
	}
	
	public static String getInPossessionTeam(boolean homePossession){
		return homePossession?"Home":"Away";
	}
	
	public static void getRandomTransitionAction(String inPossessionTeam) {
		List<String> transitionActionList =  new ArrayList<>();
		transitionActionList.add(time +"': "+ inPossessionTeam + " team is in possession");
		transitionActionList.add(time +"': "+ inPossessionTeam + " team is building from the back here");
		transitionActionList.add(time +"': "+ inPossessionTeam + " team has done well to win the ball there");
		transitionActionList.add(time +"': "+ inPossessionTeam + " team relieves the pressure with a clearance");
		transitionActionList.add(time +"': "+ inPossessionTeam + " team stop a promising attack with an interception");
		transitionActionList.add(time +"': Safe hands from "+ inPossessionTeam + " team goalkeeper as he comes out and claims the ball");
		
		System.out.println(transitionActionList.get((int)(Math.random()*transitionActionList.size())));
		
	}
	
	
	public static void getRandomBuildUpAction(String inPossessionTeam) {
		List<String> buildUpActionList =  new ArrayList<>();
		buildUpActionList.add(time +"': Beautiful intricate passing from "+ inPossessionTeam + " team");
		buildUpActionList.add(time +"': "+ inPossessionTeam + " team has worked the ball wide");
		buildUpActionList.add(time +"': "+ inPossessionTeam + " team start a counter attack");
		buildUpActionList.add(time +"': "+ inPossessionTeam + " team has played the ball through"); 
		buildUpActionList.add(time +"': "+ inPossessionTeam + " team sends a long ball upfield");
		
		System.out.println(buildUpActionList.get((int)(Math.random()*buildUpActionList.size())));
	}
	
	public static void getRandomFinishingAction(String inPossessionTeam) {
		List<String> finishingActionList =  new ArrayList<>();
		finishingActionList.add(time +"': Great chance for "+ inPossessionTeam + " team");
		finishingActionList.add(time +"': Good effort by "+ inPossessionTeam + " team as they direct a shot on target");
		finishingActionList.add(time +"': "+ inPossessionTeam + " team goes for goal");
		finishingActionList.add(time +"': "+ inPossessionTeam + " team directs a ball squarely in the box");
		finishingActionList.add(time +"': "+ inPossessionTeam + " team carves the opponent's defense open with a killer through-ball");
		
		System.out.println(finishingActionList.get((int)(Math.random()*finishingActionList.size())));
	}
	
	public static void getRandomScoringAction(String inPossessionTeam) {
		List<String> scoringActionList =  new ArrayList<>();
		scoringActionList.add(time + "': " + inPossessionTeam + " team has scored! ");
		scoringActionList.add(time +"': "+ inPossessionTeam + " team taps the ball in! ");
		scoringActionList.add(time +"': What a great goal!!! ");
		scoringActionList.add(time +"': Stunning strike!!! ");
		scoringActionList.add(time +"': And they've scored!!! ");
		scoringActionList.add(time +"': Oh no, they've put it into their own goal!!! ");
		scoringActionList.add(time +"': "+ inPossessionTeam + " team puts it away! ");
		
		System.out.println("\n" + scoringActionList.get((int)(Math.random()*scoringActionList.size())));
		
	}
	
	public static void getRandomLoseBallAction(String inPossessionTeam) {
		List<String> transitionActionList =  new ArrayList<>();
		transitionActionList.add(time +"': "+ inPossessionTeam + " team has lost possession");
		transitionActionList.add(time +"': Thats sloppy from "+ inPossessionTeam + " team");
		transitionActionList.add(time +"': "+ inPossessionTeam + " team should be doing better there");
		transitionActionList.add(time +"': "+ inPossessionTeam + " team has put themselves under pressure there");
		transitionActionList.add(time +"': "+ inPossessionTeam + " team has given the ball away");
		transitionActionList.add(time +"': Goal Kick.");
		transitionActionList.add(time +"': Throw In.");
		transitionActionList.add(time +"': "+ inPossessionTeam + " team has given away a Free kick.");
		
		System.out.println(transitionActionList.get((int)(Math.random()*transitionActionList.size())));
		
	}
	
	public static void getRandomMissedChanceAction(String inPossessionTeam) {
		List<String> missedChanceActionList =  new ArrayList<>();
		missedChanceActionList.add(time +"': "+ inPossessionTeam + " team has missed a great chance!");
		missedChanceActionList.add(time +"': The shot lacks precision and sails wide");
		missedChanceActionList.add(time +"': But the keeper saves it!");
		missedChanceActionList.add(time +"': The keeper makes a great save!");
		missedChanceActionList.add(time +"': "+ inPossessionTeam + " team should be doing better there");
		
		System.out.println(missedChanceActionList.get((int)(Math.random()*missedChanceActionList.size())));
		
	}
	
	public static void printAllActions(String inPossessionTeam) throws InterruptedException {
		Thread.sleep(SMALL_INTERVAL);
		getRandomTransitionAction(inPossessionTeam);
		Thread.sleep(SMALL_INTERVAL);
		getRandomBuildUpAction(inPossessionTeam);
		Thread.sleep(SMALL_INTERVAL);
		getRandomFinishingAction(inPossessionTeam);
		Thread.sleep(SMALL_INTERVAL);
	}
}
