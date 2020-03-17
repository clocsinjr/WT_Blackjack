/* 
 * Blackjack provides a user interface via the command terminal to play
 * Blackjack with multiple players. 
 */

import java.util.Scanner;

class Blackjack {
    static String invalidInputString = "invalid input! ";
    static String actionQuestion = "(h) hit, (p) pass, (q) quit\t- Action: ";
    static String roundEndQuestion = "Round ended!\n" +
        " (n) next round\n (p) change players\n (q) quit\n Action: ";
    
    /* askNumPlayers() asks the user to specify the number of players. Returns
     * the number of players. Answer must be a number between the minimum and
     * maximum allowed players as defined in BlackjackGamestate. 
     */
    public static int askNumPlayers(){
        Scanner inputScanner = new Scanner(System.in);
        String inp;
        boolean validInput = false;
        int nPlayers = 0;
        
        while(!validInput){
            System.out.print("How many players? (1-5): ");
            inp = inputScanner.nextLine().trim();
            
            try{
                nPlayers = Integer.parseInt(inp);
                if (nPlayers < BlackjackGamestate.MIN_PLAYERS ||
                    nPlayers > BlackjackGamestate.MAX_PLAYERS){
                    System.out.print("Unaccepted amount of players! ");
                }
                else{
                    validInput = true;
                }
            }
            catch (Exception e){
                System.out.print(invalidInputString);
            }
        }
        return nPlayers;
    }
    
    /* playTurn() plays one turn of the Blackjack game specified as argument, 
     * letting each player make a decision on what they want to do with their 
     * hand (hit, pass or quit). Returns a boolean that specifies whether the
     * game should stop afterwards. Immediately returns true if any 
     * of the players quits. Returns false if no players want to quit
     */
    public static boolean playTurn(BlackjackGamestate game){
        int nPlayers = game.getNumberOfPlayers();
        
        Scanner inputScanner = new Scanner(System.in);
        String inp;
        boolean validInput;
        
        for (int i = 0; i < nPlayers; i++){
            game.setCurrentPlayer(i);
            game.showGamestate();
            
            // If the player is not done for this round, ask for action
            if (!game.isPlayerDone(i)){
                validInput = false;
                while(!validInput){
                    System.out.print(actionQuestion);
                    inp = inputScanner.nextLine();
                    
                    if (inp.equals("h")){
                        game.hitPlayer(i);
                        validInput = true;
                    }
                    else if (inp.equals("p")){
                        game.playerToDone(i);
                        validInput = true;
                    }
                    else if (inp.equals("q")){
                        return true;
                    }
                    else{
                        System.out.print(invalidInputString);
                    }
                }
            }
        }
        
        return false;
    }
    
    public static void playEndRound(BlackjackGamestate game){
        Scanner inputScanner = new Scanner(System.in);
        
        System.out.print("Dealer's turn! Press Enter to continue ");
        inputScanner.nextLine();
        
        game.playDealer();
        game.showResults();
    }
    
    /* playRound() plays a round of blackjack. The game keeps playing turns 
     * where all players have to make a decision on what to do with their hand.
     * the round ends when all players have busted, passed or if any quit.
     */
    public static void playRound(int nPlayers){
        BlackjackGamestate game = new BlackjackGamestate(nPlayers);
        boolean quitNow = false;
        
        // play rounds until everyone has busted or passed and hasn't quit
        while (game.getNumberOfPlayersDone() < nPlayers){
            quitNow = playTurn(game);
            
            // End round if a player has chosen to quit
            if (quitNow){ return; }
        }
        game.showGamestate();
        
        playEndRound(game);
    }
    
    public static void main(String[] args){
        System.out.println("Play Blackjack!");
        
        Scanner inputScanner = new Scanner(System.in);
        String inp;
        boolean validInput = false;
        
        int nPlayers = askNumPlayers();
        
        // Start game loop;
        boolean gameOngoing = true;
        while(gameOngoing){
            playRound(nPlayers);
            
            // Ask whether to play a new round, change players or quit
            validInput = false;
            while(!validInput){
                System.out.print(roundEndQuestion);
                inp = inputScanner.nextLine();
                if (inp.equals("n")){
                    validInput = true;
                }
                else if (inp.equals("q")){
                    gameOngoing = false;
                    validInput = true;
                }
                else if (inp.equals("p")){
                    nPlayers = askNumPlayers();
                    validInput = true;
                }
                else{
                    System.out.print(invalidInputString);
                }
            }
        }
        
        System.out.println("Quitting game");
    }
}