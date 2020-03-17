/* 
 * BlackjackGamestate saves a state of a Blackjack game. It keeps track of the 
 * hands of all players, including the dealer. It provides the methods needed 
 * to play Blackjack with multiple players and a single pack of 52 cards.
 */

import java.util.ArrayList;

public class BlackjackGamestate{
    public static final int MIN_PLAYERS = 1;
    public static final int MAX_PLAYERS = 5;
    
    private Deck cardStack = new Deck(true); // full set
    
    private Deck dealer = new Deck();
    private Deck[] players;
    
    // list of players who have busted or passed
    // players in this list cannot play until a new round starts
    private ArrayList<Deck> playersDone; 
    
    private int numberOfPlayers = 0;
    private int currentPlayer = 0;
    
    /* Constructor initializes the arrays of players */
    public BlackjackGamestate(int n_players){
        cardStack.shuffleDeck();
        
        this.numberOfPlayers = n_players;
        this.players = new Deck[n_players];
        this.playersDone = new ArrayList<Deck>(n_players);
        
        for (int i = 0; i < n_players; i++){
            this.players[i] = new Deck();
        }
    }
    
    /* drawPlayer() draws a card from the card stack and adds it to the hand
     * of the specified player */
    public void drawPlayer(Deck player){
        Card drawnCard = this.cardStack.drawCard();
        player.addCard(drawnCard);
    }
    
    /* drawDealer() draws a card from the stack to the dealer */
    public void drawDealer(){
        Card drawnCard = cardStack.drawCard();
        this.dealer.addCard(drawnCard);
    }
    
    // getter numberOfPlayers
    public int getNumberOfPlayers(){
        return this.numberOfPlayers;
    }
    
    // getter for number of players who have passed or busted
    public int getNumberOfPlayersDone(){
        return this.playersDone.size();
    }
    
    // updates current player indicator
    public void setCurrentPlayer(int nPlayer){
        this.currentPlayer = nPlayer;
    }
    
    // Move player to done list
    public void playerToDone(int nPlayer){
        Deck tp = this.players[nPlayer];
        this.playersDone.add(tp);
    }
    
    /* hitPlayer() takes the player number. The specified player takes a card 
     * from the card stack. If the player busts, the player is moved to done */
    public void hitPlayer(int nPlayer){
        Deck tp = this.players[nPlayer];
        this.drawPlayer(tp);
        
        if (tp.value() > 21){
            this.playerToDone(nPlayer);
        }
    }
    
    /* isPlayerDone() Checks whether the given player is in the done list.
     * Returns true if the player is in the list, else returns false */
    public boolean isPlayerDone(int nPlayer){
        Deck tp = this.players[nPlayer];
        if (this.playersDone.contains(tp)){
            return true;
        }
        return false;
    }
    
    /* showGamestate() prints the current state of the game to the terminal in
     * a clear fashion. */
    public void showGamestate(){
        // print dealer's hand
        System.out.print("\n\tDealer \tscore: " + this.dealer.value() + "\t");
        this.dealer.showDeck();
        
        Deck player;
        StringBuilder sb;
        
        // print every player's hand
        for (int i = 0; i < this.players.length; i++){
            sb = new StringBuilder();
            player = this.players[i];
            int score = player.value();
            
            // Display score if not busted, else default to "BUST"
            String scoreDisp = "BUST";
            if (score <= 21){
                scoreDisp = Integer.toString(score);
            }
            
            // If it's the current player's turn, print an indicator
            if (this.currentPlayer == i){
                sb.append("   >");
            }
            sb.append("\tPlayer" + i + "\tscore: " + scoreDisp + "\t");
            System.out.print(sb.toString());
            player.showDeck();
        }
        System.out.println();
    }
    
}