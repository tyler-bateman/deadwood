package deadwood;

/**
 * TurnManager keeps track of who's turn it is. Pretty self explanatory.
 * 
 * @author tyler
 */

import java.util.Observable;
import java.util.Observer;
public class TurnManager extends Observable implements Observer{
    private static TurnManager instance;
    private int numPlayers;
    private int activePlayerID;
    
    
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
     * @param o the Observable that updated this observer
     * @param obj the new active player ID. If it is invalid, i.e. too large,
     *            it resets at the first player and notifies the controller.
     */
    @Override
    public void update(Observable o, Object obj) {
        int newTurn;
        try{
            newTurn = (Integer)obj;
        } catch(ClassCastException e) {
            return;
        }
        
        if(newTurn >= numPlayers) {
            activePlayerID = 0;
            notifyObservers();
        } else {
            activePlayerID = newTurn;
        }
        
    }
}
