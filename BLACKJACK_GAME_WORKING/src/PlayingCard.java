/**
 * Playing card for a standard american style card game. Cards have a face value and a suit. The face value can be
 * between 2 and 10 or can be a special card with a face value A("ACE"), K("King"), Q("Queen"), J("Jack").
 * Playing Cards are not meant to be initialized themselves, they should be generated as part of a complete Deck of
 * cards VIA a factory object
 * NOTE: There are no joker or other cards included as part of this class
 * REQUIREMENTS
 * Create a class called ‘PlayingCard’ that does the following:
 * - Has a constructor which creates the card - it should have a valid suit and a value.
 * - You should have getters only for these values.
 * - Note: use constants for suits and think of how you can constrain the value.
 *   (Hint: You can use constants or enums.)
 * - Override the toString method to print the playing card like “8 ♣” or “K ♦” (static).
 *   (Hint: google ‘unicode character for diamond’)
 * - Bonus: Print an ASCII Art version of the Card to the print screen like the following:
 *          printAsciiCard(PlayingCard playingCard, PrintStream)
 *
 * @author 20108508
 * A class that creates a playing card
 */
public record PlayingCard(PlayingCard.Suit suit, int faceValue){

    public enum Suit {HEARTS, DIAMONDS, SPADES, CLUBS}

    public static final int ACE = 1;
    public static final int KING = 13;
    public static final int QUEEN = 12;
    public static final int JACK = 11;

    /**
     * Constructor that creates a card.
     * Gets card value and card suit
     *
     * @param suit - Suit, card suit
     * @param faceValue - int, card value
     */
    public PlayingCard {
    }

    /**
     * Gets suit
     * @return suit
     */
    @Override
    public Suit suit() {
        return suit;
    }

    /**
     * Gets value
     * @return value
     */
    @Override
    public int faceValue() {
        return faceValue;
    }

    /**
     * Displays the card as a string.
     * Changes the values of the face cards to designated letter.
     * For example:
     * {@code
     * A\u2660} Instead of 1SPADES
     *
     * @return faceValue
     */
    @Override
    public String toString() {
        if (this.faceValue == ACE) {
            return "A" + getStringValue(this.suit());
        } else if (faceValue == JACK) {
            return "J" + getStringValue(this.suit());
        } else if (faceValue == QUEEN) {
            return "Q" + getStringValue(this.suit());
        } else if (faceValue == KING) {
            return "K" + getStringValue(this.suit());
        }
        return faceValue + getStringValue(this.suit());
    }

    /**
     * Change value of each suit to Unicode
     *
     * @param suit - card suit
     * @return Unicode Character
     */
    public static String getStringValue(Suit suit) {
        if (suit == Suit.SPADES) {
            return "\u2660";
        } else if (suit == Suit.HEARTS) {
            return "\u2665";
        } else if (suit == Suit.CLUBS) {
            return "\u2663";
        } else {
            return "\u2666";
        }
    }
}
