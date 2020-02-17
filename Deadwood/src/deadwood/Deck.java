package deadwood;
import java.util.Stack;
import java.util.Random;
/**
 * Class: Deck
 * Manages the stack of cards that haven't been used yet. Deals these cards to the scenes
 * @author nada & tyler
 */
public class Deck {
    private static Stack<SceneCard> cards;
    
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
    public static void shuffle(){
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
    public static void deal(Scene[] scenes){
        for(Scene s: scenes) {
            placeCard(s);
        }
    }
    
    /**
     * places a card on the given scene
     * @param scene: the scene for the card to be placed on
     */
    public static void placeCard(Scene scene){
        scene.setCard(cards.pop());
    }
    
    /** 
     * @return: the stack containing the remaining scene cards
     */
    public static Stack<SceneCard> getCards(){
        return cards;
    }
    
    /**
     * @param: the new stack of cards to replace the old stack
     */
    private static void setCards(Stack<SceneCard> newCards){
        cards = newCards;
    }
}
