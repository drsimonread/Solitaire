package edu.smcm.ai.examples.solitaire.spider.algorithm;

import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.Move;

public class Position extends edu.smcm.ai.genetic.Position {

	private Game game;
	private Move move;
	
	public Position(Game game, Move move) {
		this.game = game;
		this.move = move;
	}
	
	public Game game() {
		return this.game;
	}
	
	public Move move() {
		return this.move;
	}
}
