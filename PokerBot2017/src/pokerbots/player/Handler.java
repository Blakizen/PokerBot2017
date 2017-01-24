package pokerbots.player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Handler {
	
	private final Tracker tracker;
	private int handID;
	private boolean button;
	private List<Card> holeCards;
	private String myName;
	private String oppName;
	private int stackSize;
	private int bigBlind;
	private int numHands;
	private double timeBank;
	
	public Handler() {
		tracker = new Tracker();
		handID = 0;
		button = true;
		holeCards = new ArrayList<Card>();
		myName = "";
		oppName = "";
		stackSize = 0;
		bigBlind = 0;
		numHands = 0;
		timeBank = 0;
	}
	
	public String handleGetAction(int potSize, List<Card> boardCards, List<String> lastActions, List<String> legalActions, double timeBank) {
		
		Hand thisHand = new Hand(holeCards, boardCards);
		
		switch(boardCards.size()) {
		case 0:
			return "CALL";
		case 3:
			if(legalActions.contains("DISCARD:" + holeCards.get(1))) {
				if(holeCards.get(0).value() == holeCards.get(1).value()) {
					return "CHECK";
				}
				
				else if(holeCards.get(0).value() > 10) {
					if(holeCards.get(1).value() > 10) {
						return "CHECK";
					}
					else {
						return "DISCARD:" + holeCards.get(1);
					}
				}
				else if(holeCards.get(1).value() > 10) {
					return "DISCARD:" + holeCards.get(0);
				}
				else {
					return "DISCARD:" + holeCards.get(0);
				}
			}
			
			else {
				return "CHECK";
			}
		case 4:
			return "CHECK";
		case 5:
			return "CHECK";
		default:
			return "";
		}
	}
	
	public void handleNewGame(String myName, String oppName, int stackSize, int bigBlind, int numHands, double timeBank) {
		this.myName = myName;
		this.oppName = oppName;
		this.stackSize = stackSize;
		this.bigBlind = bigBlind;
		this.numHands = numHands;
		this.timeBank = timeBank;
	}
	
	public void handleNewHand(int handID, boolean button, List<Card> holeCards, double timeBank) {
		this.handID = handID;
		this.button = button;
		this.holeCards = holeCards;
		this.timeBank = timeBank;
	}
}
