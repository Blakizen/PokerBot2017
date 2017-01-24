package pokerbots.player;

/**
 * Immutable card class for poker. Represents a single playing card with a rank and a suit.
 *
 */
public class Card {

	private final String name;
	private final String suit;
	private final char rank;
	
	public Card(String name) {
		this.name = name;
		this.rank = name.toCharArray()[0];
		this.suit = Character.toString(name.toCharArray()[1]);
	}
	
	public String getName() {
		return name;
	}
	
	public String suit() {
		return this.suit;
	}
	
	public int rank() {
		switch(rank) {
		case 'A':
			return 14;
		case 'K':
			return 13;
		case 'Q':
			return 12;
		case 'J':
			return 11;
		case 'T':
			return 10;
		default:
			return Integer.parseInt(Character.toString(rank));
		}
	}
	
	public double value() {
		switch(rank) {
		case 'A':
			return 10;
		case 'K':
			return 8;
		case 'Q':
			return 7;
		case 'J':
			return 6;
		case 'T':
			return 5;
		default:
			return Double.parseDouble(Character.toString(rank)) / 2;
		}
	}
	
	@Override
	public boolean equals(Object thatObject) {
		if(!(thatObject instanceof Card)) return false;
		return this.value() == ((Card) thatObject).value();
	}
}
