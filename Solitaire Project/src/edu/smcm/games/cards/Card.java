package edu.smcm.games.cards;

/**
 * Representation of a card within a deck of English/American playing cards.
 */
/**
 * @author sread
 *
 */
/**
 * @author sread
 *
 */
/**
 * @author sread
 *
 */
public class Card {

	/**
	 * The suit of the card.
	 */
	private Suit suit;

	/**
	 * The face value of the playing card. Jokers have value 0.
	 */
	private int value;

	/**
	 * Whether the card is face uo (visible).
	 */
	private boolean face_up;

	/**
	 * The minimum face value of a card (excluding Jokers).
	 */
	public static final int minimum_face_value;

	/**
	 * The maximum face value of a card.
	 */
	public static final int maximum_face_value;

	/**
	 * The number of cards in each suit.
	 */
	public static final int cards_in_suit;

	// Set up the class' constant values
	static {
		minimum_face_value = 1;
		maximum_face_value = 13;
		cards_in_suit = 13;
	}

	/**
	 * Create a new Card.
	 * 
	 * @param suit    The suit of the Card.
	 * @param value   The value of the Card.
	 * @param face_up Whether the card is initially face up (visible).
	 */
	public Card(Suit suit, int value, boolean face_up) {
		this.suit = suit;
		this.value = value;
		this.face_up = face_up;
	}

	/**
	 * Create a Card that's face up (visible).
	 * 
	 * @param suit  The suit of the Card.
	 * @param value The value of the Card.
	 */
	public Card(Suit suit, int value) {
		this(suit, value, false);
	}

	/**
	 * Get the face value of the card.
	 * 
	 * NOTE: This will be zero for a Joker.
	 * 
	 * @return The face value of the Card.
	 */
	public int value() {
		return value;
	}

	/**
	 * Get the suit of the card.
	 * 
	 * @return The suit of the card.
	 */
	public Suit suit() {
		return suit;
	}

	/**
	 * Get whether the card is face up (visible).
	 * 
	 * @return True if the face of the card is visible.
	 */
	public boolean faceUp() {
		return face_up;
	}

	/**
	 * Turn the card so the face is visible.
	 */
	public void turnUp() {
		face_up = true;
	}

	/**
	 * Flip the card over.
	 * 
	 * Face up cards become face down and face down become face up.
	 */
	public void flip() {
		face_up = !face_up;
	}

	/**
	 * Create a three character String representation of the value of the card.
	 * 
	 * The representation is its face value (J for Jack, Q for Queen and K for King,
	 * A for Ace) followed by its suit (@see Suit).
	 * 
	 * NOTE: It has to be three digits because of the 10. The representation is
	 * right justified.
	 */
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
