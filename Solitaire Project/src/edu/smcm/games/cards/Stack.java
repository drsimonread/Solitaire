package edu.smcm.games.cards;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * This class cannot extend java.util.Stack because of the face up/face down
 * issue. We would need to make a special class of Card, which might be a good
 * idea.
 * 
 */
public class Stack {

	private List<Card> cards;

	/**
	 * Default constructor.
	 * 
	 * Creates an empty stacks of cards.
	 */
	public Stack() {
		this.cards = new ArrayList<Card>();
	}

	/**
	 * Copy constructor.
	 * 
	 * Creates a stack of cards that is a shallow copy of the Stack provided.
	 * 
	 * @param that the Stack to be copied.
	 */
	public Stack(Stack that) {
		this.cards = new ArrayList<Card>();

		this.cards.addAll(that.cards);
	}

	/**
	 * Constructor for fixed size Stack.
	 * 
	 * Use this constructor to create a Stack that is going to stay the same size
	 * (or smaller). This saves memory.
	 * 
	 * @param size
	 */
	public Stack(int size) {
		this.cards = new ArrayList<Card>(size);
	}

	public void push(Card card) {
		this.cards.add(card);
	}

	public void pop() {
		cards.remove(cards.size() - 1);
	}

	public Card peek() {
		return cards.get(size() - 1);
	}

	/**
	 * Show the card at a position within the stack.
	 * 
	 * The position is determined from the top of the stack. That is the card at the
	 * top of the stack after position pops.
	 * 
	 * @param position
	 * @return
	 */
	public Card peek(int position) {
		return cards.get(size() - position - 1);
	}

	/**
	 * Remove top cards.
	 * 
	 * Removes the top number cards from the Stack returning them as a Stack. Throws
	 * an exception if it tries to take more cards than there are or if some of the
	 * desired cards are not face up.
	 * 
	 * The original Stack is altered.
	 * 
	 * @param number number of cards to remove
	 * @return Stack of cards removed
	 */
	public Stack pop(int number) throws NotEnoughCardsException, CardsFaceDownException {
		Stack result;
		
		result = new Stack(number);
		
		if (cards.size() < number) {
			throw new NotEnoughCardsException(cards.size(), number);
		} else if (!peek(number - 1).faceUp()) {
			throw new CardsFaceDownException(number - 1);
		} else {
			
			/* Copy cards in reverse order into new Stack */
			// TODO I think this loop is upside down but it works
			for (int count = 0; count < number; count++) {
				result.cards.add(this.peek(count));
			}
			
			/* Remove copied cards */
			for (int count = 0; count < number; count++) {
				pop();
			}
			
			return result;
		}	
	}
	
	/**
	 * Add Stack of cards to the top of this Stack.
	 * 
	 * Adds the cards to the top of the stack in maintaining original order.  Leaves added Stack unchanged.
	 * 
	 * @param stack the stack to be added.
	 */
	public void push(Stack stack) {
		int position;
		
		for (int count = 0; count < stack.size(); count++) {
			position = stack.size() - count - 1;
			this.cards.add(stack.cards.get(position));
		}	
	}

	public void flipTopCard() {
		this.cards.get(size() - 1).turnUp();
	}

	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return cards.size();
	}
	
	public String toString() {
		String result;
		
		result = "";
		for (int count = 0; count < size(); count++) {
			result = result + cards.get(size() - count - 1) + "\n";
		}
		
		return result;
	}
}
