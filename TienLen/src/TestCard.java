
public class TestCard {

	public static void main(String[] args) {
		// Test 1 - Default Card/Print Card
		Card c1 = new Card();
		c1.printCard();
		
		// Test 2 - Valid Card
		Card c2 = new Card(1,1);
		c2.printCard();
		
		// Test 3 - Invalid Suite
		//Card c3 = new Card(5,1);
		
		// Test 4 - Invalid Rank;
		//Card c4 = new Card(1,-1);
		
		// Test 5 - getSuite
		System.out.println("c2 suite is: " + c2.getSuite());
		
		// Test 6 - getRank
		System.out.println("c2 rank is: " + c2.getRank());
		
		// Test 7 - getSRank
		System.out.println("c2 secondary rank is: " + c2.getSVal());
		
		// Test 8 - setSuite
		c2.setSuite(4);
		c2.printCard();
		
		// Test 9 - setRank
		c2.setRank(13);
		c2.printCard();
		
		// Test 10 - setSRank
		c2.setSVal(14);
		System.out.println("c2 secondary rank is: " + c2.getSVal());
		
		// Test 11 - overridden method
		System.out.println(c2);
	}

}
