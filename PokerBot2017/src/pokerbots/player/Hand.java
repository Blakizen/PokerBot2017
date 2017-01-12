package pokerbots.player;

import java.util.ArrayList;
import java.util.List;

public class Hand {

	private List<Card> cards = new ArrayList<>();
	
	public Hand(List<Card> cards) {
		this.cards = cards;
	}
	
	private boolean isFlush() {
		for(Card card : cards) {
			if(!card.suit().equals(cards.get(0).suit)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isStraight() {
		
	}
	
	private int highestMultiple() {
		
	}
	
	private int handValue() {
		
	}
	
	public boolean beats(Hand otherHand) {
		
	}
}
