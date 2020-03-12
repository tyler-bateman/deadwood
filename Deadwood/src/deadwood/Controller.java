package deadwood;

/**
 *
 *
 * Implements Singleton design pattern
 *
 * @author tyler
 */
import java.util.Observable;
import java.util.Observer;
import java.util.LinkedList;
import java.lang.Throwable;

public class Controller implements Observer {

    private static Controller instance = new Controller();
    private GameView view;

    private Controller() {

    }

    public static Controller getInstance() {
        return instance;
    }

    public void setView(GameView v) {
        view = v;
    }

    private GameView getView() {
        return view;
    }

    /**
     * This method is called whenever an observable in the model is updated. It
     * then calls the appropriate methods in the view depending on the nature of
     * the update
     *
     * @param o
     * @param obj
     */
    @Override
    public void update(Observable o, Object obj) {

        System.out.println("update " + o.getClass().toString());
        if (o instanceof Player) {
            Player p = (Player) o;
            if (p.equals(TurnManager.getInstance().getActivePlayer())) {
                System.out.println("Update player info");
                InfoPanel.getInstance().setPlayerInfoData(p);
            }
        } else if (o instanceof Space) {
            Space s = (Space) o;
            if (o instanceof Scene) {
                
                Scene scene = (Scene) o;
                
                if(scene.getCard() == null) {
                    BoardPane.getInstance().removeCard(scene.getID());
                }
                
                if (obj instanceof SceneCard) {
                    // Call set card method
                    //
                    
                    BoardPane.getInstance().setCardBackInView(scene.getID(), scene.xCoordinates, scene.yCoordinates);
                    
                    

                } else if (obj instanceof Integer) {
                    int actResult = (Integer) obj;
                    switch (actResult) {
                        case 1:
                        case 2:
                            //Act attempt successful
                            InfoPanel.getInstance().setUpdateTextArea("You have successfully acted !\n");
                            
                            break;
                        case 3:
                        case 4:
                            //Act attempt unsuccessful
                            InfoPanel.getInstance().setUpdateTextArea("Your act attempt was unsuccessful :(\n");
                            break;
                        case 5:
                            //Scene wrapped with bonus
                            InfoPanel.getInstance().setUpdateTextArea("The scene has wrapped! Players will receive a bonus.\n");
                            BoardPane.getInstance().removeCard(scene.getID());
                            break;
                        case 6:
                            //Scene wrapped without bonus
                            InfoPanel.getInstance().setUpdateTextArea("The scene has wrapped!\n"); 
                            InfoPanel.getInstance().setUpdateTextArea("There were no starring actors, so there will be no bonuses.\n");
                            BoardPane.getInstance().removeCard(scene.getID());
                            break;
                        case 7:
                            //Shot counters changed
                            BoardPane.getInstance().redrawShots(scene.getShotCounterIndex(), scene.getRemainingShots(), scene.getTotalShots());
                    }
                } else if(obj instanceof String && ((String)obj).equals("move")) {
                    if(scene.isActive()) {
                        BoardPane.getInstance().setCardFaceUpInView(scene.ID, scene.getCard().getIconID(), scene.xCoordinates, scene.yCoordinates);
                    }
                }
            }
            
            redrawPlayers(s);
        } else if (o instanceof DayManager) {
            //TODO: Update day counter
            
            if("game over".equals(obj)) {
                int[] scores = ScoreManager.calculateScores(Board.getInstance().getPlayers());
                String message = "You have completed " + DayManager.getInstance().getNumberOfDays() + " days so the game is over!\nPlayer scores:\n";
                for(int i = 0; i < scores.length; i++) {
                    message += Board.getInstance().getPlayer(i) + " : " + scores[i] + "\n";
                }
                view.gameOver(message);
            } else {
                redrawPlayers(Board.getInstance().getSpace(Board.getInstance().getTrailorsID()));
            }
            

        } else if (o instanceof Role) {
            //TODO: Updates the player icon for a role
            Role r = (Role) o;
            Player p = TurnManager.getInstance().getActivePlayer();
            if (r.getOccupant() == null) {
                redrawPlayers(Board.getInstance().getSpace(p.getLocation()));
            } else {

                if (r.getOnCard()) {
                    BoardPane.getInstance().movePlayerToStarringRole(p.getID(), p.getLocation(), r.getXCoordinates(), r.getYCoordinates());
                } else {
                    BoardPane.getInstance().movePlayerLabel(r.getOccupant().getID(), r.getXCoordinates(), r.getYCoordinates());
                }
            }

        } else if (o instanceof TurnManager) {
            if (obj instanceof LinkedList) {
                LinkedList<UseCase> availableActions = (LinkedList) obj;
                ActionsPanel.getInstance().updateEnabledButtons(availableActions);
                InfoPanel.getInstance().setPlayerInfoData(TurnManager.getInstance().getActivePlayer());
                ActionsPanel.getInstance().setPlayerLabel(TurnManager.getInstance().getActivePlayerID());
            } 
        }
    }

