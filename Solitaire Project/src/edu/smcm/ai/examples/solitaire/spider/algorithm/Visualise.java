package edu.smcm.ai.examples.solitaire.spider.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.Move;
import edu.smcm.ai.examples.solitaire.spider.MoveVisualisation;
import edu.smcm.ai.examples.solitaire.spider.variables.CreatedEmptyStack;
import edu.smcm.ai.examples.solitaire.spider.variables.DealNewRow;
import edu.smcm.ai.examples.solitaire.spider.variables.DirtyFlush;
import edu.smcm.ai.examples.solitaire.spider.variables.Discovery;
import edu.smcm.ai.examples.solitaire.spider.variables.FillsEmptyStack;
import edu.smcm.ai.examples.solitaire.spider.variables.NumberOfCards;
import edu.smcm.ai.examples.solitaire.spider.variables.OpensMoveStackDestination;
import edu.smcm.ai.examples.solitaire.spider.variables.StraightFlush;
import edu.smcm.ai.examples.solitaire.spider.variables.TopMoved;
import edu.smcm.ai.genetic.Variable;
import edu.smcm.ai.genetic.algorithm.Genotype;

public class Visualise {

	// TODO Unify with PlayGame whose code is nearly identical
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Game game;
		Scanner keyboard;
		List<Move> allowable;
		List<MoveVisualisation> move_visualisations;
		Genotype genotype;
		Random random;
		Variable variable;
		double weight;

		keyboard = new Scanner(System.in);

		game = new Game(2, true);

		random = new Random();
		// TODO Initialise all static random number generators
		genotype = new Genotype();

		System.out.println("Enter weights:");

		variable = new CreatedEmptyStack();
		System.out.print(variable.abbreviation() + ": ");
		weight = keyboard.nextDouble();
		genotype.addGene(weight, variable);

		variable = new DealNewRow();
		System.out.print(variable.abbreviation() + ": ");
		weight = keyboard.nextDouble();
		genotype.addGene(weight, variable);

		variable = new DirtyFlush();
		System.out.print(variable.abbreviation() + ": ");
		weight = keyboard.nextDouble();
		genotype.addGene(weight, variable);

		variable = new Discovery();
		System.out.print(variable.abbreviation() + ": ");
		weight = keyboard.nextDouble();
		genotype.addGene(weight, variable);

		variable = new FillsEmptyStack();
		System.out.print(variable.abbreviation() + ": ");
		weight = keyboard.nextDouble();
		genotype.addGene(weight, variable);

		variable = new NumberOfCards();
		System.out.print(variable.abbreviation() + ": ");
		weight = keyboard.nextDouble();
		genotype.addGene(weight, variable);

		variable = new OpensMoveStackDestination();
		System.out.print(variable.abbreviation() + ": ");
		weight = keyboard.nextDouble();
		genotype.addGene(weight, variable);

		variable = new StraightFlush();
		System.out.print(variable.abbreviation() + ": ");
		weight = keyboard.nextDouble();
		genotype.addGene(weight, variable);

		variable = new TopMoved();
		System.out.print(variable.abbreviation() + ": ");
		weight = keyboard.nextDouble();
		genotype.addGene(weight, variable);
		
		keyboard.nextLine();
		System.out.println();

		while (!game.won() && !game.lost()) {
			System.out.println(game);

			allowable = game.possibleMoves();

			move_visualisations = new ArrayList<MoveVisualisation>();
			for (Move move : allowable) {
				move_visualisations.add(new MoveVisualisation(game, move, genotype));
			}

			Collections.sort(move_visualisations);
			Collections.reverse(move_visualisations);

			System.out.println(move_visualisations.get(0).title());

			for (MoveVisualisation visualisation : move_visualisations) {
				System.out.println(visualisation.visualise());
			}

			keyboard.nextLine();

			try {
				game.move(move_visualisations.get(0).move());
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
