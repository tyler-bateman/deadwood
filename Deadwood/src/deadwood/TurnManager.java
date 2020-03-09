package deadwood;

/**
 * TurnManager keeps track of who's turn it is. Pretty self explanatory.
 * 
 * @author tyler
 */

import java.util.Observable;
import java.util.Observer;
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
    
    /**
     * Gets the instance of turn manager. If it has not been instantiated yet,
     * the turn manager is instantiated.
     * @return the singular instance of TurnManager
     */
    public static TurnManager getInstance() {
        if(instance == null) {
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
    public void setTakenRole(boolean b) {
        hasTakenRole = b;
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
    public void setHasMoved(Boolean b) {
        hasMoved = b;
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
    public void setHasActed(Boolean b) {
        hasActed = b;
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
    public void setHasUpgraded(Boolean b) {
        hasUpgraded = b;
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
    public void setHasRehearsed(Boolean b) {
        hasRehearsed = b;
    }
    
    
}
