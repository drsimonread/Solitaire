package edu.smcm.ai.examples.solitaire.spider;

public abstract class Move implements Cloneable {

	public String title() {
		return String.format("%1$-9s", "Move");
	}
	
	// TODO This cloning is massively ugly to create a copy constructor!
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	};
}
