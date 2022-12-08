import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackGame {

    public static void main(String[] args) {

        ArrayList<PlayingCard> deck = new ArrayList<>();
        CardDeck cardDeck = new CardDeck(deck);

        //Scanner scanner = new Scanner(System.in);

        //int wager = Integer.parseInt(scanner.nextLine());
        int score = 0;
        int gameCounter = 1;

        System.out.println("\nWelcome to BlackJack!\n");
        System.out.println("""
                Rules:
                
                - Blackjack hands are scored by their point total.\s
                - The hand with the highest total wins as long as it doesn't exceed 21.\s
                - A hand with a higher total than 21 is said to bust.\s
                - Cards 2 through 10 are worth their face value, and face cards (jack, queen, king) are also worth 10.
                """);
        System.out.printf("Game %d:\n", gameCounter);
        System.out.printf("Your score is: %d\n\n", score);


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
        while(playerHandValue < 21){
            System.out.println("Hit or stay? (h/s)\n");
            String choice = sc.nextLine();
            if(choice.equalsIgnoreCase("h")){
                //hit - deal a card to player
                playerHand.add(cardDeck.drawCards());
                //display updated player hand and value
                System.out.println("Dealer hand: " + dealerHand);
                System.out.println("Dealer Hand Value: " + dealerHandValue);
                System.out.println();
                System.out.println("Player Hand: " + playerHand);
                playerHandValue = calculateHandValue(playerHand);
                System.out.println("Player Hand Value: " + playerHandValue);
            } else if(choice.equalsIgnoreCase("s")){
                while(dealerHandValue < playerHandValue){
                    dealerHand.add(cardDeck.drawCards());
                    System.out.println("Dealer hand: " + dealerHand);
                    dealerHandValue = calculateHandValue(dealerHand);
                    System.out.println("Dealer hand value: " + dealerHandValue);
                    System.out.println();
                }
                //stay - break out of loop
                break;
            }
        }

        //determine winner
        if(playerHandValue == 21 && dealerHandValue == 21){
            //if both hands have blackjack, it's a tie
            System.out.println("Tie!");
        } else if(playerHandValue == 21){
            //if player has blackjack, player wins
            System.out.println("Player wins!");
            score++;
        } else if(dealerHandValue == 21){
            //if dealer has blackjack, dealer wins
            System.out.println("Dealer wins!");
        } else if(dealerHandValue > 21){
            //if dealer busts, player wins
            System.out.println("Dealer busts, player wins!");
            score++;
        } else{
            //if no blackjacks, compare hand values
            if(playerHandValue > dealerHandValue){
                //if the player's hand exceeds the dealers hand
                //and is higher than 21, they lose
                if(playerHandValue > 21){
                    System.out.println("Player busts, dealer wins!");
                } else{
                    //otherwise the player wins
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
                    //if both hands have the same value, tie
                    System.out.println("Tie!");
                }
            }
        }

        //display game results
        gameCounter++;
        System.out.println();
        System.out.printf("Game %d:\n", gameCounter);
        System.out.printf("Your score is: %d\n\n", score);

        //ask if player wants to play again
        System.out.println("Play again? (y/n)\n");
        String playAgain = sc.nextLine();
        if(playAgain.equalsIgnoreCase("y")){
            //restart game
            main(args);
        } else if(playAgain.equalsIgnoreCase("n")){
            //end game
            System.out.println("Goodbye!");
            System.exit(0);
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
