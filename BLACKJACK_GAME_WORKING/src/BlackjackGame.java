import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a class for a simple game of Blackjack.
 * When the main method is called, it creates a deck of playing cards, shuffles it,
 * and then deals two cards to both the player and the dealer.
 * It then calculates the values of the player's and dealer's hands and displays them.
 * The player is then given the option to "hit" or "stay", with "hit" meaning
 * that the player will receive another card and "stay" meaning that the player will keep their current hand.
 * The game continues until the player chooses to stay or until their hand value exceeds 21
 * (at which point the player will lose).
 * The dealer will continue to draw cards until their hand value is
 * greater than or equal to the player's hand value or until their hand value exceeds 21.
 * The winner is then determined based on the hand values of the player and the dealer.
 * If the player's hand value is greater than the dealer's hand value or
 * if the dealer's hand value exceeds 21, the player wins.
 * Otherwise, the dealer wins.
 *
 * @author 20108508
 */
public class BlackjackGame {

    public static void main(String[] args) {

        ArrayList<PlayingCard> deck = new ArrayList<>();
        CardDeck cardDeck = new CardDeck(deck);

        Scanner scanner = new Scanner(System.in);

        int score = 0;
        int gameCounter = 1;
        int balance = 100;
        int input;

        System.out.println("\nWelcome to BlackJack!\n");
        System.out.println("""
                Rules:
                
                - Blackjack hands are scored by their point total.\s
                - The hand with the highest total wins as long as it doesn't exceed 21.\s
                - A hand with a higher total than 21 is said to bust.\s
                - Cards 2 through 10 are worth their face value, and face cards (jack, queen, king) are also worth 10.
                """);
        System.out.printf("Game %d:\n", gameCounter);
        System.out.printf("Your score is: %d\n", score);
        System.out.printf("Your balance is: $%d\n\n", balance);

        System.out.println("Please enter an amount to wager: \n");
        input = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter an amount to wager: \n");
        System.out.printf("Your new balance is: $%d\n\n", balance - input);


        //shuffle the deck
        cardDeck.shuffleCards();

        //create player and dealer hands
        ArrayList<PlayingCard> playerHand = new ArrayList<>();
        ArrayList<PlayingCard> dealerHand = new ArrayList<>();

        //deal the first 2 cards to player and dealer
        playerHand.add(cardDeck.drawCards());
        dealerHand.add(cardDeck.drawCards());
        playerHand.add(cardDeck.drawCards());
        dealerHand.add(cardDeck.drawCards());

        //calculate player and dealer hand values
        int playerHandValue = calculateHandValue(playerHand);
        int dealerHandValue = calculateHandValue(dealerHand);

        //display player and dealer hands
        //display player and dealer hand values
        System.out.println("Player Hand: " + playerHand);
        System.out.println("Player Hand Value: " + playerHandValue);
        System.out.println();
        System.out.println("Dealer Hand: " + dealerHand.get(0));
        System.out.println("Dealer Hand Value: " + dealerHandValue);
        System.out.println();

        //allow player to hit or stay
        Scanner sc = new Scanner(System.in);

        while(playerHandValue < 21 && dealerHandValue != 21){
            System.out.println("Hit or stay? (h/s)\n");
            String choice = sc.nextLine();
            if(choice.equalsIgnoreCase("h")){ //hit - deal a card to player
                playerHand.add(cardDeck.drawCards());
                System.out.println("Dealer hand: " + dealerHand); //display updated player hand and value
                System.out.println("Dealer Hand Value: " + dealerHandValue); //display updated player hand and value
                System.out.println();
                System.out.println("Player Hand: " + playerHand);
                playerHandValue = calculateHandValue(playerHand);
                System.out.println("Player Hand Value: " + playerHandValue);
            } else if(choice.equalsIgnoreCase("s")){
                if(playerHandValue == 20 && dealerHandValue == 20) {
                    System.out.println("Dealer draws another card.\n");
                    dealerHand.add(cardDeck.drawCards());
                    System.out.println("Dealer hand: " + dealerHand);
                    dealerHandValue = calculateHandValue(dealerHand);
                    System.out.println("Dealer hand value: " + dealerHandValue);
                    System.out.println();
                }
                while(dealerHandValue < playerHandValue){
                    System.out.println("Dealer draws another card.\n");
                    dealerHand.add(cardDeck.drawCards());
                    System.out.println("Dealer hand: " + dealerHand);
                    dealerHandValue = calculateHandValue(dealerHand);
                    System.out.println("Dealer hand value: " + dealerHandValue);
                    System.out.println();
                }
                break; //stay; exit loop
            }
        }

        //determine winner
        if(playerHandValue == 21 && dealerHandValue == 21){
            //if both hands have blackjack, it's a tie
            System.out.println("Tie!");
        } else if(playerHandValue == 21){ //if player has blackjack, player wins
            System.out.println("Player wins!");
            score++;
        } else if(dealerHandValue == 21){ //if dealer has blackjack, dealer wins
            System.out.println("Dealer wins!");
        } else if(dealerHandValue > 21){ //if dealer busts, player wins
            System.out.println("Dealer busts, player wins!");
        } else{ //if no blackjacks, compare hand values
            if(playerHandValue > dealerHandValue){
                //if the player's hand exceeds the dealers hand
                //and is higher than 21, they lose
                if(playerHandValue > 21){
                    System.out.println("Player busts, dealer wins!");
                } else{ //otherwise the player wins
                    score++;
                    System.out.println("Player wins!");
                }
            } else if(dealerHandValue > playerHandValue){
                //if the dealer's hand exceeds the player's hand
                //and is also higher than 21, the dealer loses
                if(dealerHandValue > 21){
                    System.out.println("Dealer busts, player wins!");
                    score++;
                } else{
                    System.out.println("Dealer wins!");
                }
            }
        }
        gameCounter++;
        //display game results
        System.out.println();
        System.out.printf("Game %d:\n", gameCounter);
        System.out.printf("Your score is: %d\n\n", score);

        //ask if player wants to play again
        System.out.println("Play again? (y/n)\n");
        String playAgain = sc.nextLine();
        if(playAgain.equalsIgnoreCase("y")){ //restart game
            main(args);                                 //restart game
        } else if(playAgain.equalsIgnoreCase("n")){ //end game
            System.out.println("Goodbye!");
            System.exit(0); //end game
        }
    }

    /**
     * Calculates the value of a hand of cards
     *
     * @param hand - ArrayList of PlayingCard objects
     * @return total value of hand
     */
    public static int calculateHandValue(ArrayList<PlayingCard> hand) {
        int handValue = 0;
        int numAces = 0;
        //calculate value of each card in hand
        for (PlayingCard card : hand) {
            if (card.faceValue() == PlayingCard.ACE) {
                //if card is an Ace, add 11 to hand value and keep track of number of aces
                handValue += 11;
                numAces++;
            } else if (card.faceValue() > 10) {
                //if card is a face card (Jack, Queen, King), add 10 to hand value
                handValue += 10;
            } else {
                //if card is any other number, add face value to hand value
                handValue += card.faceValue();
            }
        }
        return handValue;
    }
}
