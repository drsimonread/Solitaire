package edu.smcm.games.cards;

public class Card {

	private Suit suit;
	private int value;
	private boolean face_up;

	public static final int minimum_face_value;
	public static final int maximum_face_value;
	public static final int cards_in_suit;

	static {
		minimum_face_value = 1;
		maximum_face_value = 13;
		cards_in_suit = 13;
	}

	public Card(Suit suit, int value, boolean face_up) {
		this.suit = suit;
		this.value = value;
		this.face_up = face_up;
	}

	public Card(Suit suit, int value) {
		this(suit, value, false);
	}

	public int value() {
		return value;
	}

	public Suit suit() {
		return suit;
	}

	public boolean faceUp() {
		return face_up;
	}

	public void turnUp() {
		face_up = true;
	}

	public void flip() {
		face_up = !face_up;
	}

	public String toString() {
		String result;

		assert (0 <= value && value < maximum_face_value);

		if (face_up) {
			if (0 == value) {
				result = " JK";
			} else {
				switch (value) {
				case 10:
					result = "" + value;
					break;
				case 11:
					result = " J";
					break;
				case 12:
					result = " Q";
					break;
				case 13:
					result = " K";
					break;
				case 1:
					result = " A";
					break;
				default:
					result = " " + value;
					break;
				}

				result = result + suit;
			}
		} else {
			result = "---";
		}

		return result;
	}
}
