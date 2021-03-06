import java.util.ArrayList;
import java.util.Collections;

public class Player {
	// Data Fields
	private ArrayList<Card> hand = new ArrayList<Card>();
	private boolean passTurn;
	private boolean isAI;
	
	// Constructor
	Player(boolean AI) {
		passTurn = false;
		isAI = AI;
	}
	
	// Methods
	public boolean isHandEmpty() {
		return hand.isEmpty();
	}
	
	public boolean isPassing() {
		return passTurn;
	}
	
	public boolean checkAI() {
		return isAI;
	}

	public void setPassing(boolean isPassing) {
		passTurn = isPassing;
	}
	
	public void drawCard(Deck deck) {
		hand.add(deck.drawCard());
	}
	
	public boolean drawHand(Deck deck, int num) {
		hand = deck.drawMany(num);
		if (hand.size() == num) {
			return true;
		}
		return false;
	}
	
	public void sortHand(boolean byRank) {
		if (byRank) {
			for (Card card : hand) {
				card.setSort(true);
			}
		}
		if (!hand.isEmpty()) {
			Collections.sort(hand);
		}
	}
	
	public Card checkAt(int i) {
		Card cardAt = null;
		if (i >= 0 && i <= hand.size()) {
			cardAt = hand.get(i);
		}
		return cardAt;
	}

	public Card getCardAt(int i) {
		return hand.get(i);
	}
	
	public void removeCardAt(int i) {
		hand.remove(i);
	}
	
	public void emptyHand() {
		hand.clear();
	}
	
	public int handSize() {
		return hand.size();
	}
	
	public void displayHand() {
		for (int i = 0; i < hand.size(); i++) {
			System.out.print("[" + (i + 1) + "] " + hand.get(i) + ";");
		}
		System.out.println();
	}

}
