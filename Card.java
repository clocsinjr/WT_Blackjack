/* Card is used to represent playing cards in a deck. It uses int's for both
 * symbols (0 to 3) and numbers (0 to 13). When a card is created, Strings used
 * to display the cards properly are also set (e.g. 1 = A, 11 = K) 
 */
public class Card{
    public static final String[] SYMBOLS = {"♣", "♦", "♥", "♠"};
    public static final int MAX_NUMBER = 13; //number of different card values
    
    // number value constants for cards that don't display a number
    public static final int NUMBER_JOKER = 0; //unused in blackjack
    public static final int NUMBER_ACE = 1;
    public static final int NUMBER_JACK = 11;
    public static final int NUMBER_QUEEN = 12;
    public static final int NUMBER_KING = 13;
    
    private final int symbol;
    private final int number;
    private final String numberDisplay;
    
    private final int blackjack_value;
    
    /* when given an int for the symbol (0, 1, 2, or 3) and an int for the 
     * the number, this constructor saves the symbol, and int, and uses them
     * to determine the display value (e.g. 11 displays J) and blackjack values
     * (e.g. a J counts for 10). */
    public Card(int symbol, int number){
        this.symbol = symbol;
        this.number = number;
        
        String numberDisplayTemp = "";
        
        /* blackjack value is 10 for all cards above 10 (J, Q, K), these cards
         * should also display the letters instead of the number value */
        if (number > NUMBER_JACK){
            this.blackjack_value = 10;
            
            if (number == NUMBER_JACK){
                numberDisplayTemp = "J";
            }
            if (number == NUMBER_QUEEN){
                numberDisplayTemp = "Q";
            }
            if (number == NUMBER_KING){
                numberDisplayTemp = "K";
            }
        }
        /* the ace is displayed as A and has a value of 11 */
        else if (number == NUMBER_ACE){
            this.blackjack_value = 11;
            numberDisplayTemp = "A";
        }
        else{
            this.blackjack_value = number;
            numberDisplayTemp = Integer.toString(number);
        }
        
        this.numberDisplay = numberDisplayTemp;
    }
    
    /* toString will display the unicode character for the symbol and the number
     * in between brackets. */
    public String toString(){
        return "[" + this.SYMBOLS[this.symbol] + this.numberDisplay + "]";
    }
}
