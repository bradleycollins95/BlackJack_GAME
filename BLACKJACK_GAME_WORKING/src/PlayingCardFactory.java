import java.util.Random;

/**
 * A class that generates a random card
 * Calls from the PlayingCard class
 *
 * @author 20108508
 */
public class PlayingCardFactory {
    /**
     * Generate a random playing card.
     * @return new playing card
     */
    public static PlayingCard generatePlayingCard(){
        Random rand = new Random();
        int random = rand.nextInt(1, 14);
        final int randomSuit = rand.nextInt(4);
        if (randomSuit == 0) {
            return new PlayingCard(PlayingCard.Suit.CLUBS, random);
        } else if (randomSuit == 1){
            return new PlayingCard(PlayingCard.Suit.DIAMONDS, random);
        } else if (randomSuit == 2){
            return new PlayingCard(PlayingCard.Suit.HEARTS, random);
        } else {
            return new PlayingCard(PlayingCard.Suit.SPADES, random);
        }
    }

    /**
     * Generates random card from Suit of choice
     * @param suit - card suit
     * @return new PlayingCard
     */
    public static PlayingCard generatePlayingCardSuit(PlayingCard.Suit suit){
        Random rand = new Random();
        int random = rand.nextInt(1, 14);

        if (PlayingCard.Suit.CLUBS == suit) {
            return new PlayingCard(PlayingCard.Suit.CLUBS, random);
        } else if (PlayingCard.Suit.DIAMONDS == suit){
            return new PlayingCard(PlayingCard.Suit.DIAMONDS, random);
        } else if (PlayingCard.Suit.HEARTS == suit){
            return new PlayingCard(PlayingCard.Suit.HEARTS, random);
        } else {
            return new PlayingCard(PlayingCard.Suit.SPADES, random);
        }
    }
}
