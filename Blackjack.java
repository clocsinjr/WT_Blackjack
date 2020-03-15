class Blackjack {
    public static void main(String[] args){
        Deck deck = new Deck();
        System.out.println("Unshuffled deck:");
        deck.showDeck();
        
        System.out.println("\nShuffled deck:");
        deck.shuffleDeck();
        deck.showDeck();
    }
}