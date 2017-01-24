package pokerbots.player;

/**
 * Tracks opponent bot statistics.
 *
 */
public class Tracker {

	private int cBets;
	private int foldsToCBet;
	private int cBetOpportunities;
	
	public Tracker() {
		this.cBets = 0;
		this.cBetOpportunities = 0;
	}
	
	public void updateCBets(boolean cBetted) {
		cBetOpportunities++;
		if(cBetted) cBets++;
	}
}
