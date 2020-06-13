package edu.smcm.ai.examples.solitaire.spider.algorithm;

import java.util.List;
import java.util.Scanner;

import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.IllegalMoveException;
import edu.smcm.ai.examples.solitaire.spider.Move;
import edu.smcm.ai.examples.solitaire.spider.variables.CreatedEmptyStack;
import edu.smcm.ai.examples.solitaire.spider.variables.DirtyFlush;
import edu.smcm.ai.examples.solitaire.spider.variables.Discovery;
import edu.smcm.ai.examples.solitaire.spider.variables.FillsEmptyStack;
import edu.smcm.ai.examples.solitaire.spider.variables.NumberOfCards;
import edu.smcm.ai.examples.solitaire.spider.variables.OpensMoveStackDestination;
import edu.smcm.ai.examples.solitaire.spider.variables.StraightFlush;
import edu.smcm.ai.examples.solitaire.spider.variables.TopMoved;

public class PlayGame {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Game game;
		Scanner keyboard;
		int from_stack;
		int number_of_cards;
		int to_stack;
		List<Move> allowable;
		StraightFlush straight_flush;
		DirtyFlush dirty_flush;
		NumberOfCards cards_moved;
		TopMoved top_card;
		Discovery discovery;
		OpensMoveStackDestination opens_move;
		FillsEmptyStack fills_empty;
		CreatedEmptyStack makes_empty;
		Position position;

		keyboard = new Scanner(System.in);

		game = new Game(2, true);

		straight_flush = new StraightFlush();
		dirty_flush = new DirtyFlush();
		cards_moved = new NumberOfCards();
		top_card = new TopMoved();
		discovery = new Discovery();
		opens_move = new OpensMoveStackDestination();
		fills_empty = new FillsEmptyStack();
		makes_empty = new CreatedEmptyStack();

		while (!game.won() && !game.lost()) {
			System.out.println(game);

			allowable = game.possibleMoves();

			for (Move move : allowable) {
				System.out.print(move);
				position = new Position(game, move);
				// TODO Fix printing of Variables3 1 8
/*				System.out.printf(" :  %3d %3d %3d %3d %3d %3d %3d % 3d", top_card.evaluate(position),
						cards_moved.evaluate(position), straight_flush.evaluate(position),
						dirty_flush.evaluate(position), discovery.evaluate(position), opens_move.evaluate(position),
						fills_empty.evaluate(position), makes_empty.evaluate(position));
						*/
				System.out.println();
			}

			System.out.print("Your move: ");

			from_stack = keyboard.nextInt();
			number_of_cards = keyboard.nextInt();
			to_stack = keyboard.nextInt();

			try {
				game.move(new Move(from_stack, number_of_cards, to_stack));
			} catch (IllegalMoveException caught) {
				System.out.println("That's an illegal move!");
			} catch (Exception caught) {
				System.err.println(caught);
				caught.printStackTrace();
			}
		}

		if (game.won()) {
			System.out.println("You won!");
		} else {
			System.out.println("You lost!");
		}
	}
}
