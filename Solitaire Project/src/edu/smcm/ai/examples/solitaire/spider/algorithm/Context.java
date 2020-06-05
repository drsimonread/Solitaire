package edu.smcm.ai.examples.solitaire.spider.algorithm;

import java.util.List;

import edu.smcm.ai.examples.solitaire.spider.Fitness;
import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.IllegalMoveException;
import edu.smcm.ai.examples.solitaire.spider.Move;
import edu.smcm.ai.genetic.Genotype;
import edu.smcm.games.cards.CardsFaceDownException;
import edu.smcm.games.cards.NotEnoughCardsException;
import edu.smcm.util.ImplementationErrorException;

public class Context extends edu.smcm.ai.genetic.Context {

	private static int maximum_number_of_moves;
	
	
	private List<Game> games;	
	
	public Context(int number_of_games, int suits, boolean relaxed) {
		for (int count = 0; count < number_of_games; count++) {
			games.add(new Game(suits, relaxed));
		}
	}

	static {
		maximum_number_of_moves = 1000;		
	}
	
	public static void maximum_number_of_moves (int maximum_number_of_moves) {
		Context.maximum_number_of_moves = maximum_number_of_moves;
	}
	
	public Move selectBestMove(Game game, List<Move> possible_moves, Genotype genotype) {
		Move best_move;
		double best_score;
		double score;

		best_move = null;
		best_score = 0.0;
		for (Move move : possible_moves) {
			if (null == best_move) {
				best_move = move;
				best_score = genotype.evaluate(new Position(game, move));
			} else {
				score = genotype.evaluate(new Position(game, move));
				if (score > best_score) {
					best_move = move;
					best_score = score;
				}
			}
		}

		return best_move;
	}

	public Fitness play(Game game, Genotype genotype) {
		boolean won;
		boolean lost;
		int moves;
		List<Move> possible_moves;
		
		moves = 0;
		won = false;
		lost = false;
		while (moves < maximum_number_of_moves && !(won || lost)) {
			possible_moves = game.possibleMoves();
			try {
				game.move(selectBestMove(game, possible_moves, genotype));
			} catch (IllegalMoveException caught) {
				throw new ImplementationErrorException(caught);
			} catch (NotEnoughCardsException caught) {
				throw new ImplementationErrorException(caught);
			} catch (CardsFaceDownException caught) {
				throw new ImplementationErrorException(caught);
			}
			won = game.won();
			lost = game.lost();
			moves = moves + 1;
		}

		return new Fitness(game.scoreSun(), game.scoreTwo(), game.scoreWindows(), moves, game.collected(), won);
	}
	
	@Override
	public edu.smcm.ai.genetic.Fitness evaluate(Genotype genotype) {
		Fitness fitness;
		Game game;
		
		fitness = new Fitness();
		
		for (int count = 0; count < games.size(); count++) {
			// Copy the game before playing it as update will change the game as moves are made.
			game = new Game(games.get(count));
			fitness.update(play(game, genotype));
		}
		
		return fitness;
	}

}
