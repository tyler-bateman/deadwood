package deadwood;

/**
 * Deadwood
 * 
 * This is the main class for Deadwood.
 */

import java.io.IOException;
import java.util.Stack;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;

public class Deadwood {

    /**
     * Parses and initializes the deck and the board, then launches the GUI
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, IOException {
       
        ///////XML PARSING PREP///////
        Document boardDoc;
        Document cardsDoc;
        ParseXML parsing = new ParseXML();
        //////////////////////////////
        
        ///////SET UP BOARD AND SPACE ADJACENCY///////
        boardDoc = parsing.getDocFromFile("board.xml");
        Board board = parsing.buildBoard(boardDoc);
        parsing.buildAdjacentSpaces(boardDoc, board);
        //////////////////////////////////////////////
        
        ///////INITIALIZE CARDS AND DECK////////
        cardsDoc = parsing.getDocFromFile("cards.xml");
        Stack<SceneCard> stackOfCards = parsing.buildDeck(cardsDoc);
        Deck deck = Deck.getInstance();
        deck.initDeck(stackOfCards); 
        ////////////////////////////////////////

      
        //Launch the GUI. Fingers crossed
        LaunchView launchView = new LaunchView();
        /////////////////////////////////////////
    }
}

   
