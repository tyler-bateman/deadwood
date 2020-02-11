/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadwood;
import java.util.Stack;
import java.util.Random;
/**
 *
 * @author nada & tyler
 */
public class Deck {
    private Stack<SceneCard> cards;

    public void initDeck() {
      //TODO: Parse XML


      shuffle()
    }

    public static void shuffle(){
        Stack<SceneCard> newCards = new Stack<SceneCard>();
        Random r = new Random();
        while(!cards.isEmpty()) {
            newCards.push(cards.remove(r.nextInt(cards.size())));
        }
        setCards(newCards);
    }

    public static void deal(Scene[] scenes){
        for(Scene s: scenes {
            placeCard(s);
        }
    }

    public static void placeCard(Scene scene){
        scene.setCard(cards.pop());
    }

    public static Stack<SceneCard> getCards(){
        return cards;
    }

    private static void setCards(Stack<SceneCard> newCards){
        cards = newCards;
    }
}
