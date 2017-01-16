package pokerbots.player;

public class Card {

	private final String name;
	private final String suit;
	private final String rank;
	
	public Card(String name) {
		this.name = name;
		this.rank = name.substring(0, 1);
		this.suit = name.substring(1, 2);
	}
	
	public String getName() {
		return name;
	}
	
	public String suit() {
		return this.suit;
	}
	
	public String rank() {
		return this.name;
	}
}
