import java.io.IOException;
import java.util.Scanner;

public class GameManager {
	// Data Fields
	Deck deck;
	PlayedPile pile;
	Player p1;
	Player p2;
	Player p3;
	Player p4;
	
	// Constructors
	public GameManager() {
		System.out.println("Generating New Game...");
		
		deck = new Deck();
		prepareDeck();
		deck.sortDeck();
		//deck.printDeck();
		
		pile = new PlayedPile();
		p1 = new Player(false);
		p2 = new Player(false);
		p3 = new Player(false);
		p4 = new Player(false);
	}
	
	// Methods
	public void play(Scanner userInput) throws IOException {
		System.out.println("Game Started!\n");
		deck.shuffle();
		deal();
		
//		p1.displayHand();
//		p2.displayHand();
//		p3.displayHand();
//		p4.displayHand();
		
		int whoseTurn = pile.findStarter(p1, p2, p3, p4);
		int turnCounter = 1;
		
//		int iter = 0;
		
		do {
			pile.newRound(whoseTurn, p1, p2, p3, p4);

			if (whoseTurn == 1) {
				System.out.println("*** TURN " + turnCounter + " - PLAYER 1's TURN **************************************************");
				if (!p1.isPassing()) {
					pile.playerTurn(p1, 1, p1.checkAI(), turnCounter, userInput);
				}
				else {
					System.out.println("~~ Player 1 has already passed for this current round ~~");
				}
			}
			else if (whoseTurn == 2) {
				System.out.println("*** TURN " + turnCounter + " - PLAYER 2's TURN **************************************************");
				if (!p2.isPassing()) {
					pile.playerTurn(p2, 2, p2.checkAI(), turnCounter, userInput);
				}
				else {
					System.out.println("~~ Player 2 has already passed for this current round ~~");
				}
			}
			else if (whoseTurn == 3) {
				System.out.println("*** TURN " + turnCounter + " - PLAYER 3's TURN **************************************************");
				if (!p3.isPassing()) {
					pile.playerTurn(p3, 3, p3.checkAI(), turnCounter, userInput);
				}
				else {
					System.out.println("~~ Player 3 has already passed for this current round ~~");
				}
			}
			else {
				System.out.println("*** TURN " + turnCounter + " - PLAYER 4's TURN **************************************************");
				if (!p4.isPassing()) {
					pile.playerTurn(p4, 4, p4.checkAI(), turnCounter, userInput);
				}
				else {
					System.out.println("~~ Player 4 has already passed for this current round ~~");
				}
			}
			
			whoseTurn = (whoseTurn + 1) % 5;
			if (whoseTurn == 0) {
				whoseTurn = 1;
			}
			
//			iter++;
			turnCounter++;
			System.out.println("*** END OF TURN ***************************************************************\n");
			for (int i = 0; i < 100; i++) {
				System.out.println();
			}
		} while (/*iter != 10*/ !pile.isGameOver(p1, p2, p3, p4));
		
	}
	
	private void deal() {
		while (!deck.isEmpty()) {
			p1.drawCard(deck);
			p2.drawCard(deck);
			p3.drawCard(deck);
			p4.drawCard(deck);
		}
		
		p1.sortHand(true);
		p2.sortHand(true);
		p3.sortHand(true);
		p4.sortHand(true);
	}
	
	private void prepareDeck() {
		Card temp;
		int size = deck.size();
		int shiftSize = 1;
		int currVal = 0;
		int aceIndex = 0;
		int twoIndex = 1;
		int aceVal = 12;
		int twoVal = 49;
		
		for (int i = 0; i < size; i++) {
			temp = deck.removeAt(0);
			currVal = temp.getSVal();
			
			if (i == aceIndex) {
				temp.setSVal(aceVal);
				temp.setRank(14);
				aceIndex += 13;
				aceVal += 12;
				shiftSize += 1;
			}
			else if (i == twoIndex) {
				temp.setSVal(twoVal);
				twoIndex += 13;
				twoVal += 1;
			}
			else {
				temp.setSVal(currVal - shiftSize);
				currVal = temp.getSVal();
			}
			
			deck.placeTop(temp);
		}
	}

}