    /**
     * Creates the player objects and stores them in the Board class
     *
     * @param num the number of players
     */
    public void setNumPlayers(int num) {
        view = new GameView(num);
        //Space space = Board.getInstance().getSpace(Board.getInstance().getTrailorsID());
        BoardPane.getInstance().makePlayerLabels();
        Player[] players = new Player[num];
        TurnManager.init(num);
        ScoreManager.init(num);
        Board b = Board.getInstance();
        b.setPlayers(players);
        for (int i = 0; i < num; i++) {
            players[i] = new Player(i);
        }
        DayManager.getInstance().init(num);

        //Set players' locations
        for (Player p : players) {
            p.setLocation(b.getTrailorsID());
        }

        InfoPanel.getInstance().setPlayerInfoData(TurnManager.getInstance().getActivePlayer());
        ActionsPanel.getInstance().updateEnabledButtons(TurnManager.getInstance().getAvailableActions());
        
        BoardPane.getInstance().initializeAllShots(b.getShotCounterXCoords(), b.getShotCounterYCoords());
        this.redrawPlayers(Board.getInstance().getSpace(Board.getInstance().getTrailorsID()));
        
        //BoardPane.getInstance().movePlayerLabel(0, 10, 50);

    }

    ///////////////////////////////////////////
    //             USE CASES                 //
    // The following methods are called by   //
    // the view upon user input/interaction. //
    ///////////////////////////////////////////
    
    public void takeRoleMenu() {
        Player p = TurnManager.getInstance().getActivePlayer();
        Scene s = (Board.getInstance().getScene(p.getLocation()));
        System.out.println(p.getRank());
        System.out.print(s.getName());
        System.out.println(" Available roles: " + s.getAvailableRoles(p.getRank()));
        TakeRoleChoicesFrame f = new TakeRoleChoicesFrame(s.getAvailableRoles(p.getRank()));
    }

    /**
     * Use case for taking a role
     *
     * @param r the role being taken
     */
    public void takeRole(Role r) {
        r.requestRole(TurnManager.getInstance().getActivePlayer());
    }
    
    /**
     * Displays the move menu
     */
    public void moveMenu() {
        new MoveChoicesFrame(Board.getInstance().getSpace(TurnManager.getInstance().getActivePlayer().getLocation()).getAdjacentSpaces());
    }

    /**
     * Use case for moving
     *
     * @param index the ID of the desired space
     */
    public void move(int index) {
        Player p = TurnManager.getInstance().getActivePlayer();
        Board.getInstance().getSpace(p.getLocation()).moveTo(p, index);
    }

    /**
     * Use case: Act
     */
    public void act() {
        Player p = TurnManager.getInstance().getActivePlayer();
        Scene s = (Scene) Board.getInstance().getSpace(p.getLocation());
        s.requestActAttempt(p);
    }

    /**
     * Use case: Rehearse
     */
    public void rehearse() {
        Player p = TurnManager.getInstance().getActivePlayer();
        Space loc = Board.getInstance().getSpace(p.getLocation());
        if (loc.getClass().equals(Scene.class)) {
            Scene s = (Scene) loc;
            s.requestRehearsal(p);
        }
    }
    
    /**
     * Displays the upgrade menu
     */
    public void upgradeMenu() {
        TurnManager.getInstance().setHasUpgraded(true);
        
        Player p = TurnManager.getInstance().getActivePlayer();
        CastingOffice c = CastingOffice.getInstance();
        BoardPane.getInstance().displayUpgradeLabels(p.getRank(), c.getMaxRankIndex(p.getDollars(), CurrencyType.DOLLARS), c.getMaxRankIndex(p.getCredits(), CurrencyType.CREDITS));
    }

    /**
     * Use case: upgrade
     *
     * @param rank the desired rank
     * @param type the type of currency the player is using to pay
     */
    public void upgrade(int rank, CurrencyType type) {
        System.out.println("upgrade");
        Player p = TurnManager.getInstance().getActivePlayer();
        CastingOffice.getInstance().purchaseRank(p, rank, type);
        BoardPane.getInstance().deleteUpgradeLabels();
    }

    /**
     * Use case: end turn
     */
    public void endTurn() {
        TurnManager.getInstance().nextTurn();

    }

    /**
     * Redraws the players on a given space, except for the ones that are
     * working on a role.
     *
     * @param s the space to be redrawn
     */
    public void redrawPlayers(Space s) {
        int xOffset = 0;
        int yOffset = 0;
        for (Player p : s.getPlayerSet()) {
            if (p.getRole() == null) {
                BoardPane.getInstance().movePlayerLabel(p.getID(), s.getXCoordinates() + (50 * xOffset), s.getYCoordinates() + (50 * yOffset));
                if (xOffset >= 3) {
                    xOffset = 0;
                    yOffset++;
                } else {
                    xOffset++;
                }
            }
        }
    }
}
