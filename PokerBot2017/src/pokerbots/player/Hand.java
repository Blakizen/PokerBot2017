package pokerbots.player;

import java.util.ArrayList;
import java.util.List;

public class Hand {

	private List<Card> holeCards = new ArrayList<>();
	private List<Card> boardCards = new ArrayList<>();
	
	public Hand(List<Card> holeCards, List<Card> boardCards) {
		this.holeCards = holeCards;
		this.boardCards = boardCards;
	}
	
	public Card discard() {
		return null;
	}
	
	private boolean isFlush() {
		for(Card card : holeCards) {
			if(!card.suit().equals(holeCards.get(0).suit())) {
				return false;
			}
		}
		return true;
	}
	
	private double holeValue() {
		Card first = holeCards.get(0);
		Card second = holeCards.get(1);
		
		double value = first.value() + second.value();
		if(first.value() == second.value()) {
			value *= 2;
		}
		
		if(first.suit().equals(second.suit())) {
			value += 2;
		}
		
		switch(Math.abs((first.rank() - second.rank()))) {
		case 0:
			break;
		case 1:
			value -= 1;
			break;
		case 2:
			value -= 2;
			break;
		case 3:
			value -= 4;
			break;
		default:
			value -= 5;
		}
		
		
		return value;
	}
	
	/**
	private boolean isStraight() {
		
	}
	
	private int highestMultiple() {
		
	}
	
	private int handValue() {
		
	}
	
	public boolean beats(Hand otherHand) {
		
	}
	**/
}
