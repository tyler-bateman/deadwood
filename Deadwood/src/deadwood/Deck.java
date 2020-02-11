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
    
    public Deck(){
        //TODO: Parse XML
    }
    
    public void shuffle(){
        Stack<SceneCard> newCards = new Stack<SceneCard>();
        Random r = new Random();
        while(!cards.isEmpty()) {
            newCards.push(cards.remove(r.nextInt(cards.size())));
        }
        setCards(newCards);
    }
    
    public void deal(){
        
    }
    
    public void placeCard(Scene scene){
        
    }
    
    public Stack<SceneCard> getCards(){
        return null;
    }
    
    public void setCards(Stack<SceneCard> newCards){
        
    }
}
