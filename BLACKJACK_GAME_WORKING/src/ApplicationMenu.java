import java.util.ArrayList;
import java.util.Scanner;

/**
 * Application display
 * User enters a command from the given list allowing them to either:
 * Draw 1 card ('A')
 * Draw a hand of 5 cards ('B')
 * Shuffle the deck ('C')
 * Print out the deck ('D')
 * OR
 * Quit ('Q')
 *
 * @author 20108508
 */
public class ApplicationMenu {
    public static void main(String[] args) {
        System.out.println("Deck of Cards");

        Scanner input = new Scanner(System.in);
        char c; //Char to get menu choice
        //empty ArrayList for the deck
        ArrayList<PlayingCard> cards = new ArrayList<>();

        CardDeck deck = new CardDeck(cards);// Creates the deck
        do {
            printMenu();
            //Grab the user input
            c = Character.toUpperCase(input.next().charAt(0));

            System.out.printf("\nEcho: %c\n", c);

            if (c == 'A'){
                PlayingCard card = deck.drawCards();
                //Prints card
                System.out.println(card);
            }
            if (c == 'B'){
                System.out.println("Here's a 5 card hand:");
                for (int i = 1; i <= 5; i++) {
                    PlayingCard card = deck.drawCards();
                    //Prints drawn cards
                    System.out.printf("\n%d: %s", i, card);
                }
            }
            if (c == 'C') {
                deck.shuffleCards();
                System.out.println("You shuffled the deck.\nIt is now randomized.");
            }
            if (c == 'D') {
                deck.printDeck();
            }
        }
        while (c != 'Q');
    }

    public static void printMenu () {
        System.out.println("\nSelect from the following options:");
        System.out.println("(A) Draw a card");
        System.out.println("(B) Draw a hand of 5 cards");
        System.out.println("(C) Shuffle");
        System.out.println("(D) Print out the deck");
        System.out.println("(Q) Quit");
    }
}
