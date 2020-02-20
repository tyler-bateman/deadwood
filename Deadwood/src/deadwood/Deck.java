package deadwood;
import java.util.Stack;
import java.util.Random;
/**
 * Class: Deck
 * Manages the stack of cards that haven't been used yet. Deals these cards to the scenes
 * 
 * Implements Singleton design pattern with eager initialization
 * since there will always be one and only one deck that is initialized at startup
 * 
 * @author nada & tyler
 */
public class Deck {
    
    private static Deck instance = new Deck();
    
    private static Stack<SceneCard> cards;
    
    /**
     * Private constructor as required by Singleton design pattern
     * Does not modify the state of the deck as this is done externally by ParseXML.java
     */
    private Deck() {}
    
    /**
     * getInstance() method as required by Singleton design pattern
     * @return the singular instance of deck
     */
    public static Deck getInstance() {
        return instance;
    }
    
    /**
     * Initializes the deck with a new stack of cards
     * @param cardStack: the new stack of cards
     */
    public void initDeck(Stack<SceneCard> cardStack) {
      cards = cardStack;
      shuffle();
    }
    
    /**
     * Randomizes the order of the card stack
     */
    public void shuffle(){
        Stack<SceneCard> newCards = new Stack<SceneCard>();
        Random r = new Random();
        while(!cards.isEmpty()) {
            newCards.push(cards.remove(r.nextInt(cards.size())));
        }
        setCards(newCards);
    }
    
    /**
     * Places a card on each of the scenes
     * @param scenes: the array of scenes on th board
     */
    public void deal(Scene[] scenes){
        for(Scene s: scenes) {
            placeCard(s);
        }
    }
    
    /**
     * places a card on the given scene
     * @param scene: the scene for the card to be placed on
     */
    public void placeCard(Scene scene){
        scene.setCard(cards.pop());
    }
    
    /** 
     * @return: the stack containing the remaining scene cards
     */
    public Stack<SceneCard> getCards(){
        return cards;
    }
    
    /**
     * @param: the new stack of cards to replace the old stack
     */
    private void setCards(Stack<SceneCard> newCards){
        cards = newCards;
    }
}
