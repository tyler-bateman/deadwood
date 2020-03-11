/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadwood;

import java.io.IOException;
import java.util.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;

/**
 *
 * @author Nada
 *//**
 *
 * @author Nada
 */
public class Deadwood {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, IOException {
       
        ///////XML PARSING PREP///////
        Document boardDoc = null;
        Document cardsDoc = null;
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
      /*  
        ///////INITIALIZE PLAYERS///////
        int numberOfPlayers;                            
        if(args.length > 0 ) {                          
            numberOfPlayers = Integer.parseInt(args[0]);
        } else {                                        
            numberOfPlayers = 2;                        
        }
        Player[] players = new Player[numberOfPlayers];
        
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player(i, board.getTrailorsID());
            players[i] = player;
        }
        ////////////////////////////////
        */
      
        //Launch the GUI. Fingers crossed
        LaunchView launchView = new LaunchView();
    }
}

   
