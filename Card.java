/* 
 * Card is used to represent playing cards in a deck. It uses int's for both
 * symbols (0 to 3) and numbers (0 to 13). When a card is created, Strings used
 * to display the cards properly are also set (e.g. 1 = A, 11 = K) 
 */
public class Card{
    public static final String[] SYMBOLS = {"♣", "♦", "♥", "♠"};
    public static final int MAX_CARDS = 52; //number of cards in a full set
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
    
    /* when given an int for the symbol (0, 1, 2, or 3) and an int for the 
     * the number, this constructor saves the symbol, and int, and uses them
     * to determine the display value (e.g. 11 displays J) */
    public Card(int symbol, int number){
        this.symbol = symbol;
        this.number = number;
        
        String numberDisplayTemp = "";
        
        switch(number){
            case NUMBER_ACE:
                numberDisplayTemp = "A";
                break;
            case NUMBER_JACK:
                numberDisplayTemp = "J"; 
                break;
            case NUMBER_QUEEN:
                numberDisplayTemp = "Q";
                break;
            case NUMBER_KING:
                numberDisplayTemp = "K";
                break;
            default:
                numberDisplayTemp = Integer.toString(number);
        }
        
        this.numberDisplay = numberDisplayTemp;
    }
    
    public int getSymbol(){
        return this.symbol;
    }
    
    public int getNumber(){
        return this.number;
    }
    /* toString will display the unicode character for the symbol and the number
     * in between brackets. */
    public String toString(){
        return "[" + this.SYMBOLS[this.symbol] + this.numberDisplay + "]";
    }
}
