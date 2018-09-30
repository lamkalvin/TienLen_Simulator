import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
	// Data Fields
	private ArrayList<Card> deck = new ArrayList<Card>(52);
	
	// Constructors
	Deck() {
		for(int suite = 0; suite < 4; suite++) {
			for (int rank = 0; rank < 13; rank++) {
				deck.add(new Card(suite + 1, rank + 1, (suite * 13) + (rank + 1)));
			}
		}
	}
	
	public boolean isEmpty() {
		return deck.isEmpty();
	}

	// Methods
	public int size() {
		return deck.size();
	}
	
	public boolean reset() {
		boolean result = false;
		deck.clear();
		if (deck.isEmpty()) {
			for(int suite = 0; suite < 4; suite++) {
				for (int rank = 0; rank < 13; rank++) {
					deck.add(new Card(suite + 1, rank + 1, (suite * 13) + (rank + 1)));
				}
			}
		}
		
		if (deck.size() == 52) {
			result = true;
		}
		return result;
	}

	public void sortDeck() {
		if (!deck.isEmpty()) {
			Collections.sort(deck);
		}
	}

	public Card drawCard() {
		if (!deck.isEmpty()) {
			return deck.remove(deck.size() - 1);
		}
		return null;
	}
	
	public ArrayList<Card> drawMany(int num) {
		if (num < 0) {
			num = 5;
		}
		
		ArrayList<Card> hand = new ArrayList<Card>(num);
		
		for (int currNum = 0; currNum < num; currNum++) {
			if (!deck.isEmpty()) {
				hand.add(drawCard());
			}
			else {
				return hand;
			}
		}
		return hand;
	}

	public void placeTop(Card spCard) {
		deck.add(spCard);
	}
	
	public void insertCard(int index, Card spCard) {
		deck.add(index, spCard);
	}
	
	public Card removeAt(int i) {
		return deck.remove(i);
	}
	
	public void cutDeck() {
		if (deck.size() > 1) {
			int midSize = deck.size()/2;
			for (int index = 0; index < midSize; index++) {
				deck.add(deck.remove(0));
			}
		}
	}
	
	public void shuffle() {
		hinduShuffle(3);
		rippleShuffle(2);
		hinduShuffle(1);
		cutDeck();
	}

	public void rippleShuffle(int reps) {
		int size = deck.size();
		int midSize = deck.size()/2;
		ArrayList<Card> LHalf = new ArrayList<Card>(midSize);
		ArrayList<Card> RHalf = new ArrayList<Card>(size - midSize);
		Random randomizer = new Random();
		
		for (int num = 0; num < reps; num++) {
			
			for (int index = 0; index < size; index++) {
				if (index < midSize) {
					LHalf.add(deck.remove(0));
				}
				else {
					RHalf.add(deck.remove(0));
				}
			}
			
			while (deck.size() != size) {
				if ((randomizer.nextInt() % 2) == 0) {
					if (!LHalf.isEmpty()) {
						deck.add(LHalf.remove(0));
					}
					else {
						while (!RHalf.isEmpty()) {
							deck.add(RHalf.remove(0));
						}
						break;
					}
				}
				else {
					if (!RHalf.isEmpty()) {
						deck.add(RHalf.remove(0));
					}
					else {
						while (!LHalf.isEmpty()) {
							deck.add(LHalf.remove(0));
						}
						break;
					}
				}
			}
		}
	}
	
	public void hinduShuffle(int reps) {
		Random randomizer = new Random();
		int randNum;
		int lBound, uBound;
		int range;
		for (int num = 0; num < reps; num++) {
			lBound = 0;
			uBound = 0;
			range = 0;
			do {
				randNum = (randomizer.nextInt(4)) + 2;
				uBound = deck.size() - randNum;			
				
				for (int index = lBound; index < uBound; index++) {
					deck.add(deck.remove(lBound));
				}
				
				lBound += randNum;
				range = uBound - lBound;
				
			} while (range > 10);
		}
	}
	
	public void printSize() {
		System.out.println("Size of Deck is: " + deck.size());
	}
	
	public void printDeck() {
		printSize();
		
		for(int index = 0; index < deck.size(); index++) {
			deck.get(index).printCard();
		}
		
		System.out.println();
	}
	

}
