import java.util.ArrayList;

public class TestDeck {

	public static void main(String[] args) {
		
		// Test 1 - Create Deck
		Deck testDeck = new Deck();
		testDeck.printDeck();
		
		// Test 2 - Draw Card (And check stack vs array)
		Card drawnCard = testDeck.drawCard();
		System.out.println(drawnCard);
		//testDeck.printDeck();
		
		// Test 3 - Put Card Back on Top
		testDeck.placeTop(drawnCard);
		//testDeck.printDeck();
		
		// Test 3 - Draw Hand
		ArrayList<Card> hand = testDeck.drawMany(13);
		//testDeck.printDeck();
		/*System.out.println("Hand:");
		for (int i = 0; i < hand.size(); i++) {
			System.out.println(hand.get(i));
		}*/
		for (int i = 0; i < 13; i++) {
			testDeck.placeTop(hand.remove(12 - i));
		}
		//testDeck.printDeck();
		
		// Test 4 - Cut Deck
		testDeck.cutDeck();
		//testDeck.printDeck();
		testDeck.cutDeck();
		//testDeck.printDeck();
		
		// Test 5 - Ripple Shuffle
		testDeck.rippleShuffle(10);
		testDeck.printDeck();
		
		// Test 6 - Reset
		testDeck.reset();
		//testDeck.printDeck();
		
		// Test 7 - Hindu Shuffle
		testDeck.hinduShuffle(100);
		//testDeck.printDeck();
		
		// Test 8 - Shuffle
		testDeck.reset();
		testDeck.shuffle();
		//testDeck.printDeck();

	}

}
