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
            System.out.println("update space");
            //Updates player icon location
            Space s = (Space) o;
            //System.out.println("I m in instance of SPACE");
            //BoardPane.getInstance().movePlayerLabel(TurnManager.getInstance().getActivePlayer().getID(), s.getXCoordinates(), s.getYCoordinates());
            int i = 0;
            /* for (Player p : s.getPlayerSet()) {
                if (p.getRole() == null) {
                    System.out.println("Redrawing players...");
                    BoardPane.getInstance().movePlayerLabel(p.getID(), s.getXCoordinates() + (20 * i), s.getYCoordinates());
                    i++;
                }
            }*/
            if (o instanceof Scene) {
                Scene scene = (Scene) o;
                System.out.println(scene.getTotalShots());
               /* for (int j = 0; j < scene.getShotCountersXCoordinates().size(); j++) {
                    BoardPane.getInstance().setShotCountersInView(scene.getShotCountersXCoordinates().get(j), scene.getShotCountersYCoordinates().get(j));
                }*/

                if (obj instanceof SceneCard) {
                    //TODO: Call set card method
                    BoardPane.getInstance().setCardFaceUpInView(scene.ID, scene.getCard().getIconID(), scene.xCoordinates, scene.yCoordinates);

                } else if (obj instanceof Integer) {
                    int actResult = (Integer) obj;
                    switch (actResult) {
                        case 1:
                        case 2:
                            //Act attempt successful
                            InfoPanel.getInstance().setUpdateTextArea("You have successfully acted!");
                            
                            break;
                        case 3:
                        case 4:
                            //Act attempt unsuccessful
                            InfoPanel.getInstance().setUpdateTextArea("Your act attempt was unsuccessful :(");
                            break;
                        case 5:
                            //Scene wrapped with bonus
                            InfoPanel.getInstance().setUpdateTextArea("The scene has wrapped! Players will receive a bonus.");
                            break;
                        case 6:
                            //Scene wrapped without bonus
                            InfoPanel.getInstance().setUpdateTextArea("The scene has wrapped! There were no starring actors, so there will be no bonuses.");
                            break;
                        case 7:
                            //Shot counters changed
                            BoardPane.getInstance().redrawShots(scene.getShotCounterIndex(), scene.getRemainingShots(), scene.getTotalShots());
                    }
                }
            }
        } else if (o instanceof DayManager) {
            //TODO: Update day counter
            //Space space = Board.getInstance().getSpace(Board.getInstance().getTrailorsID());
            //BoardPane.getInstance().positionPlayersInTrailer(space.getXCoordinates(), space.getYCoordinates());
            //System.out.println("POSITIONFZEJNF ZEJKFNKZJEFZE");

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
            } else {
                //TODO: Update active player info
            }
        }
    }

    /**
     * Creates the player objects and stores them in the Board class
     *
     * @param num the number of players
     */
    public void setNumPlayers(int num) {
        Space space = Board.getInstance().getSpace(Board.getInstance().getTrailorsID());
        BoardPane.getInstance().positionPlayersInTrailer(space.getXCoordinates(), space.getYCoordinates());
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
        Player p = TurnManager.getInstance().getActivePlayer();
        CastingOffice c = CastingOffice.getInstance();
        new UpgradeFrame(p.getRank(), p.getDollars(), p.getCredits(), c.getDollarPrices(), c.getCreditPrices());
    }

    /**
     * Use case: upgrade
     *
     * @param rank the desired rank
     * @param type the type of currency the player is using to pay
     */
    public void upgrade(int rank, CurrencyType type) {
        Player p = TurnManager.getInstance().getActivePlayer();
        CastingOffice.getInstance().purchaseRank(p, rank, type);
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
