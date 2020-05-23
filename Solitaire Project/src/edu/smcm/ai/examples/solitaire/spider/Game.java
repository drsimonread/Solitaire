 package edu.smcm.ai.examples.solitaire.spider;

import edu.smcm.games.cards.Cards;
import edu.smcm.games.cards.CardsFaceDownException;
import edu.smcm.games.cards.Deck;
import edu.smcm.games.cards.NotEnoughCardsException;
import edu.smcm.games.cards.Stack;
import edu.smcm.games.cards.Suit;
import edu.smcm.util.Util;

import java.util.ArrayList;
import java.util.List;

import edu.smcm.ai.examples.solitaire.spider.heuristics.DealNewRow;
import edu.smcm.games.cards.Card;

public class Game {

	public static final int number_of_stacks;
	private static final int number_of_reserved_cards;

	/**
	 * The stacks of cards in the game.
	 */
	private Stack[] stacks;

	/**
	 * The reserved cards that are dealt in rows.
	 */
	private Stack reserved;

	/**
	 * Are empty stacks allowed at the dealing of a new row.
	 */
	private boolean relaxed;

	private int collected;

	private int moves;
	
	private boolean lost;

	static {
		number_of_stacks = 10;
		number_of_reserved_cards = 50;
	}

	private void initialiseStacks(int suits) {
		List<Card> cards;
		Deck deck;
		Card card;
		int index;

		cards = new ArrayList<Card>(104);

		// Select 104 cards from the right suits
		deck = new Deck();
		while (cards.size() < 104) {
			if (deck.isEmpty()) {
				deck = new Deck();
			}

			card = deck.deal();

			switch (suits) {
			case 4:
				cards.add(card);
				break;
			case 2:
				if (card.suit() == Suit.Hearts || card.suit() == Suit.Spades) {
					cards.add(card);
				}
				break;
			case 1:
				if (card.suit() == Suit.Spades) {
					cards.add(card);
				}
				break;
			}
		}

		// Shuffle cards
		Cards.shuffle(cards);

		// Deal 50 cards into reserved stack
		reserved = new Stack();
		for (int count = 0; count < number_of_reserved_cards; count++) {
			reserved.push(cards.get(cards.size() - 1));
			cards.remove(cards.size() - 1);
		}

		// Initialise the right number of empty stacks
		stacks = new Stack[number_of_stacks];
		for (int count = 0; count < number_of_stacks; count++) {
			stacks[count] = new Stack();
		}

		// Deal cards into main stacks
		index = 0;
		while (cards.size() > 0) {
			stacks[index % number_of_stacks].push(cards.get(cards.size() - 1));
			cards.remove(cards.size() - 1);
			index = index + 1;
		}

		// Turn the top card of each stack face up
		for (Stack stack : stacks) {
			stack.flipTopCard();
		}
	}

	public Game(int suits, boolean relaxed) {
		initialiseStacks(suits);
		this.relaxed = relaxed;
		this.collected = 0;
		this.moves = 0;
		this.lost = false;
	}

	/**
	 * Copy constructor for Game
	 * 
	 * @param that the game to be copied
	 */
	public Game(Game that) {
		// TODO Keep this method up-to-date with respect to implementation
		this.stacks = new Stack[that.stacks.length];
		for (int index = 0; index < that.stacks.length; index++) {
			this.stacks[index] = new Stack(that.stacks[index]);
		}
		this.reserved = new Stack(that.reserved);
		this.collected = that.collected();
		this.moves = that.moves;
		this.lost = that.lost;
	}

	public int cardsInStack(int stack) {
		assert (stack < stacks.length);

		return stacks[stack].size();
	}

	/**
	 * Get the Card at a particular position in the game.
	 * 
	 * Note: The card positions count from zero, just as in Position.
	 * 
	 * @param stack the stack to look in for the card, counting from 0.
	 * @param card  card position, counting uppermost card as 0.
	 * @return the Card at the given position.
	 */
	public Card cardAt(int stack, int card) {
		return stacks[stack].peek(card);
	}

	public int reservedSize() {
		return reserved.size();
	}

	public int collected() {
		return collected;
	}

	/**
	 * Check that the cards at the top of a Stack are in order and of the same suit.
	 * 
	 * @param stack    the Stack to check
	 * @param position the position of the last card to check
	 * @return the cards are in order
	 */
	private boolean moveableStack(Stack stack, int cards) {
		boolean result;

		result = stack.size() >= cards;
		for (int count = 0; count < cards - 1; count++) {
			result = result && stack.peek(count).value() + 1 == stack.peek(count + 1).value();
			result = result && stack.peek(count).suit() == stack.peek(count + 1).suit();
		}

		for (int count = 0; count < cards; count++) {
			result = result && stack.peek(count).faceUp();
		}

		return result;
	}

