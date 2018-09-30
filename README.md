# Tien Len Simulator
Author: Kalvin Lam

## Description:
This program is a console-based simulator of Tien Len, a Vietnamese card game often known in English as Thirteen or Killer 13.  Tien Len is a game where a regular deck of playing cards is divided among 4 players and the objective is to be the first player to have no cards left in hand. This is done by taking turns and playing a specific combination of cards depending on the combination chosen for each round of the game.
    
## Running the Simulator:
1. If you haven't already, download this repository. Please check that the following files are present:
    ```
    Card.java 
    Deck.java
    Player.java
    PlayedPile.java
    GameManager.java
    Game13.java
    ```
2. Open a command prompt (or terminal) and change directories to where the files listed above were downloaded to. Compile all the files using the `javac *.java` command and ensure there is a .class file for each of the listed files.
3. To run the simulator, type on the command line: `java Game13`

### Running the Tester Files:
- In addition to the main files downloaded, 3 tester files should also be included:
    ```
    TestCard.java
    TestDeck.java
    TestPlayer.java
    ```
- These testers were basic tests the functionality of the Card class, Deck class, and Player class.
1. To run these testers, first uncomment any line that does not have `Test # - ` in it.
2. If you haven't already, compile the test file you would like to use and make sure there is a .class file for it.
3. Run the tester by typing: `java <name of tester file>` (ie. `java TestCard`).
    
## Game Rules:
_Note: The rules you are about to read for this simulator is adopted from the rules listed on the **Tien Len wiki page** and covers a simplified version of the actual Tien Len game. In other words, not all of the official rules are present in this game. The full game details can be read [here](https://en.wikipedia.org/wiki/Ti%E1%BA%BFn_l%C3%AAn). Additionally, these rules can be read again by entering 2 (ie. 2 \<enter>\) at the home screen of the simulator._
    
### Cards:
- Standard 52 Playing Cards
- Ranking of cards from highest to lowest: 2 A K Q J 10 9 8 7 6 5 4 3
- Ranking of suites from highest to lowest: Hearts, Diamonds, Clubs, Spades

### Legal Combinations:
- Single: A single played card. Singles can only be defeated by singles that are higher in rank.
- Double: A combination of exactly 2 cards of the same rank. A double can only be defeated by another pair of higher rank than the **highest card** of the previous pair. For example, if a player plays a 9:spades:9:diamonds:, the next player would need to play a 9:clubs:9:hearts: or a higher-ranked pair. to defeat the previous pair
- Triples: A combination of exactly 3 cards of the same rank. They can only be defeated by a triple of a higher rank. For example, if a player plays the triple 4:spades:4:diamonds:4:hearts:, the next player will have to play a 5:spades:5:clubs:5:diamonds: or higher to defeat the previous triple.
- Straight: A combination of at least 3 cards that are in numberical sequence. The order of the cards must be in a consecutive order. The highest possible ending card in a straight is an A and the lowest beginning card is the 3.

### Twos and Bombs:
- 2's (ie. 2:spades:, 2:clubs:, 2:diamonds:, 2:hearts:) are the strongest card and can only be played as a single.
- The only way to beat a 2 is with a **Bomb** - 3 doubles in numerical sequence (ie. 3-3-4-4-5-5).
- You can beat a bomb with another bomb of a higher rank than the highest card of the previous pair (ie. a 3:spades:3:diamonds:4:diamonds:4:hearts:5:clubs:5:diamonds: can be defeated by 3:clubs:3:hearts:4:spades:4:clubs:5:spades:5:hearts:).

### Play:
- Each player is dealt 13 cards.
- The person with the 3 of spades will go first.
- In a turn, a player will decided to play or not (called passing). A player who passes cannot play anymore until the remaining players pass.
- If a player chooses to play, they may only play a combination that matches the combination of the current round (ie. If the player who started the round played a single, the only singles may be played until the next round starts).
- When a player plays a combination and everyone else passes, he or she has control and can play any legal combination.
- The first player to shed all 13 cards is the winner.

## Design Choices
For this simulator, I chose to program it in Java mainly because I wanted to work with the language I am most comfortable and familiar with for my first personal project. I wanted to build the game from scratch so I implemented the game's card, deck, and player as their own separate classes. Not only did this allow me to have more control over what data fields and methods were necessary specifically for the game, but also allowed me to implement the cards, deck, and player as their own separate entities that I could potentially use for a different card game. Additionally, I implemented a GameManager class that handles generating the game, preparing the deck, dealing the cards, rotating through the players turns, and check for when the game is over. Furthermore, the PlayedPile class handles the steps in a player's turn as well as checks for valid plays.

The only data structure I used in this simulator was a mutable array (specially, the ArrayList in Java) to simulate the deck of cards. Originally, I played with the idea of using a stack as the underlying data structure for the deck, the played pile, and player's hand since the game only involves dealing cards from the deck, playing cards from the hand, and putting the played cards in a played pile. However, I realized that a stack is not a good data structure choice because it is only capable of removing from the top of the stack when I wanted to implement shuffling methods that involved accessing cards from the middle of the deck. Additionally, a stack for the players hand was not appropriate for this game because Tien Len involves being able to choose combinations of cards which can be anywhere in the hand, not just the top (in this case the right-most card). Therefore, I concluded that an ArrayList was the most appropriate data structure because it was mutable and was capable of accessing any of the elements by index, which means I can access any card in the deck or hand.

Although I did not implement any algorithms for this program, I did utilize the sort method found in the `java.util.Collections` library in order to sort the deck and players hand. Since in Tien Len, 2's are the highest ranked card and Aces's are the second highest ranked card, I had to modify their ranking value when creating the cards and the deck. Thus, I overloaded the `compareTo` method in order use the sort method to correctly sort the cards in the deck for the game.

## Java Libraries Used:
- java.util.ArrayList : This was used as the underlying data structure for the deck of cards, players' hands, and played pile.
- java.util.Collections : This was used in order to sort the deck and players' hands.
- java.util.Random: This was used to randomize the card shuffling in the deck.
- java.util.Scanner: This was used in order to read key input from the user during a player's turn.
- java.util.IOException : This was used in order to catch any errors from the user input.
