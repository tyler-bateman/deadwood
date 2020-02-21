/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadwood;

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
        int menuChoice;
        Player players[];
        CastingOffice castingOffice = new CastingOffice();
        Deck deck = Deck.getInstance();
        Dice dice = new Dice();
        int cnt =1;
        Scene sceneMovedTo = new Scene();
        Scanner sc = new Scanner(System.in);
        Stack<SceneCard> stackOfCards = new Stack<SceneCard>();
        Board board = Board.getInstance();
        
        Document boardDoc = null;
        Document cardsDoc = null;
        ParseXML parsing = new ParseXML();
        try{
      
            boardDoc = parsing.getDocFromFile("board.xml");
            board = parsing.buildBoard(boardDoc);
            parsing.buildAdjacentSpaces(boardDoc, board);

            cardsDoc = parsing.getDocFromFile("cards.xml");
            stackOfCards = parsing.buildDeck(cardsDoc);    
            deck.initDeck(stackOfCards); // it is initialized and shuffled
            
        }catch (Exception e){
        System.out.println("Error = "+e);   
        }
        
        System.out.println("Welcome to Deadwood ! \n\t Please input the number of players: ");
        numberOfPlayers = sc.nextInt();
        
        
        players = new Player[numberOfPlayers];
        
        for(int i=0; i< numberOfPlayers; i++){
            Player player = new Player(i,0);
            players[i] = player;
            
        }
        
        board.setPlayers(players);
        
        DayManager.init(numberOfPlayers);
        
        switch (numberOfPlayers){
            case 2:
            case 3:
                System.out.printf("You will play for %d days \n ", DayManager.getNumberOfDays());
                break;
            case 4:
                System.out.printf("You will play for %d days \n ", DayManager.getNumberOfDays());
                break;
            case 5:        
                for (int i=0; i< numberOfPlayers; i++){
                    board.getPlayer(i).setCredits(2);
                }
                System.out.printf("You will play for %d days and every player will start with 2 credits \n", DayManager.getNumberOfDays());
                break;
            case 6: 
                for (int i=0; i< numberOfPlayers; i++){
                    board.getPlayer(i).setCredits(4);
                }
                System.out.printf("You will play for %d days and every player will start with 4 credits \n", DayManager.getNumberOfDays());
                break;
            case 7:
            case 8:
                for (int i=0; i< numberOfPlayers; i++){
                    board.getPlayer(i).setRank(2);
                }
                System.out.printf("You will play for %d days and every player will start with a rank of 2 \n", DayManager.getNumberOfDays());
                break;
            default: 
                System.out.println("Please enter a number between 2 and 8");
        }
        

        
        try{
            for(int i=0;i<numberOfPlayers; i++){ //Player turn order loop
                
                Player currPlayer = board.getPlayer(i);
                System.out.println("\nPlayer " + currPlayer.getID()+" please input a number from the menu\n");
                System.out.println("1. Move");
                System.out.println("2. Act");
                System.out.println("3. Rehearse");
                System.out.println("4. Upgrade");
                System.out.println("5. End turn\n");
                menuChoice = sc.nextInt();
                
                switch(menuChoice){
                    case 1:
                        if(currPlayer.getRole() == null){
                            System.out.println("Where to ?\n");
                            Space currSpace = board.getSpace(currPlayer.getLocation());
                            Iterator<Space> iterator = currSpace.getAdjacentSpaces().iterator();
                            while (iterator.hasNext()) {                                      
                                System.out.println(cnt +". " +iterator.next().getName()); 
                                cnt++;
                            }
                            cnt =1;
                            int moveChoice = sc.nextInt();
                            board.getSpace(moveChoice - 1).requestMove(currPlayer, board.getSpaces());

                            Space newSpace = board.getSpace(currPlayer.getLocation());
                            System.out.println("Player "+currPlayer.getID()+"has moved from "+currSpace.getName()+" to " +currSpace.getName()+"\n");  

                            if(newSpace.getClass().toString().equals("Scene")) {
                                Scene currScene = board.getScene(sceneMovedTo.ID);
                                System.out.println("You can still take a role, or end your turn");
                                System.out.println("1. Take a role");
                                System.out.println("2. End Turn");
                                int afterMoveChoice = sc.nextInt();
                                switch(afterMoveChoice){
                                    case 1:
                                        if(currScene.getRemainingShots() == 0){
                                            System.out.println("You cannot choose that role, its scene has already wrapped !");
                                        }
                                        else{
                                            if(currPlayer.getRole() == null ){
                                                System.out.println("\nWhat role ?");
                                                Iterator<Role> it1 = currScene.getOffCardRoles().iterator();              
                                                Iterator<Role> it2= currScene.getCard().getRoles().iterator();

                                                System.out.println("Extra roles: ");
                                                while (it1.hasNext()){
                                                    System.out.println(cnt +". " +it1.next().getName());
                                                    cnt++;
                                                }
                                                System.out.println("\nStarring roles: ");                                    
                                                while (it2.hasNext()){
                                                    System.out.println(cnt +". " +it2.next().getName());
                                                    cnt++;
                                                }
                                                cnt=1;
                                                int roleChoice = sc.nextInt();




                                                if(roleChoice >= currScene.getCard().getRoles().size()){
                                                    roleChoice = roleChoice % currScene.getCard().getRoles().size();
                                                    if(currScene.getCard().getRoles().get(roleChoice).requestRole(currPlayer)) {
                                                        System.out.println("You have successfully claimed the role " + currPlayer.getRole().getName());
                                                    } else {
                                                        System.out.println("You cannot take that role");
                                                    }
                                                }
                                                else{                                               

                                                    if(currPlayer.getRole()== null){
                                                        if((currScene.getOffCardRoles().get(roleChoice-1).requestRole(currPlayer))){
                                                            System.out.println("Player " +currPlayer.getID()+ "has taken the role "+currPlayer.getRole().getName()); 
                                                        }
                                                        else{
                                                            System.out.println("You cannot take that extra role, please choose another role");     
                                                        }
                                                    }
                                                }
                                            }

                                            else{
                                              System.out.println("You cannot move while working on a role");
                                            }
                                        }

                                        break;
                                    case 2: 
                                        System.out.println("Your turn has ended\n");
                                        break;
                                }           
                            } else if(newSpace.getName().equals("office")){
                                //TODO give option to upgrade
                            } else {
                                System.out.println("Your turn has ended\n");
                                break;
                            }
                        }      

                        else{
                            System.out.println("You cannot move while working on a role");
                        }
                        break;
                    
                    case 2: //Act
                        
                        if(currPlayer.getRole() == null) {
                            System.out.println("You cannot act unless you are working on a role.");
                        } else {
                            if(board.getScene(currPlayer.getLocation()).requestActAttempt(currPlayer)) {
                                System.out.println("You have successfully acted!");
                            } else {
                                System.out.println("You have failed to act, you worthless little worm. I hope you die.");
                            }
                        }
                    
                    case 3: //Rehearse
                        Space currSpace = board.getSpace(currPlayer.getLocation());
                        if(currPlayer.getRole()!= null) {
                            
                            if(board.getScene(currPlayer.getLocation()).requestRehearsal(currPlayer) ){ 
                                System.out.println("You have successfully rehearsed");
                            }
                            else{
                                System.out.println("You already have enough rehearsals to guarantee success!");
                            }
                        } else {
                            System.out.println("You can only rehearse if you're on a role");
                        }
                        break;
                    
                    case 4:
                        if(board.getSpace(currPlayer.getLocation()).equals("office")){
                                System.out.println("Please select which rank you wish to upgrade to: \n");
                                System.out.println("1. To upgrade to Rank 2 you need "+castingOffice.getDollarPrice(2)+ " dollars or "+ castingOffice.getCreditPrice(2)+" credits");
                                System.out.println("2. To upgrade to Rank 3 you need "+castingOffice.getDollarPrice(3)+ " dollars or "+ castingOffice.getCreditPrice(3)+" credits");
                                System.out.println("3. To upgrade to Rank 4 you need "+castingOffice.getDollarPrice(4)+ " dollars or "+ castingOffice.getCreditPrice(4)+" credits");
                                System.out.println("4. To upgrade to Rank 5 you need "+castingOffice.getDollarPrice(5)+ " dollars or "+ castingOffice.getCreditPrice(5)+" credits");
                                System.out.println("5. To upgrade to Rank 6 you need "+castingOffice.getDollarPrice(6)+ " dollars or "+ castingOffice.getCreditPrice(6)+" credits");
                                int rankChoice = sc.nextInt();
                                System.out.println("Dollars or Credits ?");
                                System.out.println("1. Dollars");
                                System.out.println("2. Credits");
                                int paymentChoice = sc.nextInt();
                                
                                if(paymentChoice == 1){
                                    if(castingOffice.purchaseRank(currPlayer, (rankChoice+1), CurrencyType.DOLLARS)){
                                        System.out.println("Player "+currPlayer.getID()+ " has upgraded rank to "+currPlayer.getRank());
                                    }
                                    else{
                                        System.out.println("You cannot upgrade to that rank ");
                                    }
                                }
                                else if(paymentChoice ==2){
                                    if(castingOffice.purchaseRank(currPlayer, (rankChoice+1), CurrencyType.CREDITS)){
                                        System.out.println("Player "+currPlayer.getID()+ " has upgraded rank to "+currPlayer.getRank());
                                    }
                                    else{
                                        System.out.println("You cannot upgrade to that rank ");
                                    }
                                }
                        }
                        else{
                            System.out.println("You can only upgrade at the Casting Office");
                        }
                        break;
                        
                    case 5:
                        System.out.println("Your turn has ended");
                    default:
                        System.exit(0);
                }
                /*if(DayManager.checkForDayEnd(board)){
                    System.out.println("day "+(DayManager.getCurrentDay()-1)+ " is over. Starting day "+ DayManager.getCurrentDay());
                }*/
                if(DayManager.checkForDayEnd()){
                    System.out.println("\n\n There is only 1 scene left. The day has ended.\n Starting day "+ DayManager.getCurrentDay());
                }
                if(i== (numberOfPlayers-1)){
                    i =-1;
                }
                
                System.out.println("\n");

            }
            }catch(Exception e){
              e.printStackTrace();
                 }

    }
    
    /**
     * Declares one or more players the winner and terminates the program
     * @param winners the list of winning players
     */
    public static void declareWinner(LinkedList<Integer> winners) {
        System.out.println("The game is over! \nThe winners are:");
        for(int p : winners) {
            System.out.println("Player " + p);
        }
        System.exit(0);
    }
}
