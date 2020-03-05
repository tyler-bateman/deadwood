/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadwood;

import java.util.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
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
        int numberOfPlayers;
//        int menuChoice;
//        int moveChoice;
//        int afterMoveChoice;
//        int roleChoice;
        Player players[];

        Deck deck = Deck.getInstance();
        Scanner sc = new Scanner(System.in);
        Stack<SceneCard> stackOfCards = new Stack<SceneCard>();
        Board board = Board.getInstance();
        CastingOffice castingOffice = CastingOffice.getInstance();
       

        Document boardDoc = null;
        Document cardsDoc = null;
        ParseXML parsing = new ParseXML();
        try {

            boardDoc = parsing.getDocFromFile("board.xml");
            board = parsing.buildBoard(boardDoc);
            parsing.buildAdjacentSpaces(boardDoc, board);

            cardsDoc = parsing.getDocFromFile("cards.xml");
            stackOfCards = parsing.buildDeck(cardsDoc);
            deck.initDeck(stackOfCards); // it is initialized and shuffled

        } catch (Exception e) {
            System.out.println("Error = " + e);
        }

        System.out.println("Welcome to Deadwood ! \n");
//        if (args.length != 0) {
            numberOfPlayers = Integer.parseInt(args[0]);
//        } else {
//            do {
//                System.out.println("Please input the number of players: ");
//                numberOfPlayers = sc.nextInt();
//
//            } while (!isPlayerNumberValid(numberOfPlayers));
//        }

        players = new Player[numberOfPlayers];
        ScoreManager.init(numberOfPlayers);

        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player(i, board.getTrailorsID());
            players[i] = player;

        }

        board.setPlayers(players);

        DayManager.getInstance().init(numberOfPlayers);

        
    }
}

   
