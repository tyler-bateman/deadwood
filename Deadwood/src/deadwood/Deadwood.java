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
        int dieRoll = 0;
        Player players[];
        CastingOffice castingOffice = new CastingOffice();
        Deck deck;
        Dice dice = new Dice();
        int cnt =1;
        Scene sceneMovedTo = new Scene();
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);
        Scanner sc4 = new Scanner(System.in);
        Scanner sc5 = new Scanner(System.in);
        Scanner sc6 = new Scanner(System.in);
        Stack<SceneCard> stackOfCards = new Stack<SceneCard>();
        Board board;
        
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
        numberOfPlayers = sc1.nextInt();
        players = new Player[numberOfPlayers];
        
        for(int i=0; i< numberOfPlayers; i++){
            Player player = new Player(i,"trailer");
            try{
            board.getScene(10).addPlayer(player);
            }catch(Exception e){
                e.printStackTrace();
            }
            players[i] = player;
            
        }
        
        board.setPlayers(players);
        deck.deal(board.getScenes());

        
        
        switch (numberOfPlayers){
            case 2:
            case 3:
                DayManager.setNumberOfDays(3);
                System.out.printf("You will play for %d days \n ", DayManager.getNumberOfDays());
                break;
            case 4:
                DayManager.setNumberOfDays(4);
                System.out.printf("You will play for %d days \n ", DayManager.getNumberOfDays());
                break;
            case 5: 
                DayManager.setNumberOfDays(4);                
                for (int i=0; i< numberOfPlayers; i++){
                    board.getPlayer(i).setCredits(2);
                }
                System.out.printf("You will play for %d days and every player will start with 2 credits \n", DayManager.getNumberOfDays());
                break;
            case 6: 
                DayManager.setNumberOfDays(4);
                for (int i=0; i< numberOfPlayers; i++){
                    board.getPlayer(i).setCredits(4);
                }
                System.out.printf("You will play for %d days and every player will start with 4 credits \n", DayManager.getNumberOfDays());
                break;
            case 7:
            case 8:
                DayManager.setNumberOfDays(4);
                for (int i=0; i< numberOfPlayers; i++){
                    board.getPlayer(i).setRank(2);
                }
                System.out.printf("You will play for %d days and every player will start with a rank of 2 \n", DayManager.getNumberOfDays());
                break;
            default: 
                System.out.println("Please enter a number between 2 and 8");
        }
        TurnManager.init(players);
        
        try{
            for(int i=0;i<numberOfPlayers; i++){
                System.out.println("\nPlayer "+board.getPlayer(i).getID()+" please input a number from the menu\n");
                System.out.println("1. Move");
                System.out.println("2. Act");
                System.out.println("3. Rehearse");
                System.out.println("4. Upgrade");
                System.out.println("5. End turn\n");
                menuChoice = sc1.nextInt();
                
                switch(menuChoice){
                    case 1:
                        if(board.getPlayer(i).getRole() == null ){
                            System.out.println("Where to ?\n");
                            for (int j=0; j <board.getScenes().length; j++){
                                if(board.getPlayer(i).getLocation().equals(board.getScene(j).getName())){
                                    Iterator<Scene> iterator = board.getScene(j).getAdjacentSpaces().iterator();
                                    while (iterator.hasNext()) {                                      
                                        System.out.println(cnt +". " +iterator.next().getName()); 
                                        cnt++;
                                    }
                                    cnt =1;
                                    int moveChoice = sc2.nextInt();
                                    board.getPlayer(i).setLocation(board.getScene(j).getAdjacentSpaces().get(moveChoice-1).getName());
                                    board.getScene(j).removePlayer(board.getPlayer(i));
                                    sceneMovedTo = board.getScene(j).getAdjacentSpaces().get(moveChoice-1);
                                    board.getScene(sceneMovedTo.ID).addPlayer(board.getPlayer(i));
                                    //board.getScene(sceneMovedTo.ID).getCard().flip();

                                    
                                    System.out.println("Player "+board.getPlayer(i).getID()+"has moved from "+board.getScene(j).getName()+" to " +board.getScene(sceneMovedTo.ID).getName()+"\n");  
                                    break;
                                }
                            }
                                    System.out.println("You can still take a role, or end your turn");
                                    System.out.println("1. Take a role");
                                    System.out.println("2. End Turn");
                                    int afterMoveChoice = sc3.nextInt();
                                    switch(afterMoveChoice){
                                        case 1:
                                            if(board.getScene(sceneMovedTo.ID).getRemainingShots() == 0){
                                                System.out.println("You cannot choose that role, its scene has already wrapped !");
                                            }
                                            else{
                                            if(board.getPlayer(i).getRole() == null ){
                                            System.out.println("\nWhat role ?");
                                            Iterator<Role> it1 = board.getScene(sceneMovedTo.ID).getOffCardRoles().iterator();              
                                            Iterator<Role> it2= board.getScene(sceneMovedTo.ID).getCard().getRoles().iterator();

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
                                            int roleChoice = sc4.nextInt();
                                            

                                            
                                            
                                            if(roleChoice >= board.getScene(sceneMovedTo.ID).getCard().getRoles().size()){
                                                roleChoice = roleChoice % board.getScene(sceneMovedTo.ID).getCard().getRoles().size();
                                                if((board.getScene(sceneMovedTo.ID).getCard().getRoles().get(roleChoice).requestRole(board.getPlayer(i))) == true){
                                                    board.getPlayer(i).setRole(board.getScene(sceneMovedTo.ID).getCard().getRoles().get(roleChoice));
                                                    System.out.println("Player " +board.getPlayer(i).getID()+ "has taken the role "+board.getPlayer(i).getRole().getName()); 
                                                }
                                                else{
                                                    System.out.println("You cannot take that starring role, please choose another role"); 
                                                }
                                            }
                                            else{                                               
                                            
                                                if(board.getPlayer(i).getRole()== null){
                                                    if((board.getScene(sceneMovedTo.ID).getOffCardRoles().get(roleChoice-1).requestRole(board.getPlayer(i))) == true){
                                                        board.getPlayer(i).setRole(board.getScene(sceneMovedTo.ID).getOffCardRoles().get(roleChoice-1));
                                                        System.out.println("Player " +board.getPlayer(i).getID()+ "has taken the role "+board.getPlayer(i).getRole().getName()); 
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
                            }      

                        else{
                            System.out.println("You cannot move while working on a role");
                        }
                        break;
                    
                    case 2:
                      if(board.getPlayer(i).getRole()!=null){
                        dice.roll(1);
                        dieRoll = dice.getDice().getFirst() + board.getPlayer(i).getRehearsalChips();
                        for(int j=0; j<board.getScenes().length; j++){
                            if(board.getPlayer(i).getLocation().equals(board.getScene(j).getName())){
                                if(dieRoll >= board.getScene(j).getCard().getBudget()){
                                    if( board.getScene(j).getOffCardRoles().contains(board.getPlayer(i).getRole())){
                                        System.out.println("You rolled a " +dieRoll+ " Acting off card succeeded !");
                                        board.getScene(j).setRemainingShots((board.getScene(j).getRemainingShots())-1);
                                        Banker.giveDollars(board.getPlayer(i), 1);
                                        Banker.giveCredits(board.getPlayer(i), 1);
                                    }
                                    else{
                                        System.out.println("You rolled a " +dieRoll+ " Acting on card succeeded !");
                                        board.getScene(j).setRemainingShots((board.getScene(j).getRemainingShots())-1);
                                        Banker.giveCredits(board.getPlayer(i), 2);
                                    }
                                    }
                                else{
                                    if( board.getScene(j).getOffCardRoles().contains(board.getPlayer(i).getRole())){
                                        System.out.println("You rolled a " +dieRoll+ " Acting off card failed !");
                                        Banker.giveDollars(board.getPlayer(i), 1);
                                        
                                    }
                                    else{
                                        System.out.println("You rolled a " +dieRoll+ " Acting on card failed !");
                                    }
                                }
                                if(board.getScene(j).getRemainingShots() == 0){
                                    System.out.println("No more shots ! \nThe scene is wrapped ! ");
                                    board.getScene(j).wrap();
                                }
                            }
                        }
                        dice.getDice().clear();
                      }
                      else{
                          System.out.println("You don't have a role to act on !");
                      }
                        break;
                    
                    case 3:
                        if(board.getPlayer(i).getRole()!= null){                  
                            for(int j=0; j<board.getScenes().length; j++){
                                if(board.getPlayer(i).getLocation().equals(board.getScene(j).getName())){
                                    if(board.getPlayer(i).getRehearsalChips() > board.getScene(j).getCard().getBudget()){
                                    System.out.println("Success is guaranteed ! Act !");                         
                                    }
                                    else{
                                        System.out.println("+1 added to all your die rolls on this role !");
                                        board.getPlayer(i).setRehearsal(1);
                                    }
                                }
                            }
                        }
                        else{
                            System.out.println("You don't have a role to rehearse !");
                        }
                            
                        break;
                    
                    case 4:
                        if(board.getPlayer(i).getLocation().equals("office")){
                                System.out.println("Please select which rank you wish to upgrade to: \n");
                                System.out.println("1. To upgrade to Rank 2 you need "+castingOffice.getDollarPrice(2)+ " dollars or "+ castingOffice.getCreditPrice(2)+" credits");
                                System.out.println("2. To upgrade to Rank 3 you need "+castingOffice.getDollarPrice(3)+ " dollars or "+ castingOffice.getCreditPrice(3)+" credits");
                                System.out.println("3. To upgrade to Rank 4 you need "+castingOffice.getDollarPrice(4)+ " dollars or "+ castingOffice.getCreditPrice(4)+" credits");
                                System.out.println("4. To upgrade to Rank 5 you need "+castingOffice.getDollarPrice(5)+ " dollars or "+ castingOffice.getCreditPrice(5)+" credits");
                                System.out.println("5. To upgrade to Rank 6 you need "+castingOffice.getDollarPrice(6)+ " dollars or "+ castingOffice.getCreditPrice(6)+" credits");
                                int rankChoice = sc5.nextInt();
                                System.out.println("Dollars or Credits ?");
                                System.out.println("1. Dollars");
                                System.out.println("2. Credits");
                                int paymentChoice = sc6.nextInt();
                                
                                if(paymentChoice == 1){
                                    if(castingOffice.purchaseRank(board.getPlayer(i), (rankChoice+1), CurrencyType.DOLLARS)){
                                        System.out.println("Player "+board.getPlayer(i).getID()+ " has upgraded rank to "+board.getPlayer(i).getRank());
                                    }
                                    else{
                                        System.out.println("You cannot upgrade to that rank ");
                                    }
                                }
                                else if(paymentChoice ==2){
                                    if(castingOffice.purchaseRank(board.getPlayer(i), (rankChoice+1), CurrencyType.CREDITS)){
                                        System.out.println("Player "+board.getPlayer(i).getID()+ " has upgraded rank to "+board.getPlayer(i).getRank());
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
                if(DayManager.checkForDayEnd(board)){
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
}




            /*for(int i = 0; i<board.getScenes().length; i++){
                System.out.println(board.getScene(i).getName());
                System.out.println(board.getScene(i).ID);
                System.out.println(board.getScene(i).getTotalShots());
                LinkedList<Role> roles = board.getScene(i).getOffCardRoles();
                LinkedList<String> neighbors = board.getScene(i).getAdjacentSpaces();
                for(int j=0; j<roles.size() ; j++){
                    Role role = (Role) roles.get(j);
                    System.out.println(role.getName());
                    System.out.println(role.getRank());
                    System.out.println(role.getLine());
                }
                for(int j=0; j<neighbors.size() ; j++){
                    System.out.println(neighbors.get(j));
                }                
                System.out.println("\n\n");


            for(int i=0; i<board.getScenes().length;i++){
                System.out.println(board.getScene(i).ID);
            }
            }


            for(int i=0; i<board.getScenes().length;i++){
                System.out.println(board.getScene(i).getName()+"\n");
                Iterator<Scene> iterator = board.getScene(i).getAdjacentSpaces().iterator();
                while (iterator.hasNext()) {                                      
                    System.out.println(iterator.next().getName()); 
                } 
                System.out.println("\n");
            }*/