	/**
	 * Check that a move is legal.
	 * 
	 * A move is legal if it moves a set of cards that are of the same suit, in
	 * order and face up to either an empty stack or to a stack where the card is
	 * one higher than the top of the stack being moved.
	 * 
	 * The to Position's card instance variable should be zero. This is not checked.
	 * 
	 * @param from the Position Cards are being moved from
	 * @param to   the Position Cards are being moved to
	 * @return the move is legal
	 */
	private boolean isLegalMove(int from, int cards, int to) {
		boolean result;

		result = (stacks[to].isEmpty() || (moveableStack(stacks[from], cards)
				&& stacks[to].peek().value() == stacks[from].peek(cards - 1).value() + 1));

		return result;
	}

	private boolean canDealNewRow() {
		boolean result;

		if (relaxed) {
			result = true;
		} else {
			result = true;
			for (Stack stack : stacks) {
				result = result && !stack.isEmpty();
			}
		}
		return result;
	}

	private void checkAllStacksForSuits() {

		for (Stack stack : stacks) {
			if (!stack.isEmpty() && moveableStack(stack, Card.cards_in_suit)) {
				for (int count = 0; count < Card.cards_in_suit; count++) {
					stack.pop();
				}

				if (!stack.isEmpty() && !stack.peek().faceUp()) {
					stack.flipTopCard();
				}
				collected = collected + 1;
			}
		}
	}
	
	/**
	 * Move a stack of cards from one stack to another.
	 * 
	 * @param from the Position to move from
	 * @param to   the Position to move to
	 * @throws IllegalMoveException
	 * @throws CardsFaceDownException
	 * @throws NotEnoughCardsException
	 */
	public void move(int from, int cards, int to)
			throws IllegalMoveException, NotEnoughCardsException, CardsFaceDownException {
		Stack stack;

		if (!isLegalMove(from, cards, to)) {
			throw new IllegalMoveException(from, cards, to);
		} else {
			stack = stacks[from].pop(cards);
			stacks[to].push(stack);
			if (!stacks[from].isEmpty() && !stacks[from].peek().faceUp()) {
				stacks[from].flipTopCard();
			}
		}

		checkAllStacksForSuits();

		moves = moves + 1;
	}

	public void move(Move move) 
		throws IllegalMoveException, NotEnoughCardsException, CardsFaceDownException {
		if (move instanceof MoveStack) {
			move(((MoveStack) move).from(), ((MoveStack) move).cards(), ((MoveStack) move).to());
		} else {
			newRow();
		}
	}
	
	/**
	 * Deal a row of cards from the reserved cards to each stack.
	 * 
	 * Dealing a new row doesn't count as a move.
	 * 
	 * @throws IllegalMoveException
	 */
	public void newRow() throws IllegalMoveException {

		if (reserved.isEmpty()) {
			lost = true;
		} else if (!canDealNewRow()) {
			throw new IllegalMoveException(99, 1, 1);
		} else {
			for (Stack stack : stacks) {
				stack.push(reserved.peek());
				reserved.pop();
				stack.flipTopCard();
			}
		}

		checkAllStacksForSuits();
	}

	public boolean won() {
		boolean result;

		result = true;
		for (Stack stack : stacks) {
			result = result && stack.isEmpty();
		}

		return result;
	}
	
	public boolean lost() {
		return lost;
	}

	private Range moveableRange(int from) {
		assert (!stacks[from].isEmpty());

		Suit suit;
		int top;

		suit = stacks[from].peek().suit();
		top = 0;
		while (stacks[from].size() > top && stacks[from].peek(top).faceUp() && stacks[from].peek(top).suit() == suit
				&& stacks[from].peek(top).value() == stacks[from].peek().value() + top) {
			top = top + 1;
		}
		top = top - 1;

		return new Range(stacks[from].peek().value(), stacks[from].peek(top).value(), suit);
	}

