import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class PlayedPile {
	public final Card lowestCard = new Card(1,3);
	// Data Fields
	private ArrayList<Card> currSet;
	private boolean playAny;
	private boolean isSingle;
	private boolean isDouble;
	private boolean isTriple;
	private boolean isStraight;
	private boolean isBomb;
	private int straightSize;
	boolean validSSize = false;
	private int typeSel;
	private ArrayList<Card> cardSel;
	
	// Constructors
	PlayedPile() {
		currSet = new ArrayList<Card>();
		playAny = true;
		isSingle = false;
		isDouble = false;
		isTriple = false;
		isStraight = false;
		isBomb = false;
		straightSize = 3;
		typeSel = 0;
		cardSel = new ArrayList<Card>();
	}
	
	public void playerTurn(Player currP, int playerNum, boolean AI, int turnNum, Scanner userInput) {
		int numOfCards = 0;
		int userCommand = 0;
		int userTypeSel = 0;
		boolean endTurn = false;
		cardSel.clear();
		
		if (AI) {
			aiTurn(currP, playerNum);
		}
		else {
			System.out.println("Current Play:");
			printCurrPlay();
			
			System.out.println("Your Hand:");
			currP.displayHand();
			
			while (!endTurn) {
			
				System.out.println("What would you like to do:");
				System.out.println("[1] Play [2] Pass");
				userCommand = userInput.nextInt();
				userInput.nextLine();
				
				if (userCommand == 1) {
					ArrayList<Integer> cIndices = new ArrayList<Integer>();
					boolean goBack = false;
					
					do {
						userCommand = 0;
						System.out.println("Select the number of the type of set you would like to play:");
						System.out.println("[1] Single [2] Double [3] Triple [4] Straight [5] Bomb");
						userCommand = userInput.nextInt();
						userInput.nextLine();
						userTypeSel = userCommand;
						
						if (checkTypeSel(userCommand)) {
							
							if (typeSel == 1) {
								numOfCards = 1;
							}
							else if (typeSel == 2) {
								numOfCards = 2;
							}
							else if (typeSel == 3) {
								numOfCards = 3;
							}
							else if (typeSel == 4) {
								validSSize = false;
								System.out.println("Select the size of the Straight you would like to play:");
								numOfCards = userInput.nextInt();
								userInput.nextLine();
								if (playAny) {
									if (numOfCards > 2 && numOfCards < currP.handSize()) {
										straightSize = numOfCards;
										validSSize = true;
									}
									else {
										System.out.println("**Invalid Size - Select a size between 3 and " + currP.handSize() + "**");
										validSSize = false;
									}
								}
								else if (!playAny && numOfCards > straightSize) {
									System.out.print("**Invalid Size - Current Straight Size is " + straightSize + "**");
									validSSize = false;
								}
								else {
									validSSize = true;
								}
							}
							else if (typeSel == 5) {
								numOfCards = 6;
							}
							
							if (userTypeSel == 4 && !validSSize) {
								continue;
							}
							System.out.println("Select the number(s) of the card(s) you would like to play (ie. 1 2 3):");
							currP.displayHand();
							System.out.println("[14] Go Back to Type Selection");
							cardSel.clear();
							cIndices.clear();
							
							while (userInput.hasNextInt()) {
			
								userCommand = userInput.nextInt();
								
								if (userCommand == 14) {
									goBack = true;
									break;
								}
								
								cIndices.add(userCommand - 1);
								cardSel.add(currP.getCardAt(userCommand - 1));
								
								if (cardSel.size() >= numOfCards ) {
									break;
								}
							}
							userInput.nextLine();
						}
		
					} while (!isValid(userTypeSel, cardSel, turnNum, goBack) );
					
					playSelection(playerNum);
					removeFromHand(currP, cIndices);
					endTurn = true;
				}
				else if (userCommand == 2) {
					if (playAny) {
						System.out.println("You cannot pass when you are able to play anything!");
					}
					else {
						System.out.println("~~ Player " + playerNum + " is passing ~~");
						currP.setPassing(true);
						endTurn = true;
					}
				}
				else {
					System.out.println("**Invalid Selection - Try Again**");
				}
			}
		}
	}
	
	// Methods
	public int findStarter(Player p1, Player p2, Player p3, Player p4) {
		int starter = 1;

		if (p2.checkAt(0).equals(lowestCard)) {
			starter = 2;
		}
		else if (p3.checkAt(0).equals(lowestCard)) {
			starter = 3;
		}
		else if (p4.checkAt(0).equals(lowestCard)) {
			starter = 4;
		}
		
		return starter;
	}

	public boolean isGameOver(Player p1, Player p2, Player p3, Player p4) {
		boolean gameOver = false;
		if (p1.isHandEmpty()) {
			System.out.println("PLAYER 1 WINS! GAME OVER!\n");
			gameOver = true;
		}
		else if (p2.isHandEmpty()) {
			System.out.println("PLAYER 2 WINS! GAME OVER!\n");
			gameOver = true;
		}
		else if (p3.isHandEmpty()) {
			System.out.println("PLAYER 3 WINS! GAME OVER!\n");
			gameOver = true;
		}
		else if (p4.isHandEmpty()) {
			System.out.println("PLAYER 4 WINS! GAME OVER!\n");
			gameOver = true;
		}
		return gameOver;
	}
	
	public void newRound(int currP, Player p1, Player p2, Player p3, Player p4) {
		if (checkPasses(currP, p1, p2, p3, p4)) {
			currSet.clear();
			playAny = true;
		}
	}

	public void printCurrPlay() {
		if (currSet.isEmpty()) {
			printCurrRound();
			System.out.println();
		}
		else {
			for (Card card : currSet) {
				System.out.print("|" + card + "|");
			}
			System.out.println("\n");
		}
	}

	private void aiTurn(Player currAI, int playerNum) {
		System.out.println("~~ Player " + playerNum + " is passing ~~");
		currAI.setPassing(true);
	}
	
	private boolean checkTypeSel(int userCommand) {
		boolean validSel = false;
		switch (userCommand) {
			case 1: if (isSingle || playAny) {
						typeSel = userCommand; 
						validSel = true; 
					}
					else {
						System.out.println("**Invalid Selection**");
						printCurrRound();
					}
					break;
			case 2: if (isDouble || playAny) {
						typeSel = userCommand;
						validSel = true; 
					}
					else {
						System.out.println("**Invalid Selection**");
						printCurrRound();
					}
					break;
			case 3: if (isTriple || playAny) {
						typeSel = userCommand;
						validSel = true;
					}
					else {
							System.out.println("**Invalid Selection**");
							printCurrRound();
					}
					break;
			case 4: if (isStraight || playAny) {
						typeSel = userCommand;
						validSel = true;
					}
					else {
							System.out.println("**Invalid Selection**");
							printCurrRound();
					}
					break;
			case 5: if (isSingle || isBomb || playAny) { 
						typeSel = userCommand;
						validSel = true;
					}
					else {
						System.out.println("**Invalid Selection**");
						printCurrRound();
					}
					break;
			default: System.out.println("**Invalid Selection**");
					 printCurrRound();
		}
		return validSel;
	}

	private boolean isValid(int userTypeSel, ArrayList<Card> toPlay, int turnNum, boolean goBack) {
		boolean validPlay = false;
		
		if (userTypeSel == 1 && toPlay.size() == 1 && (playAny || isSingle) ) {
			validPlay = checkSingle(toPlay, turnNum);			
			if (validPlay) {
				setTypeFlags(true, false, false, false, false);
				if (playAny) {
					playAny = false;
				}
			}
		}
		else if (userTypeSel == 2 && toPlay.size() == 2 && (playAny || isDouble) ) {
			validPlay = checkDouble(toPlay, turnNum);
			if (validPlay) {
				setTypeFlags(false, true, false, false, false);
				if (playAny) {
					playAny = false;
				}
			}
		}
		else if (userTypeSel == 3 && toPlay.size() == 3 && (playAny || isTriple) ) {
			validPlay = checkTriple(toPlay, turnNum);
			if (validPlay) {
				setTypeFlags(false, false, true, false, false);
				if (playAny) {
					playAny = false;
				}
			}
		}
		else if (userTypeSel == 4 && toPlay.size() == straightSize && (playAny || isStraight) ) {
			validPlay = checkStraight(toPlay, turnNum);
			if (validPlay) {
				setTypeFlags(false, false, false, true, false);
				if (playAny) {
					playAny = false;
				}
			}
		}
		else if (userTypeSel == 5 && toPlay.size() == 6 && (playAny || isSingle || isBomb) ) {
			validPlay = checkBomb(toPlay, turnNum);
			if (validPlay) {
				setTypeFlags(false, false, false, false, true);
				if (playAny) {
					playAny = false;
				}
			}
		}
	
		if (!validPlay) {
			if (!goBack) {
				System.out.println("**Invalid Play - Please Select Again**");
			}
		}
		
		return validPlay;
	}
	
	private boolean checkSingle(ArrayList<Card> toPlay, int turnNum) {
		System.out.println("CHECKING SINGLE . . .");
		boolean valid = false;

		if (currSet.size() != 1 && !playAny) {
			System.out.println("The current played set of cards is not a Single");
		}
		else {
			Card selectedCard = toPlay.get(0);		
			
			if (currSet.isEmpty() && playAny) {
				if (turnNum == 1) {
					if (selectedCard.equals(lowestCard)) {
						valid = true;
					}
					else {
						System.out.println("The first turn must include the 3 of Spades");
					}
				}
				else {
					valid = true;
				}
			}
			else {
				Card currentCard = currSet.get(0);
				if (selectedCard.getRank() == 1 || selectedCard.getRank() == 2) {
					if (selectedCard.getSVal() > currentCard.getSVal()) {
						valid = true;
					}
				}
				else if (selectedCard.getRank() > currentCard.getRank()) {
					valid = true;
				}
				else if (selectedCard.getRank() == currentCard.getRank()) {
					if (selectedCard.getSuite() > currentCard.getSuite()) {
						valid = true;
					}
				}
			}
		}
		return valid;
	}
	
	private boolean checkDouble(ArrayList<Card> toPlay, int turnNum) {
		System.out.println("CHECKING DOUBLE . . .");
		boolean valid = false;

		if (currSet.size() != 2 && !playAny) {
			System.out.println("The current played set of cards is not a Double");
		}
		else {
			Collections.sort(toPlay);
			Card tpFirstC = toPlay.get(0);
			Card tpSecondC = toPlay.get(1);
			if (tpFirstC.getRank() == 2 || tpSecondC.getRank() == 2) {
				System.out.println("Cannot use 2 in a Double");
			}
			else if (tpFirstC.getRank() == tpSecondC.getRank()) {
				
				if (currSet.isEmpty() && playAny) {
					if (turnNum == 1) {
						if (tpFirstC.equals(lowestCard)) {
							valid = true;
						}
						else {
							System.out.println("The first turn must include the 3 of Spades");
						}
					}
					else {
						valid = true;
					}
				}
				else {
					Card highestCurrC = currSet.get(1);
					if (tpSecondC.getRank() > highestCurrC.getRank()) {
						valid = true;
					}
					else if (tpSecondC.getRank() == highestCurrC.getRank()) {
						if (tpSecondC.getSuite() > highestCurrC.getSuite()) {
							valid = true;
						}
					}
				}
				
			}
		}
		return valid;
	}
	
	private boolean checkTriple(ArrayList<Card> toPlay, int turnNum) {
		System.out.println("CHECKING TRIPLE . . .");
		boolean valid = false;
		
		if (currSet.size() != 3 && !playAny) {
			System.out.println("The current played set of cards is not a Triple");
		}
		else {
			Collections.sort(toPlay);
			Card tpFirstC = toPlay.get(0);
			Card tpSecondC = toPlay.get(1);
			Card tpThirdC = toPlay.get(2);
			if (tpFirstC.getRank() == 2 || tpSecondC.getRank() == 2 || tpThirdC.getRank() == 2) {
				System.out.println("You cannot use 2 in a Triple");
			}
			else if (tpFirstC.getRank() == tpSecondC.getRank() && tpSecondC.getRank() == tpThirdC.getRank()) {
				
				if (currSet.isEmpty() && playAny) {
					if (turnNum == 1) {
						if (tpFirstC.equals(lowestCard)) {
							valid = true;
						}
						else {
							System.out.println("The first turn must include the 3 of Spades");
						}
					}
					else {
						valid = true;
					}
				}
				else {
					Card highestCurrC = currSet.get(2);
					if (tpThirdC.getRank() > highestCurrC.getRank()) {
						valid = true;
					}
					else if (tpThirdC.getRank() == highestCurrC.getRank()) {
						if (tpThirdC.getSuite() > highestCurrC.getSuite()) {
							valid = true;
						}
					}
				}
				
			}
		}
		return valid;
	}
	
	private boolean checkStraight(ArrayList<Card> toPlay, int turnNum) {
		System.out.println("CHECKING STRAIGHT . . .");
		boolean valid = false;
		boolean isConsec = false;
		
		if (currSet.size() != straightSize && !playAny) {
			System.out.println("The current played set of cards does not have the same straight size as your selection");
		}
		else {
			Collections.sort(toPlay);
			for (int i = 0; i < toPlay.size() - 1; i++) {
				if (toPlay.get(i).getRank() == 2 || toPlay.get(i+1).getRank() == 2) {
					System.out.println("You cannot use a 2 in a Straight");
					isConsec = false;
					break;
				}
				if ((toPlay.get(i).getRank() + 1) == toPlay.get(i+1).getRank()) {
					isConsec = true;
				}
				else {
					isConsec = false;
					break;
				}
			}
			
			if (isConsec) {
				if (currSet.isEmpty() && playAny) {
					if (turnNum == 1) {
						Card tpFirstC = toPlay.get(0);
						if (tpFirstC.equals(lowestCard)) {
							valid = true;
						}
						else {
							System.out.println("The first turn must include the 3 of Spades");
						}
					}
					else {
						valid = true;
					}
				}
				else {
					Card highestTpC = toPlay.get(straightSize - 1);
					Card highestCurrC = currSet.get(straightSize - 1);
					if (highestTpC.getRank() > highestCurrC.getRank()) {
						valid = true;
					}
					else if (highestTpC.getRank() == highestCurrC.getRank()) {
						if (highestTpC.getSuite() > highestCurrC.getSuite()) {
							valid = true;
						}
					}
				}
			}
		}
		return valid;
	}

	private boolean checkBomb(ArrayList<Card> toPlay, int turnNum) {
		System.out.println("CHECKING BOMB . . .");
		boolean valid = false;
		boolean validBomb = false;
		
		Collections.sort(toPlay);
		Card tpFirstD = toPlay.get(1);
		Card tpSecondD = toPlay.get(3);
		Card tpThirdD = toPlay.get(5);
		
		if (tpFirstD.getRank() == toPlay.get(0).getRank() &&
			tpSecondD.getRank() == toPlay.get(2).getRank() &&
			tpThirdD.getRank() == toPlay.get(4).getRank()) {
			if ((tpFirstD.getRank() + 1 == tpSecondD.getRank()) && (tpSecondD.getRank() + 1 == tpThirdD.getRank())) {
				validBomb = true;
			}
		}
		
		if (turnNum == 1) {
			System.out.println("You cannot play a Bomb on Turn 1");
		}
		else if (validBomb) {
			if (isSingle) {
				if (currSet.size() != 1 && !playAny) {
					System.out.println("The current played set of cards is not a valid play to Bomb");
				}
				else {
					if (currSet.isEmpty() && playAny) {
						System.out.println("You can only Bomb a 2 or another Bomb");
					}
					else if (currSet.get(0).getRank() == 2) {
						valid = true;
					}
					else {
						System.out.println("You can only Bomb a Single that is a 2");
					}
				}
			}
			else if (isBomb) {
				if (currSet.size() != 6 && !playAny) {
					System.out.println("The current played set of cards is not a Bomb");
				}
				else {
					if (currSet.isEmpty() && playAny) {
						System.out.println("You can only Bomb a 2 or another Bomb");
					}
					else {
						Card highestCurrC = currSet.get(5);
						if (tpThirdD.getRank() > highestCurrC.getRank()) {
							valid = true;
						}
						else if (tpThirdD.getRank() == highestCurrC.getRank()) {
							if (tpThirdD.getSuite() > highestCurrC.getSuite()) {
								valid = true;
							}
						}
					}
				}
			}
		}
		else {
			System.out.println("Your selected Bomb was not a set of 3 consecutive Doubles");
		}
		
		return valid;
	}
	
	private boolean checkPasses(int currP, Player p1, Player p2, Player p3, Player p4) {
		boolean allPassed = false;
		switch(currP) {
			case 1: if (p2.isPassing() && p3.isPassing() && p4.isPassing()) {
						p2.setPassing(false);
						p3.setPassing(false);
						p4.setPassing(false);
						allPassed = true;
					}
					break;
			case 2: if (p1.isPassing() && p3.isPassing() && p4.isPassing()) {
						p1.setPassing(false);
						p3.setPassing(false);
						p4.setPassing(false);
						allPassed = true;
					}
					break;
			case 3: if (p1.isPassing() && p2.isPassing() && p4.isPassing()) {
						p1.setPassing(false);
						p2.setPassing(false);
						p4.setPassing(false);
						allPassed = true;
					}
					break;
			case 4: if (p1.isPassing() && p2.isPassing() && p3.isPassing()) {
						p1.setPassing(false);
						p2.setPassing(false);
						p3.setPassing(false);
						allPassed = true;
					}
					break;
		}
		return allPassed;
	}

	private void playSelection(int playerNum) {
		currSet.clear();
		for (Card card : cardSel) {
			currSet.add(card);
		}
		cardSel.clear();
		System.out.println("Player " + playerNum + " played:");
		printCurrPlay();
	}
	
	private void removeFromHand(Player currP, ArrayList<Integer> cIndices) {
		for (int i = cIndices.size() - 1; i >= 0; i--) {
			currP.removeCardAt(cIndices.get(i));
		}
	}
	
	private void setTypeFlags(boolean setS, boolean setD, boolean setT, boolean setSt, boolean setB) {
		isSingle = setS;
		isDouble = setD;
		isTriple = setT;
		isStraight = setSt;
		isBomb = setB;
	}

	private void printCurrRound() {
		if (playAny) {
			System.out.println(">> Current Round is Anything <<");
		}
		else if (isSingle) {
			System.out.println(">> Current Round is Single <<");
		}
		else if (isDouble) {
			System.out.println(">> Current Round is Double <<");
		}
		else if (isTriple) {
			System.out.println(">> Current Round is Triple <<");
		}
		else if (isStraight) {
			System.out.println(">> Current Round is Straight of size: " + straightSize + " <<");
		}
		else if (isBomb){
			System.out.println(">> Current Round is Bomb <<");
		}

	}

}
