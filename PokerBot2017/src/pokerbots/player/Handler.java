package pokerbots.player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Handler {
	
	private final Tracker tracker;
	private int handID;
	private boolean button;
	private Set<Card> holeCards;
	String myName;
	String oppName;
	int stackSize;
	int bigBlind;
	int numHands;
	double timeBank;
	
	public Handler() {
		tracker = new Tracker();
		handID = 0;
		button = true;
		holeCards = new HashSet<Card>();
	}
	
	public String handleGetAction(int potSize, List<Card> boardCards, List<String> lastActions, List<String> legalActions, double timeBank) {
		switch(boardCards.size()) {
		case 0:
			return "";
		case 3:
			return "";
		case 4:
			return "";
		case 5:
			return "";
		default:
			return "";
		}
	}
	
	public void handleNewGame(String myName, String oppName, int stackSize, int bigBlind, int numHands, double timeBank) {
		
	}
	
	public void handleNewHand(int handID, boolean button, Set<Card> holeCards, double timeBank) {
		
	}
}