	/**
	 * Find all possible moves.
	 * 
	 * Find all possible moves given the boards current state.
	 * 
	 * @return a List of all possible Moves.
	 */
	public List<Move> possibleMoves() {
		/*
		 * Strategy: Find the range of moveable cards at the top of each stack.
		 * Determine if any subgroup of these cards could be moved to any other stack.
		 * This involves determining if the card at the top of the destination stack is
		 * one greater than any of the cards in the range that can be moved.
		 */
		List<Move> result;
		Range range;

		result = new ArrayList<Move>();

		for (int from = 0; from < stacks.length; from++) {
			if (!stacks[from].isEmpty()) {
				range = moveableRange(from);
				for (int to = 0; to < stacks.length; to++) {
					if (to != from) {
						if (stacks[to].isEmpty()) {
							result.add(new MoveStack(from, range.size(), to));
						} else if (range.contains(stacks[to].peek().value() - 1)) {
							result.add(
									new MoveStack(from, stacks[to].peek().value() - stacks[from].peek().value(), to));
						}
					}
				}
			}
		}

		if (canDealNewRow()) {
			result.add(new DealNewRow());
		}

		return result;
	}

	private int countFaceUp() {
		int result;

		result = 0;
		for (Stack stack : stacks) {
			for (int count = 0; count < stack.size(); count++) {
				if (stack.peek(count).faceUp()) {
					result = result + 1;
				}
			}
		}

		return result;
	}

	private int columnsTurned() {
		int result;

		result = 0;
		for (Stack stack : stacks) {
			if (stack.isEmpty() || stack.peek(stack.size() - 1).faceUp()) {
				result = result + 1;
			}
		}

		return result;
	}

	private int inSequence() {
		int result;

		result = 0;
		for (Stack stack : stacks) {
			// Yes we do mean - 2!
			// TODO Do we mean - 2 or -1 (experiment?)
			// If it's -1 we need to consider empty stacks
			for (int count = 0; count < stack.size() - 2; count++) {
				if (stack.peek(count).faceUp() && stack.peek(count + 1).faceUp()
						&& stack.peek(count).value() + 1 == stack.peek(count + 1).value()
						&& stack.peek(count).suit() == stack.peek(count + 1).suit()) {
					result = result + 1;
				}
			}
		}

		return result;
	}

	/**
	 * Calculate the score for Sun Microsystems version.
	 * 
	 * See: https://en.wikipedia.org/wiki/Spider_(solitaire)#Scoring
	 * 
	 * This implementation doesn't keep suits in the tableau, so the maximum score
	 * is 990.
	 * 
	 * @return the Sun score
	 */
	public int scoreSun() {
		int face_up;

		face_up = countFaceUp() - 10;

		return (10 * face_up) + (15 * columnsTurned()) + (2 * inSequence()) + (50 * collected);
	}

	public int scoreTwo() {
		int face_up;

		face_up = countFaceUp() - 10;

		return (2 * face_up) + (15 * columnsTurned()) + (10 * inSequence()) + (200 * collected);
		
	}
	
	/**
	 * Calculate the score for Microsoft Windows version.
	 * 
	 * See: https://en.wikipedia.org/wiki/Spider_(solitaire)#Scoring
	 * 
	 * @return the Sun score
	 */
	public int scoreWindows() {
		return 500 - moves + (100 * collected);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String result;
		int maximum_size;

		result = "";

		maximum_size = 0;
		for (Stack stack : stacks) {
			maximum_size = Util.maxInt(maximum_size, stack.size());
		}

		for (int count = 0; count < stacks.length; count++) {
			result = result + "  " + count + " ";
		}
		result = result + "\n";

		for (int row = 0; row < maximum_size; row++) {
			for (Stack stack : stacks) {
				if (stack.size() - row - 1 < 0) {
					result = result + "    ";
				} else {
					result = result + stack.peek(stack.size() - row - 1) + " ";
				}
			}
			result = result + "\n";
		}

		result = result + "\nCollected: " + collected();
		result = result + " Reserved: " + reservedSize();
		result = result + "\n";

		result = result + "Sun score: " + scoreSun();
		result = result + " Windows score: " + scoreWindows();
		result = result + "\n";

		return result;
	}

	public void printGame() {
		int maximum_size;

		maximum_size = 0;
		for (Stack stack : stacks) {
			maximum_size = Util.maxInt(maximum_size, stack.size());
		}

		for (int count = 0; count < stacks.length; count++) {
			System.out.print("  " + count + " ");
		}
		System.out.println();

		for (int row = 0; row < maximum_size; row++) {
			for (Stack stack : stacks) {
				if (stack.size() - row - 1 < 0) {
					System.out.print("    ");
				} else {
					System.out.print(stack.peek(stack.size() - row - 1) + " ");
				}
			}
			System.out.println();

			System.out.println();
			System.out.println("Collected: " + collected() + " Reserved: " + reservedSize());
			System.out.println("Sun score: " + scoreSun() + " Windows score: " + scoreWindows());
		}
	}
}
