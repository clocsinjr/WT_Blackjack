/* 
 * Deck uses an arraylist to replicate one deck of cards and provides methods
 * to draw Cards, generate Cards or calculate the Blackjack value of the cards
 * currently in the deck 
 */

import java.util.ArrayList;
import java.util.Random;

public class Deck{
    private ArrayList<Card> cards;
    
    
    /* Constructor without parameters starts with an empty list of cards */
    public Deck(){
        this.cards = new ArrayList<Card>(Card.MAX_CARDS);
    }
    
    /* When given a boolean isFullSet, this constructor fills the cards field 
     * with a full set of 52 cards */
    public Deck(boolean isFullSet){
        if (isFullSet){
            this.generateFullSet();
        }
        else{
            this.cards = new ArrayList<Card>(Card.MAX_CARDS);
        }
    }
    
    /* drawCard() removes the first card in the deck and returns it */
    public Card drawCard(){
        Card topCard = this.cards.get(0);
        this.cards.remove(0);
        
        return topCard;
    }
    
    /* addCard() takes the specified card and adds it to the back of the deck */
    public void addCard(Card card){
        this.cards.add(card);
    }
    
    /* value() calculates the blackjack value of the cards currently in the
     * hand and returns it. The values of the aces in the hand are picked to
     * maximize the score while trying to prevent the score from going over 21
     */
    public int value(){
        int totalValue = 0;
        int n_aces = 0;
        int cn = 0; //temporary card number
        
        // Sum the values of all cards in hand
        for (Card card:this.cards){
            cn = card.getNumber();
            if (cn == Card.NUMBER_ACE){
                totalValue += 11;   // automatically counted as 11
                n_aces += 1;
            }
            else if (cn >= Card.NUMBER_JACK){
                totalValue += 10;
            }
            else{
                totalValue += cn;
            }
        }
        
        /* to reduce the score when possible, remove 10 (count an Ace as 1 
         * instead of 11) off of the score until the score is below 21 or
         * until the player is out of aces. */
        while(totalValue > 21 && n_aces > 0){
            totalValue -= 10;
            n_aces -= 1;
        }
        return totalValue;
    }
    
    /* generateFullSet() generates a new list of all 52 possible cards and sets
     * it as this Deck's cards */
    public void generateFullSet(){
        ArrayList<Card> newCards = new ArrayList<Card>(Card.MAX_CARDS);
        for (int symbol = 0; symbol < Card.SYMBOLS.length; symbol++){
            for (int number = 1; number <= Card.NUMBER_KING; number++){
                newCards.add(new Card(symbol, number));
            }
        }
        this.cards = newCards;
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
    
    /* showDeck() prints all cards in a row */
    public void showDeck(){
        String deckDisplayString = "";
        for (Card thisCard:this.cards){
            deckDisplayString += thisCard.toString();
        }
        System.out.println(deckDisplayString);
    }
}