package edu.smcm.ai.examples.solitaire.spider;

public class Fitness {
	private double sun_score;
	private double score_two;
	private double windows_score;
	private int wins;
	private int moves;
	private int collected;
	private int games;
	
	public Fitness(double sun_score, double score_two, double windows_score, int wins, int moves, int collected, int games) {
		this.sun_score = sun_score;
		this.score_two = score_two;
		this.windows_score = windows_score;
		this.wins = wins;
		this.moves = moves;
		this.collected = collected;
		this.games = games;
	}

	/**
	 * Constructor for the fitness of a single game.
	 * 
	 * @param sun_score
	 * @param windows_score
	 * @param moves
	 * @param win
	 */
	public Fitness(double sun_score, double score_two, double windows_score, int moves, int collected, boolean win) {
		this.sun_score = sun_score;
		this.score_two = score_two;
		this.windows_score = windows_score;
		this.wins = win ? 1 : 0;
		this.moves = moves;
		this.collected = collected;
		this.games = 1;
	}
	
	public Fitness() {
		this(0.0, 0.0, 0.0, 0, 0, 0, 0);
	}
	
	public double sunScore() {
		return sun_score / games;
	}
	
	public double scoreTwo() {
		return score_two / games;
	}
	
	public double windowsScore() {
		return windows_score / games;
	}
	
	public int wins() {
		return wins;
	}
	
	public double moves() {
		return moves / games;
	}
	
	public double collected() {
		return collected / games;
	}
	
	public int games() {
		return games;
	}
	
	public double winRatio() {
		return (double) wins / games;
	}
	
	public void update(double sun_score, double score_two, double windows_score, int moves, int collected, boolean win) {
		this.sun_score = this.sun_score + sun_score;
		this.score_two = this.score_two + score_two;
		this.windows_score = this.windows_score + windows_score;
		this.wins = this.wins + (win ? 1 : 0);
		this.moves = this.moves + moves;
		this.collected = this.collected + collected;
		this.games = this.games + 1;
	}
	
	public void update(Fitness that) {
		this.sun_score = this.sun_score + that.sun_score;
		this.score_two = this.score_two + that.score_two;
		this.windows_score = this.windows_score + that.sun_score;
		this.wins = this.wins + that.wins;;
		this.moves = this.moves + that.moves;
		this.collected = this.collected + that.collected;
		this.games = this.games + that.games;
	}
}
