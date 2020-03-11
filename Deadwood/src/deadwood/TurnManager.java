package deadwood;

/**
 * TurnManager keeps track of who's turn it is. Pretty self explanatory.
 * 
 * @author tyler
 */

import java.util.Observable;
import java.util.LinkedList;

public class TurnManager extends Observable{
    private static TurnManager instance;
    private int numPlayers;
    private int activePlayerID;
    
    //The following variables keep track of which actions have been taken by the player this turn
    private boolean hasTakenRole = false;
    private boolean hasMoved = false;
    private boolean hasActed = false;
    private boolean hasRehearsed = false;
    private boolean hasUpgraded = false;
    
    private final UseCase[] useCases = {UseCase.MOVE, UseCase.TAKE_ROLE, UseCase.REHEARSE, UseCase.ACT, UseCase.UPGRADE, UseCase.END_TURN};
    
    
    /**
     * Private constructor as required by the singleton design pattern.
     * @param c the controller
     * @param numPlayers the number of players
     */
    private TurnManager (Controller c, int numPlayers) {
        addObserver(c);
        this.numPlayers = numPlayers;
        activePlayerID = 0;
    }
    
    public static void init(int numPlayers) {
        instance = new TurnManager(Controller.getInstance(), numPlayers);
    }
    /**
     * Gets the instance of turn manager. If it has not been instantiated yet,
     * the turn manager is instantiated.
     * @return the singular instance of TurnManager
     */
    public static TurnManager getInstance() {
        if(instance == null) {
            System.out.println("Controller " + Controller.getInstance());
            System.out.println("Board: " + Board.getInstance());
            System.out.println("Players: " + Board.getInstance().getPlayers());
            instance = new TurnManager(Controller.getInstance(), Board.getInstance().getPlayers().length);
        }
        return instance;
    }
    
    
    /**
     * @return the active player ID
     */
    public int getActivePlayerID() {
        return activePlayerID;
    }
    
    /**
     * 
     * @return the active player
     */
    public Player getActivePlayer() {
        return Board.getInstance().getPlayer(getActivePlayerID());
    }
    
    /**
     * Begins the next player's turn
     */
    public void nextTurn() {
        activePlayerID ++;
        if(getActivePlayerID() >= numPlayers) {
            activePlayerID = 0;
        }
        setHasTakenRole(false);
        setHasMoved(false);
        setHasActed(false);
        setHasRehearsed(false);
        setHasUpgraded(false);
        
        setChanged();
        notifyObservers();
    }
    
    /**
     * @return true if the active player has taken a role this turn
     */
    public boolean hasTakenRole() {
        return hasTakenRole;
    }
    
    /**
     * @param b the new flag for whether the active player has taken a role this turn
     */
    public void setHasTakenRole(boolean b) {
        hasTakenRole = b;
        setChanged();
        notifyObservers(getAvailableActions());
    }
    
    /**
     * @return true if the active player has moved this turn.
     */
    public boolean hasMoved() {
        return hasMoved;
    }
    
    /**
     * @param b the new flag for whether the active player has moved this turn
     */
    public void setHasMoved(boolean b) {
        hasMoved = b;
        setChanged();
        notifyObservers(getAvailableActions());
    }
    
    /**
     * @return true if the active player has acted this turn
     */
    public boolean hasActed() {
        return hasActed;
    }
    
    /**
     * @param b the new flag for whether the active player has acted this turn
     */
    public void setHasActed(boolean b) {
        hasActed = b;
        setChanged();
        notifyObservers(getAvailableActions());
    }
    
    /**
     * @return true if the active player has acted this turn
     */
    public boolean hasUpgraded() {
        return hasUpgraded;
    }
    
    /**
     * @param b the new flag for whether the active player has upgraded this turn
     */
    public void setHasUpgraded(boolean b) {
        hasUpgraded = b;
        setChanged();
        notifyObservers(getAvailableActions());
    }
    
    /**
     * @return true if the active player has rehearsed this turn
     */
    public boolean hasRehearsed() {
        return hasRehearsed;
    }
    
    /**
     * @param b the new flag for whether the active player has rehearsed this turn
     */
    public void setHasRehearsed(boolean b) {
        hasRehearsed = b;
        setChanged();
        notifyObservers(getAvailableActions());
    }
    
    
    //////////////////////////////////////
    //        AVAILABLE ACTIONS         //
    // The the following methods        //
    // determine which actions the      //
    // active player is allowed to take //
    //////////////////////////////////////
    
    /**
     * The player can take a role if they are on a scene, and not already working
     * @return true if the active player is allowed to take a role
     */
    public boolean canTakeRole() {
        Player p = getActivePlayer();
        Space currSpace = Board.getInstance().getSpace(p.getLocation());
        
        return currSpace instanceof Scene && p.getRole() == null;
    }
    
    /**
     * The player can move if they are not working, they have not moved or acted
     * @return true if the active player is allowed to move
     */
    public boolean canMove() {
        return !hasMoved() && !hasActed() && getActivePlayer().getRole() == null;
    }
    
    /**
     * The player can rehearse if they are working, they are not guaranteed 
     * success if they choose to act, and they haven't already acted, rehearsed, or taken a role
     * @return true if the active player is allowed to rehearse
     */
    public boolean canRehearse() {
        Player p = getActivePlayer();
        return !hasActed() && !hasRehearsed() && !hasTakenRole() && p.getRole() != null && (p.getRehearsalChips() + 1) < ((Scene)Board.getInstance().getSpace(p.getLocation())).getCard().getBudget();
    }
    
    /**
     * The player can act if they are working, and they haven't already acted, rehearsed, or taken a role
     * @return true if the active player is allowed to act
     */
    public boolean canAct() {
        return !hasActed() && !hasRehearsed() && !hasTakenRole() && getActivePlayer().getRole() != null;
    }
    
    /**
     * The player can upgrade if they are on the casting office
     * @return true if the active player is allowed to upgrade.
     */
    public boolean canUpgrade() {
        return Board.getInstance().getSpace(getActivePlayer().getLocation()) instanceof CastingOffice;
    }
    
    /**
     * The player can end their turn immediately if they are not working.
     * Otherwise, they must act or rehearse.
     */
    public boolean canEndTurn() {
        return getActivePlayer().getRole() == null || hasRehearsed() || hasActed() || hasTakenRole();
    }
    
        
    /**
     * @return true if the given use case is available
     */
    public boolean can(UseCase action) {
        switch(action) {
            case TAKE_ROLE:
                return canTakeRole();
            case MOVE:
                return canMove();
            case ACT:
                return canAct();
            case REHEARSE:
                return canRehearse();
            case UPGRADE:
                return canUpgrade();
            case END_TURN:
                return canEndTurn();
            default:
                return false;
        }
    }
    
    /**
     * 
     * @return the list of all actions that the user is allowed to perform
     */
    public LinkedList<UseCase> getAvailableActions() {
        LinkedList<UseCase> availableActions = new LinkedList<UseCase>();
        for(UseCase action: useCases) {
            if(can(action)) {
                availableActions.add(action);
            }
        }   
        return availableActions;
    }
    
    
}
