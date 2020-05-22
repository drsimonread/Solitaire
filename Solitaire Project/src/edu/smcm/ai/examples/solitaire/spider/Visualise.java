package edu.smcm.ai.examples.solitaire.spider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
		Heuristic heuristic;
		double weight;

		keyboard = new Scanner(System.in);

		game = new Game(2, true);

		random = new Random();
		genotype = new Genotype(random);

		System.out.println("Enter weights:");

		heuristic = new CreatedEmptyStack();
		System.out.print(heuristic.abbreviation() + ": ");
		weight = keyboard.nextDouble();
		genotype.addGene(weight, heuristic);

		heuristic = new DealNewRowRecogniser();
		System.out.print(heuristic.abbreviation() + ": ");
		weight = keyboard.nextDouble();
		genotype.addGene(weight, heuristic);

		heuristic = new DirtyFlush();
		System.out.print(heuristic.abbreviation() + ": ");
		weight = keyboard.nextDouble();
		genotype.addGene(weight, heuristic);

		heuristic = new Discovery();
		System.out.print(heuristic.abbreviation() + ": ");
		weight = keyboard.nextDouble();
		genotype.addGene(weight, heuristic);

		heuristic = new FillsEmptyStack();
		System.out.print(heuristic.abbreviation() + ": ");
		weight = keyboard.nextDouble();
		genotype.addGene(weight, heuristic);

		heuristic = new NumberOfCards();
		System.out.print(heuristic.abbreviation() + ": ");
		weight = keyboard.nextDouble();
		genotype.addGene(weight, heuristic);

		heuristic = new OpensMoveStackDestination();
		System.out.print(heuristic.abbreviation() + ": ");
		weight = keyboard.nextDouble();
		genotype.addGene(weight, heuristic);

		heuristic = new StraightFlush();
		System.out.print(heuristic.abbreviation() + ": ");
		weight = keyboard.nextDouble();
		genotype.addGene(weight, heuristic);

		heuristic = new TopMoved();
		System.out.print(heuristic.abbreviation() + ": ");
		weight = keyboard.nextDouble();
		genotype.addGene(weight, heuristic);
		
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
