/* Deck uses an arraylist to replicate one deck of cards. */

import java.util.ArrayList;
import java.util.Random;

public class Deck{
    private ArrayList<Card> cards = new ArrayList<Card>();
    
    /* The constructor generates all possible cards and adds them to list of 
     * of cards. */
    public Deck(){
        for (int symbol = 0; symbol < 4; symbol++){
            for (int number = 1; number <= Card.NUMBER_KING; number++){
                this.cards.add(new Card(symbol, number));
            }
        }
    }
    
    /* shuffleDeck shuffles the cards by randomly picking a card in the current
     * deck and adding it into a new list until no cards in the current deck
     * are left. The new list of cards is set as the deck's new card list. */
    public void shuffleDeck(){
        ArrayList<Card> newDeckCards = new ArrayList<Card>();
        Random rand = new Random();
        int cardIndex = 0;
        
        while (this.cards.size() > 0){
            // pick a random card
            cardIndex = rand.nextInt(this.cards.size());
            Card thisCard = this.cards.get(cardIndex);
            
            // move card to the new card list
            newDeckCards.add(thisCard);
            this.cards.remove(thisCard);
        }
        
        this.cards = newDeckCards;
    }
    public void showDeck(){
        String deckDisplayString = "";
        for (Card thisCard:this.cards){
            deckDisplayString += thisCard.toString();
        }
        System.out.println(deckDisplayString);
    }
}