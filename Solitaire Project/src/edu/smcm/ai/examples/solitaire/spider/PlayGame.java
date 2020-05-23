package edu.smcm.ai.examples.solitaire.spider;

import java.util.List;
import java.util.Scanner;

import edu.smcm.ai.examples.solitaire.spider.heuristics.CreatedEmptyStack;
import edu.smcm.ai.examples.solitaire.spider.heuristics.DealNewRow;
import edu.smcm.ai.examples.solitaire.spider.heuristics.DirtyFlush;
import edu.smcm.ai.examples.solitaire.spider.heuristics.Discovery;
import edu.smcm.ai.examples.solitaire.spider.heuristics.FillsEmptyStack;
import edu.smcm.ai.examples.solitaire.spider.heuristics.NumberOfCards;
import edu.smcm.ai.examples.solitaire.spider.heuristics.OpensMoveStackDestination;
import edu.smcm.ai.examples.solitaire.spider.heuristics.StraightFlush;
import edu.smcm.ai.examples.solitaire.spider.heuristics.TopMoved;
import edu.smcm.util.Util;

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
				// TODO Fix object-orientism if you can
				if (move instanceof MoveStack) {
					System.out.printf(" :  %3d %3d %3d %3d %3d %3d %3d % 3d", top_card.value(game, (MoveStack) move),
							cards_moved.value(game, (MoveStack) move), straight_flush.value(game, (MoveStack) move),
							dirty_flush.value(game, (MoveStack) move), discovery.value(game, (MoveStack) move),
							opens_move.value(game, (MoveStack) move), fills_empty.value(game, (MoveStack) move),
							makes_empty.value(game, (MoveStack) move));
				} else {
					System.out.printf(" :  %3d %3d %3d %3d %3d %3d %3d % 3d", top_card.value(game, (DealNewRow) move),
							cards_moved.value(game, (DealNewRow) move), straight_flush.value(game, (DealNewRow) move),
							dirty_flush.value(game, (DealNewRow) move), discovery.value(game, (DealNewRow) move),
							opens_move.value(game, (DealNewRow) move), fills_empty.value(game, (DealNewRow) move),
							makes_empty.value(game, (DealNewRow) move));
				}
				System.out.println();
			}

			System.out.print("Your move: ");

			from_stack = keyboard.nextInt();
			number_of_cards = keyboard.nextInt();
			to_stack = keyboard.nextInt();

			try {
				if (from_stack == 99) {
					game.newRow();
				} else if (!Util.isBetween(0, from_stack, 9) || !Util.isBetween(0, to_stack, 9)) {
					System.out.println("That's an illegal move.");
				} else {
					game.move(from_stack, number_of_cards, to_stack);
				}
			} catch (IllegalMoveException caught) {
				// TODO Something more specific about move?
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
