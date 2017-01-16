package pokerbots.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pokerbots.player.Card;

/**
 * Simple example pokerbot, written in Java.
 * 
 * This is an example of a bare bones, pokerbot. It only sets up the socket
 * necessary to connect with the engine and then always returns the same action.
 * It is meant as an example of how a pokerbot should communicate with the
 * engine.
 * 
 */
public class Player {
	
	private final PrintWriter outStream;
	private final BufferedReader inStream;

	public Player(PrintWriter output, BufferedReader input) {
		this.outStream = output;
		this.inStream = input;
	}
	
	public void run() {
		String input;
		Handler handler = new Handler();
		try {
			// Block until engine sends us a packet; read it into input.
			while ((input = inStream.readLine()) != null) {

				// Here is where you should implement code to parse the packets
				// from the engine and act on it.
				System.out.println(input);
				
				String[] words = input.split(" ");
				String word = words[0];
				if ("GETACTION".compareToIgnoreCase(word) == 0) {
					// When appropriate, reply to the engine with a legal
					// action.
					// The engine will ignore all spurious packets you send.
					// The engine will also check/fold for you if you return an
					// illegal action.
					
					// Parse inputs
					int potSize = Integer.parseInt(words[1]);
					int numBoardCards = Integer.parseInt(words[2]);
					int numLastActions = Integer.parseInt(words[numBoardCards + 3]);
					int numLegalActions = Integer.parseInt(words[numBoardCards + numLastActions + 4]);
					double timeBank = Double.parseDouble(words[numBoardCards + numLastActions + numLegalActions + 5]);
					
					List<Card> boardCards = new ArrayList<>();
					List<String> lastActions = new ArrayList<>();
					List<String> legalActions = new ArrayList<>();
					
					for(int i = 0; i < Integer.parseInt(words[2]); i++) {
						boardCards.add(new Card(words[i + 4]));
					}
					
					for(int i = 0; i < numLastActions; i++) {
						lastActions.add(words[i + numBoardCards + 5]);
					}
					
					for(int i = 0; i < numLegalActions; i++) {
						legalActions.add(words[i + numBoardCards + numLastActions + 6]);
					}
					
					// Send inputs to handler and get returned action
					String finalAction = handler.handleGetAction(potSize, boardCards, lastActions, legalActions, timeBank);
					outStream.println(finalAction);
				}
				else if ("REQUESTKEYVALUES".compareToIgnoreCase(word) == 0) {
					// At the end, engine will allow bot to send key/value pairs to store.
					// FINISH indicates no more to store.
					int bytesLeft = Integer.parseInt(words[1]);
					outStream.println("FINISH");
				}
				else if("KEYVALUE".compareToIgnoreCase(word) == 0) {
					String key = words[1];
					String value = words[2];
				}
				else if("HANDOVER".compareToIgnoreCase(word) == 0) {
					int myBankRoll = Integer.parseInt(words[1]);
					int oppBankRoll = Integer.parseInt(words[2]);
					int numBoardCards = Integer.parseInt(words[3]);
					
					List<Card> boardCards = new ArrayList<>();
					int numLastActions = Integer.parseInt(words[4 + numBoardCards]);
					List<String> lastActions = new ArrayList<>();
					double timeBank = Double.parseDouble(words[5 + numBoardCards + numLastActions]);
					
					for(int i = 0; i < Integer.parseInt(words[2]); i++) {
						boardCards.add(new Card(words[i + 4]));
					}
					
					for(int i = 0; i < numLastActions; i++) {
						lastActions.add(words[i + numBoardCards + 5]);
					}
				}
				else if("NEWHAND".compareToIgnoreCase(word) == 0) {
					int handID = Integer.parseInt(words[1]);
					boolean button = Boolean.parseBoolean(words[2]);
					Set<Card> holeCards = new HashSet<>();
					for(int i = 3; i < 5; i++) {
						holeCards.add(new Card(words[i]));
					}
					int myBank = Integer.parseInt(words[5]);
					int oppBank = Integer.parseInt(words[6]);
					double timeBank = Double.parseDouble(words[7]);
					
					handler.handleNewHand(handID, button, holeCards, timeBank);
				}
				else if("NEWGAME".compareToIgnoreCase(word) == 0) {
					String myName = words[1];
					String oppName = words[2];
					int stackSize = Integer.parseInt(words[3]);
					int bigBlind = Integer.parseInt(words[4]);
					int numHands = Integer.parseInt(words[5]);
					double timeBank = Double.parseDouble(words[6]);
					
					handler.handleNewGame(myName, oppName, stackSize, bigBlind, numHands, timeBank);
				}
				//TODO: NEWGAME yourName oppName stackSize bb numHands timeBank
				//		KEYVALUE key value
				//		REQUESTKEYVALUES bytesLeft
				//		NEWHAND handId button holeCard1 holeCard2 holeCard3 holeCard4 myBank otherBank timeBank
				//		GETACTION potSize numBoardCards [boardCards] numLastActions [lastActions] numLegalActions [legalActions] timebank
				//		HANDOVER myBankRoll opponentBankRoll numBoardCards [boardCards] numLastActions [lastActions] timeBank

			}
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}

		System.out.println("Gameover, engine disconnected");
		
		// Once the server disconnects from us, close our streams and sockets.
		try {
			outStream.close();
			inStream.close();
		} catch (IOException e) {
			System.out.println("Encountered problem shutting down connections");
			e.printStackTrace();
		}
	}
	
}
