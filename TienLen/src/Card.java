
public class Card implements Comparable<Card> {
	// Data Fields
	private int suite;
	private int primaryRank;
	private int secondaryValue;
	private boolean sortByRank;
	
	// Constructors
	Card() {
		suite = 0;
		primaryRank = 0;
		secondaryValue = 0;
		sortByRank = false;
	}
	
	Card(int newSuite, int newPRank) {
		if (newSuite < 1 || newSuite > 4) {
			throw new IllegalArgumentException("Suite Error - Correct Options: 1 (Spades), 2 (Clubs), 3 (Diamonds), 4 (Hearts)");
		}
		
		if (newPRank < 1 || newPRank > 13) {
			throw new IllegalArgumentException("Rank Error - Correct Options: 1 (Ace), 2-10, 11 (Jack), 12 (Queen), 13 (King)");
		}
		
		suite = newSuite;
		primaryRank = newPRank;
		secondaryValue = 0;
		sortByRank = false;
	}
	
	Card(int newSuite, int newPRank, int newSVal) {
		if (newSuite < 1 || newSuite > 4) {
			throw new IllegalArgumentException("Suite Error - Correct Options: 1 (Spades), 2 (Clubs), 3 (Diamonds), 4 (Hearts)");
		}
		
		if (newPRank < 1 || newPRank > 13) {
			throw new IllegalArgumentException("Rank Error - Correct Options: 1 (Ace), 2-10, 11 (Jack), 12 (Queen), 13 (King)");
		}
		
		if (newSVal < 1) {
			throw new IllegalArgumentException("Secondary Value Error - Must be greater than 0");
		}
		
		suite = newSuite;
		primaryRank = newPRank;
		secondaryValue = newSVal;
		sortByRank = false;
	}
	
	// Methods
	public int getSuite() {
		return this.suite;
	}
	
	public int getRank() {
		return this.primaryRank;
	}
	
	public int getSVal() {
		return this.secondaryValue;
	}
	
	public int getCardIndex() {
		return ((this.suite-1) * 13) + this.getRank();
	}
	
	public void setSuite(int newSuite) {
		if (newSuite < 1 || newSuite > 4) {
			System.out.println("Suite Error - Correct Options: 1 (Spades), 2 (Clubs), 3 (Diamonds), 4 (Hearts)");
		}
		else {
			suite = newSuite;
		}
	}
	
	public void setRank(int newPRank) {
		if (newPRank < 1 || newPRank > 14) {
			throw new IllegalArgumentException("Rank Error - Correct Options: 1 or 14 (Ace), 2-10, 11 (Jack), 12 (Queen), 13 (King)");
		}
		else {
			primaryRank = newPRank;
		}
	}
	
	public void setSVal(int newSVal) {
		if (newSVal > 0) {
			secondaryValue = newSVal;
		}
	}
	
	public void setSort(boolean sortRank) {
		sortByRank = sortRank;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean equal = false;
		if (o == this) {
			equal = true;
		}
		else if (o instanceof Card) {
			Card inqObj = (Card)o;
			if (this.suite == inqObj.suite && this.primaryRank == inqObj.primaryRank) {
				equal = true;
			}
		}
		return equal;
	}
	
	@Override
	public int compareTo(Card compareCard) {
		
		int c1_val = this.getSVal();
		int c2_val = compareCard.getSVal();
		
		if (sortByRank) {
			c1_val = this.getRank();
			c2_val = compareCard.getRank();
			
			if ((c1_val == 1 && c2_val != 1) || (c1_val != 1 && c2_val == 1) 
					|| (c1_val == 2 && c2_val != 2) || (c1_val != 2 && c2_val == 2)) {
				c1_val = this.getSVal();
				c2_val = compareCard.getSVal();
			}
			else if (c1_val == c2_val) {
				c1_val = this.getSuite();
				c2_val = compareCard.getSuite();
			}
		}
		
		return c1_val - c2_val;
	}
	
	public void printCard() {
		switch(primaryRank) {
			case 1: System.out.print("Ace of ");
					break;
			case 2: System.out.print("2 of ");
					break;
			case 3: System.out.print("3 of ");
					break;
			case 4: System.out.print("4 of ");
					break;
			case 5: System.out.print("5 of ");
					break;
			case 6: System.out.print("6 of ");
					break;
			case 7: System.out.print("7 of ");
					break;
			case 8: System.out.print("8 of ");
					break;
			case 9: System.out.print("9 of ");
					break;
			case 10: System.out.print("10 of ");
					break;
			case 11: System.out.print("Jack of ");
					break;
			case 12: System.out.print("Queen of ");
					break;
			case 13: System.out.print("King of ");
					break;
			case 14: System.out.print("Ace of ");
					break;
			default: System.out.print("(No Rank) of ");
		}
		
		switch(suite) {
			case 1: System.out.print("Spades");
					break; 
			case 2: System.out.print("Clubs");
					break;
			case 3: System.out.print("Diamonds");
					break;
			case 4: System.out.print("Hearts");
					break;
			default: System.out.print("(No Suite)");
		}
		//System.out.print("(" + secondaryValue + ")\n");
	}
	
	@Override
	public String toString() {
		StringBuilder card = new StringBuilder();
		switch(primaryRank) {
			case 1: card.append("Ace of ");
					break;
			case 2: card.append("2 of ");
					break;
			case 3: card.append("3 of ");
					break;
			case 4: card.append("4 of ");
					break;
			case 5: card.append("5 of ");
					break;
			case 6: card.append("6 of ");
					break;
			case 7: card.append("7 of ");
					break;
			case 8: card.append("8 of ");
					break;
			case 9: card.append("9 of ");
					break;
			case 10: card.append("10 of ");
					break;
			case 11: card.append("Jack of ");
					break;
			case 12: card.append("Queen of ");
					break;
			case 13: card.append("King of ");
					break;
			case 14: card.append("Ace of ");
					break;
			default: card.append("(No Rank) of ");
		}
		
		switch(suite) {
			case 1: card.append("Spades");
					break; 
			case 2: card.append("Clubs");
					break;
			case 3: card.append("Diamonds");
					break;
			case 4: card.append("Hearts");
					break;
			default: card.append("(No Suite)");
		}
		//card.append("(" + secondaryValue + ")");
		return card.toString();
	}

}
