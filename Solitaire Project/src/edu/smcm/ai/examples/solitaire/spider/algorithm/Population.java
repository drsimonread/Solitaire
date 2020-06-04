package edu.smcm.ai.examples.solitaire.spider.algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Population extends edu.smcm.ai.genetic.Population<Individual> {
	
	public Population(Factory factory) {
		super(factory);
	}
	
	public void dumpCSV(String file_name) {
		PrintStream output;
		
		try {
			output = new PrintStream(new File(file_name));
			
			for (Individual individual : individuals()) {
				individual.dumpCSV(output);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Could not create or write to " + file_name + ".");
		}
	}

	// TODO This will crash if the individuals have not been set up yet!
	public String title() {
		return individuals().get(0).title();
	}
}
