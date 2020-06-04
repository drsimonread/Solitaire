package edu.smcm.ai.examples.solitaire.spider;

/**
 * The special move Deal Next Row is represented by a from column of 99.
 */		
public class Move {
	
	public static final int deal_new_row;

	private int from;
	private int cards;
	private int to;
	
	static {
		deal_new_row = 99;
	}
	
	public Move(int from, int cards, int to) {
		this.from = from;
		this.cards = cards;
		this.to = to;
	}
	
	// TODO This cloning is massively ugly to create a copy constructor!
	// TODO Why is this necessary?
	public Object clone() throws CloneNotSupportedException {
		
		return new Move(from, cards, to);
	}

	public int from() {
		return from;
	}
	
	public int cards() {
		return cards;
	}
	
	public int to() {
		return to;
	}

	public String toString() {
		return String.format("%1$ 2d %2$ 2d %3$ 2d", from, cards, to);
	}
	
	public String title() {
		return String.format("%1$-9s", "Move");
	}
	

}
