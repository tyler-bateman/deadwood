package deadwood;

/**
 * Controller.java
 * Represents the controller according to the MVC paradigm
 * Handles the interaction between the view classes and the model classes
 *
 * Implements Singleton design pattern
 *
 * @author tyler
 */
import java.util.Observable;
import java.util.Observer;
import java.util.LinkedList;

public class Controller implements Observer {
    
    /**
     * The singular instance of this class
     */
    private static Controller instance = new Controller();
    
    /**
     * The view that this class interacts with
     */
    private GameView view;

    /**
     * Private constructor as required by Singleton design pattern
     */
    private Controller() {

    }
    
    /**
     * Gets this instance of controller
     * @return 
     */
    public static Controller getInstance() {
        return instance;
    }
    
    /**
     * Sets the GameView
     * @param v 
     */
    private void setView(GameView v) {
        view = v;
    }
    
    /**
     * Gets the GameView
     * @return 
     */
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
                getView().getInfoPanel().setPlayerInfoData(p);
            }
        } else if (o instanceof Space) {
            Space s = (Space) o;
            if (o instanceof Scene) {
                
                Scene scene = (Scene) o;
                if (obj instanceof Integer) {
                    int actResult = (Integer) obj;
                    switch (actResult) {
                        case 1:
                        case 2:
                            //Act attempt successful
                            getView().getInfoPanel().setUpdateTextArea("You have successfully acted !\n");
                            
                            break;
                        case 3:
                        case 4:
                            //Act attempt unsuccessful
                            getView().getInfoPanel().setUpdateTextArea("Your act attempt was unsuccessful :(\n");
                            break;
                        case 5:
                            //Scene wrapped with bonus
                            getView().getInfoPanel().setUpdateTextArea("The scene has wrapped! Players will receive a bonus.\n");
                            //getView().getBoardPane().removeCard(scene.getID());
                            break;
                        case 6:
                            //Scene wrapped without bonus
                            getView().getInfoPanel().setUpdateTextArea("The scene has wrapped!\n"); 
                            getView().getInfoPanel().setUpdateTextArea("There were no starring actors, so there will be no bonuses.\n");
                            //getView().getBoardPane().removeCard(scene.getID());
                            break;
                        case 7:
                            //Shot counters changed
                            getView().getBoardPane().redrawShots(scene.getShotCounterIndex(), scene.getRemainingShots(), scene.getTotalShots());
                            if(scene.getRemainingShots() == 0) {
                                System.out.println("no more shots");
                                getView().getBoardPane().removeCard(scene.getID());
                            }
                    }
                } else if(obj instanceof String && ((String)obj).equals("move")) {
                    if(scene.isActive()) {
                        getView().getBoardPane().setCardFaceUpInView(scene.ID);
                    }
                }
            }
            
            redrawPlayers(s);
        } else if (o instanceof DayManager) {
            //Update day counter
            
            if("game over".equals(obj)) {
                int[] scores = ScoreManager.calculateScores(Board.getInstance().getPlayers());
                String message = "You have completed " + DayManager.getInstance().getNumberOfDays() + " days so the game is over!\nPlayer scores:\n";
                for(int i = 0; i < scores.length; i++) {
                    message += Board.getInstance().getPlayer(i) + " : " + scores[i] + "\n";
                }
                getView().gameOver(message);
            } else {
                redrawPlayers(Board.getInstance().getSpace(Board.getInstance().getTrailorsID()));
                for(Scene s : Board.getInstance().getScenes()) {
                    getView().getBoardPane().dealCard(s.getID(), s.getCard().getIconID(), s.getXCoordinates(), s.getYCoordinates());
                }
                getView().getActionsPanel().setDayLabel("Day " + DayManager.getInstance().getCurrentDay());
            }
            

        } else if (o instanceof Role) {
            //Updates the player icon for a role
            Role r = (Role) o;
            Player p = TurnManager.getInstance().getActivePlayer();
            if (r.getOccupant() == null) {
                redrawPlayers(Board.getInstance().getSpace(p.getLocation()));
            } else {

                if (r.getOnCard()) {
                    getView().getBoardPane().movePlayerToStarringRole(p.getID(), p.getLocation(), r.getXCoordinates(), r.getYCoordinates());
                } else {
                    getView().getBoardPane().movePlayerLabel(r.getOccupant().getID(), r.getXCoordinates(), r.getYCoordinates());
                }
            }

        } else if (o instanceof TurnManager) {
            if (obj instanceof LinkedList) {
                LinkedList<UseCase> availableActions = (LinkedList) obj;
                getView().getActionsPanel().updateEnabledButtons(availableActions);
                getView().getInfoPanel().setPlayerInfoData(TurnManager.getInstance().getActivePlayer());
                getView().getActionsPanel().setPlayerLabel(TurnManager.getInstance().getActivePlayerID());
            } 
        }
    }

    /**
     * Sets up the view and model 
     * Starts the game
     *
     * @param num the number of players
     */
    public void initialize(int num) {
        //Initialize view and draw player labels
        setView( new GameView(num));
        getView().getBoardPane().makePlayerLabels();
        
        //Initialize turn manager and score manager
        TurnManager.init(num);
        ScoreManager.init(num);
        
        Board b = Board.getInstance();
        
        //Populate board with player array
        Player[] players = new Player[num];
        b.setPlayers(players);
        for (int i = 0; i < num; i++) {
            players[i] = new Player(i);
        }
        
        //Draw shot counters
        getView().getBoardPane().initializeAllShots(b.getShotCounterXCoords(), b.getShotCounterYCoords());
        
        //Initialize day manager
        DayManager.getInstance().init(num);

        //Set players' locations
        for (Player p : players) {
            p.setLocation(b.getTrailorsID());
        }
        this.redrawPlayers(Board.getInstance().getSpace(b.getTrailorsID()));
        
        //Display active player info
        getView().getInfoPanel().setPlayerInfoData(TurnManager.getInstance().getActivePlayer());
        
        //Update enabled buttons 
        getView().getActionsPanel().updateEnabledButtons(TurnManager.getInstance().getAvailableActions());
    }

    ///////////////////////////////////////////
    //             USE CASES                 //
    // The following methods are called by   //
    // the view upon user input/interaction. //
    ///////////////////////////////////////////
    
    /**
     * Displays the menu for taking roles
     */
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
        getView().getBoardPane().displayUpgradeLabels(p.getRank(), c.getMaxRankIndex(p.getDollars(), CurrencyType.DOLLARS), c.getMaxRankIndex(p.getCredits(), CurrencyType.CREDITS));
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
        System.out.println("going to delete labels");
        getView().getBoardPane().deleteUpgradeLabels();
        getView().getBoardPane().setPlayerIcon(p.getID(), rank);
        getView().getActionsPanel().setPlayerLabel(p.getID());
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
                getView().getBoardPane().movePlayerLabel(p.getID(), s.getXCoordinates() + (50 * xOffset), s.getYCoordinates() + (50 * yOffset));
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
