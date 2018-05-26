package com.prsquared.fixturesimulator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prsquared.fixturesimulator.gamesimulator.GameCommentary;




@Controller
public class FixtureSimulatorController {

	@RequestMapping("/")
	public @ResponseBody String simulateGame() {
		GameCommentary.playGame();	
		return "Success!";
	}
}
