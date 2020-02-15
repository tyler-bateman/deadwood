/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadwood;

import java.util.Stack;
import org.w3c.dom.Document;

/**
 *
 * @author Nada
 */
public class Deadwood {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Stack<SceneCard> deck = new Stack<SceneCard>();
        Board board = new Board();
        
        Document boardDoc = null;
        Document cardsDoc = null;
        ParseXML parsing = new ParseXML();
        try{
      
            boardDoc = parsing.getDocFromFile("board.xml");
            board = parsing.buildBoard(boardDoc);
        
            cardsDoc = parsing.getDocFromFile("cards.xml");
            deck = parsing.buildDeck(cardsDoc);       
      
        }catch (Exception e){
        System.out.println("Error = "+e);   
        }
    }   
}
