package edu.smcm.ai.examples.solitaire.spider;

import edu.smcm.util.ImplementationErrorException;

@SuppressWarnings("serial")
public class IllegalMoveException extends Exception {
	private Move move;

	public IllegalMoveException(Move move) {
		try {
			this.move = (Move) move.clone();
		} catch (CloneNotSupportedException caught) {
			throw new ImplementationErrorException(caught);
		}
	}

	public String toString() {
		return "Illegal move " + move + "].";
	}
}
