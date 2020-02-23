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
        int menuChoice;
        int moveChoice;
        int afterMoveChoice;
        int roleChoice;
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
        try {
            System.out.println("Welcome to Deadwood ! \n");
            if (args.length != 0) {
                numberOfPlayers = Integer.parseInt(args[0]);
            } else {
                do {
                    System.out.println("Please input the number of players: ");
                    numberOfPlayers = sc.nextInt();

                } while (!isPlayerNumberValid(numberOfPlayers));
            }

            players = new Player[numberOfPlayers];

            for (int i = 0; i < numberOfPlayers; i++) {
                Player player = new Player(i, board.getTrailorsID());
                players[i] = player;

            }

            board.setPlayers(players);

            DayManager.init(numberOfPlayers);

            setStartUpParameters(numberOfPlayers);

            for (int i = 0; i < numberOfPlayers; i++) { //Player turn order loop
                Player currPlayer = board.getPlayer(i);
                boolean invalidChoice = false;
                do {
                    System.out.println("\nPlayer " + (currPlayer.getID() + 1) + " please input a number from the menu\n");
                    do {
                        mainMenu();
                        menuChoice = sc.nextInt();
                    } while (!isMenuChoiceValid(menuChoice));

                    switch (menuChoice) {
                        case 1: //Move
                            if (currPlayer.getRole() == null) {
                                invalidChoice = false;
                                System.out.println("Where to ?\n");
                                Space currSpace = board.getSpace(currPlayer.getLocation());
                                int AdjacentSpacesSize = currSpace.getAdjacentSpaces().size();
                                do {
                                    displayAdjacentSpaces(currSpace);
                                    moveChoice = sc.nextInt();

                                } while (!isMoveChoiceValid(moveChoice, AdjacentSpacesSize));

                                currSpace.moveTo(currPlayer, (moveChoice - 1));

                                Space newSpace = currSpace.getAdjacentSpaces().get(moveChoice - 1);
                                System.out.println("Player " + (currPlayer.getID() + 1) + " has moved from " + currSpace.getName() + " to " + newSpace.getName() + "\n");
                                if (newSpace.getClass().equals((new Scene()).getClass())) {

                                    Scene currScene = board.getScene(newSpace.ID);
                                    do {
                                        afterMoveMenu();
                                        afterMoveChoice = sc.nextInt();
                                    } while (!isAfterMoveChoiceValid(afterMoveChoice));

                                    switch (afterMoveChoice) {
                                        case 1:
                                            if (currScene == null || currScene.getRemainingShots() == 0) {
                                                System.out.println("There are no roles to take, your turn has ended.");
                                            } else {

                                                int extraRolesListSize = currScene.getOffCardRoles().size();
                                                int starringRolesListSize = currScene.getCard().getRoles().size();
                                                do {
                                                    if (takeARole(currScene, currPlayer, extraRolesListSize, starringRolesListSize, sc)) {
                                                        break;
                                                    }
                                                } while (true);

                                            }

                                            break;

                                        case 2:
                                            System.out.println("Your turn has ended\n");
                                            break;
                                    }
                                }
                            } else {
                                System.out.println("You cannot move while working on a role");
                                invalidChoice = true;
                            }
                            break;
                        case 2: // Take a role
                            if (board.getSpace(currPlayer.getLocation()).getClass().equals((new Scene()).getClass())) {
                                Scene currScene = board.getScene(currPlayer.getLocation());
                                if (currScene.getRemainingShots() == 0) {
                                    invalidChoice = true;
                                    System.out.println("This scene has wrapped, choose something else.");
                                } else {
                                    if (currPlayer.getRole() != null) {
                                        System.out.println("You already have a role. You cannot take another one !");
                                        invalidChoice = true;
                                    } else {
                                        int extraRolesListSize = currScene.getOffCardRoles().size();
                                        int starringRolesListSize = currScene.getCard().getRoles().size();
                                        takeARole(currScene, currPlayer, extraRolesListSize, starringRolesListSize, sc);
                                        invalidChoice = false;
                                    }
                                }
                            } else {
                                System.out.println("You can only take a role if you're on a scene");
                                invalidChoice = true;
                            }
                            break;

                        case 3: //Act

                            if (currPlayer.getRole() == null) {
                                System.out.println("You cannot act unless you are working on a role.");
                                invalidChoice = true;
                            } else {
                                invalidChoice = false;
                                if (board.getScene(currPlayer.getLocation()).requestActAttempt(currPlayer)) {
                                    System.out.println("You have successfully acted! You now have " + currPlayer.getDollars() + " dollars and " + currPlayer.getCredits() + " credits");
                                    System.out.println("This scene has " + board.getScene(currPlayer.getLocation()).getRemainingShots() + " shots remaining.");
                                } else {
                                    System.out.println("You have failed to act, you worthless little worm. Out of my sight.");
                                }
                            }
                            break;

                        case 4: //Rehearse

                            if (currPlayer.getRole() != null) {
                                if (board.getScene(currPlayer.getLocation()).requestRehearsal(currPlayer)) {
                                    System.out.println("You have successfully rehearsed ! You now have +" + currPlayer.getRehearsalChips() + " to your die rolls.");
                                    invalidChoice = false;
                                } else {
                                    System.out.println("You already have enough rehearsals to guarantee success! Act !");
                                    invalidChoice = true;
                                }
                            } else {
                                System.out.println("You can only rehearse if you're on a role");
                                invalidChoice = true;
                            }
                            break;

                        case 5: //Upgrade
                            if (board.getSpace(currPlayer.getLocation()).getName().equals("office")) {
                                upgradeMenu(castingOffice);
                                int rankChoice = sc.nextInt();
                                paymentChoiceMenu();
                                int paymentChoice = sc.nextInt();

                                if (paymentChoice == 1) {
                                    if (castingOffice.purchaseRank(currPlayer, (rankChoice + 1), CurrencyType.DOLLARS)) {
                                        System.out.println("Player " + (currPlayer.getID() + 1) + " has upgraded rank to " + currPlayer.getRank());
                                        invalidChoice = false;
                                    } else {
                                        System.out.println("You cannot upgrade to that rank ");
                                        invalidChoice = true;
                                    }
                                } else if (paymentChoice == 2) {
                                    if (castingOffice.purchaseRank(currPlayer, (rankChoice + 1), CurrencyType.CREDITS)) {
                                        System.out.println("Player " + (currPlayer.getID() + 1) + " has upgraded rank to " + currPlayer.getRank());
                                        invalidChoice = false;
                                    } else {
                                        invalidChoice = true;
                                        System.out.println("You cannot upgrade to that rank ");
                                    }
                                }
                            } else {
                                System.out.println("You can only upgrade at the Casting Office");
                                invalidChoice = true;
                            }
                            break;

                        case 6:

                            System.out.println("Your turn has ended");
                            invalidChoice = false;
                            break;
                        case 7:

                            System.out.println("This is player " + (currPlayer.getID() + 1) + " You have " + currPlayer.getDollars() + " dollars and " + currPlayer.getCredits() + " credits. You are currently in " + board.getSpace(currPlayer.getLocation()).getName());
                            invalidChoice = true;
                            break;

                        default:
                            System.exit(0);
                    }
                } while (invalidChoice);
                if (DayManager.checkForDayEnd()) {
                    System.out.println("\n\n There is only 1 scene left. The day has ended.\n Starting day " + DayManager.getCurrentDay());
                }
                if (i == (numberOfPlayers - 1)) {
                    i = -1;
                }

                System.out.println("\n");

            }

        } catch (InputMismatchException e) {
            System.out.println("Congratulations, you crashed the game ! Integers only please !");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Declares one or more players the winner and terminates the program
     *
     * @param winners the list of winning players
     */
    public static void declareWinner(LinkedList<Integer> winners) {
        System.out.println("The game is over! \nThe winners are:");
        for (int p : winners) {
            System.out.println("Player " + p);
        }
        System.exit(0);
    }

    /**
     * Displays the main playing menu
     */
    public static void mainMenu() {

        System.out.println("1. Move");
        System.out.println("2. Take a role");
        System.out.println("3. Act");
        System.out.println("4. Rehearse");
        System.out.println("5. Upgrade");
        System.out.println("6. End turn");
        System.out.println("7. Display player information");
        //System.out.println("8. Display all players' locations");
    }

    /**
     * Displays the menu of adjacent spaces
     *
     * @param space the space that has the adjacent spaces
     */
    public static void displayAdjacentSpaces(Space space) {
        int cnt = 1;

        for (Space adjacentSpace : space.getAdjacentSpaces()) {
            System.out.println(cnt + ". " + adjacentSpace.getName());
            cnt++;
        }

//        Iterator<Space> iterator = space.getAdjacentSpaces().iterator();
//        while (iterator.hasNext()) {
//            System.out.println(cnt + ". " + iterator.next().getName());
//            cnt++;
//        }
    }

    /**
     * Displays the menu of scene roles
     *
     * @param scene the scene that has the on-card and off-card roles
     */
    public static void displaySceneRoles(Scene scene) {
        int cnt = 1;
        System.out.println("\nWhat role ?\n");
        System.out.println("Extra roles: ");
        for (int i = 0; i < scene.getOffCardRoles().size(); i++) {
            System.out.println((i + 1) + ". " + scene.getOffCardRoles().get(i).getName() + " | Rank: " + scene.getOffCardRoles().get(i).getRank() + (scene.getOffCardRoles().get(i).isRoleTaken() ? " *Taken*" : ""));
            cnt++;
        }
        System.out.println("Starring roles: ");
        for (int j = 0; j < scene.getCard().getRoles().size(); j++) {
            System.out.println(cnt + ". " + scene.getCard().getRoles().get(j).getName() + " | Rank: " + scene.getCard().getRoles().get(j).getRank() + (scene.getCard().getRoles().get(j).isRoleTaken() ? " *Taken*" : ""));
            cnt++;
        }

    }

    /**
     * Displays the player's choices after moving
     */
    public static void afterMoveMenu() {

        System.out.println("You can still take a role, or end your turn");
        System.out.println("1. Take a role");
        System.out.println("2. End Turn");
    }

    /**
     * Displays the upgrades menu
     *
     * @param castingOffice
     */
    public static void upgradeMenu(CastingOffice castingOffice) {
        System.out.println("Please select which rank you wish to upgrade to: \n");
        System.out.println("1. To upgrade to Rank 2 you need " + castingOffice.getDollarPrice(2) + " dollars or " + castingOffice.getCreditPrice(2) + " credits");
        System.out.println("2. To upgrade to Rank 3 you need " + castingOffice.getDollarPrice(3) + " dollars or " + castingOffice.getCreditPrice(3) + " credits");
        System.out.println("3. To upgrade to Rank 4 you need " + castingOffice.getDollarPrice(4) + " dollars or " + castingOffice.getCreditPrice(4) + " credits");
        System.out.println("4. To upgrade to Rank 5 you need " + castingOffice.getDollarPrice(5) + " dollars or " + castingOffice.getCreditPrice(5) + " credits");
        System.out.println("5. To upgrade to Rank 6 you need " + castingOffice.getDollarPrice(6) + " dollars or " + castingOffice.getCreditPrice(6) + " credits");
    }

    /**
     * Displays the currency choices for payment
     */
    public static void paymentChoiceMenu() {
        System.out.println("Dollars or Credits ?");
        System.out.println("1. Dollars");
        System.out.println("2. Credits");
    }

    public static boolean isPlayerNumberValid(int num) {
        if (num < 2 || num > 8) {
            System.out.println("Invalid input. The game can only support between 2 and 8 players");
            return false;
        } else {
            return true;
        }
    }

    public static boolean isMenuChoiceValid(int choice) {
        if (choice < 1 || choice > 8) {
            System.out.println("Invalid input. Please enter a number between 1 and 8");
            return false;
        } else {
            return true;
        }
    }

    public static boolean isMoveChoiceValid(int choice, int size) {
        if (choice < 1 || choice > size) {
            System.out.println("Invalid input. Please enter a number between 1 and " + size);
            return false;
        } else {
            return true;
        }
    }

    public static boolean isAfterMoveChoiceValid(int choice) {
        if (choice < 1 || choice > 2) {
            System.out.println("Invalid input. Please enter a number between 1 and 2");
            return false;
        } else {
            return true;
        }
    }

    public static boolean isRoleChoiceValid(int choice, int size1, int size2) {
        if (choice < 1 || choice > (size1 + size2)) {
            System.out.println("Stop making me handle your errors :( please input a number between 1 and " + (size1 + size2));
            return false;
        } else {
            return true;
        }
    }

    public static void setStartUpParameters(int num) {

        Board board = Board.getInstance();
        switch (num) {
            case 2:
            case 3:
                System.out.printf("You will play for %d days \n ", DayManager.getNumberOfDays());
                break;
            case 4:
                System.out.printf("You will play for %d days \n ", DayManager.getNumberOfDays());
                break;
            case 5:
                for (int i = 0; i < num; i++) {
                    board.getPlayer(i).setCredits(2);
                }
                System.out.printf("You will play for %d days and every player will start with 2 credits \n", DayManager.getNumberOfDays());
                break;
            case 6:
                for (int i = 0; i < num; i++) {
                    board.getPlayer(i).setCredits(4);
                }
                System.out.printf("You will play for %d days and every player will start with 4 credits \n", DayManager.getNumberOfDays());
                break;
            case 7:
            case 8:
                for (int i = 0; i < num; i++) {
                    board.getPlayer(i).setRank(2);
                }
                System.out.printf("You will play for %d days and every player will start with a rank of 2 \n", DayManager.getNumberOfDays());
                break;
            default:
                System.out.println("Please enter a number between 2 and 8");
        }
    }

    /**
     * Prints a message to the console
     *
     * @param msg the message to be printed
     */
    public static void sendMessage(String msg) {
        System.out.println(msg);
    }

    public static boolean takeARole(Scene scene, Player player, int size1, int size2, Scanner sc) {

        int roleChoice;
        System.out.println("You are currently in the scene " + scene.getName());

        do {
            displaySceneRoles(scene);
            System.out.println("\n" + (size1 + size2 + 1) + ". End turn");
            roleChoice = sc.nextInt();
            if (roleChoice == (size1 + size2 + 1)) {
                return true;
            }
        } while (!isRoleChoiceValid(roleChoice, size1, size2));
        if (roleChoice > scene.getOffCardRoles().size()) {
            roleChoice = roleChoice - scene.getOffCardRoles().size() - 1;
            if (scene.getCard().getRoles().get(roleChoice).requestRole(player)) {
                System.out.println("You have successfully claimed the role " + player.getRole().getName());
                return true;
            } else {
                System.out.println("You cannot take that role ! Choose another role");
                return false;
            }
        } else {

            if (player.getRole() == null) {
                if ((scene.getOffCardRoles().get(roleChoice - 1).requestRole(player))) {
                    System.out.println("Player " + (player.getID() + 1) + " has taken the role " + player.getRole().getName());
                    return true;
                } else {
                    System.out.println("You cannot take that role ! Choose another role");
                    return false;
                }
            }
        }
        return true;
    }

}
