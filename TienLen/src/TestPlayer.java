
public class TestPlayer {

	public static void main(String[] args) {
		Deck deck = new Deck();
		Player testPlayer = new Player(false);
		
		System.out.println("Before drawing hand:");
		testPlayer.displayHand();
		deck.printDeck();
		
		testPlayer.drawHand(deck, 13);
		
		System.out.println("After drawing hand:");
		testPlayer.displayHand();
		deck.printDeck();
		
		testPlayer.emptyHand();
		deck.reset();
		deck.shuffle();
		
		System.out.println("Before drawing hand:");
		testPlayer.displayHand();
		deck.printDeck();
		
		System.out.println("After drawing hand:");
		testPlayer.drawHand(deck, 13);
		testPlayer.displayHand();
		
		testPlayer.sortHand(false);
		
		System.out.println("After sorting hand:");
		testPlayer.displayHand();

	}

}
