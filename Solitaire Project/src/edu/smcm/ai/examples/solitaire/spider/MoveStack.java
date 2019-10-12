package edu.smcm.ai.examples.solitaire.spider;

public class MoveStack extends Move {

	private int from;
	private int cards;
	private int to;
	
	public MoveStack(int from, int cards, int to) {
		this.from = from;
		this.cards = cards;
		this.to = to;
	}
	
	public Object clone() throws CloneNotSupportedException {
		MoveStack result;
		
		result = (MoveStack) super.clone();
		result.from = this.from;
		result.cards = this.cards;
		result.to = this.to;
		
		return result;
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
	
}
