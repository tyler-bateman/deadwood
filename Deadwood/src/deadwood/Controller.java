package deadwood;

/**
 * 
 * 
 * Implements Singleton design pattern
 * @author tyler
 */

import java.util.Observable;
import java.util.Observer;

public class Controller extends Observable implements Observer {
    private static Controller instance = new Controller();
    
    private Controller() {
        
    }
    
    public static Controller getInstance() {
        return instance;
    }
    
    @Override
    public void update(Observable o, Object obj) {
        Class source = o.getClass();
        if(source.equals(Player.class)){
            //TODO
        } else if(source.equals(Space.class)) {
            //TODO
        } else if(source.equals(DayManager.class)) {
            //TODO
        } else if(source.equals(Role.class)) {
            //TODO
        } else if(source.equals(Scene.class)) {
            //TODO
        } else if(source.equals(ScoreManager.class)) {
            //TODO
        } else if (source.equals(TurnManager.class)) {
            //TODO
        }
    }
    
    
    /**
     * Creates the player objects and stores them in the Board class
     * @param num the number of players
     */
    public void setNumPlayers(int num) {
        Player[] players = new Player[num];
        for(int i = 0; i < num; i++) {
            players[i] = new Player(i, Board.getInstance().getTrailorsID());
        }
        Board.getInstance().setPlayers(players);
    }
    
    /**
     * Use case for taking a role
     * @param r the role being taken
     */
    public void takeRole(Role r) {
        r.requestRole(TurnManager.getInstance().getActivePlayer());
    }
    
    /**
     * Use case for moving
     * @param index the in the adjacent spaces array for the current space
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
        Space loc = Board.getInstance().getSpace(p.getLocation());
        if(loc.getClass().equals(Scene.class)) {
            Scene s = (Scene)loc;
            s.requestActAttempt(p);
        }
    }
    
    /**
     * Use case: Rehearse
     */
    public void rehearse() {
        Player p = TurnManager.getInstance().getActivePlayer();
        Space loc = Board.getInstance().getSpace(p.getLocation());
        if(loc.getClass().equals(Scene.class)) {
            Scene s = (Scene)loc;
            s.requestRehearsal(p);
        }
    }
    
    /**
     * Use case: upgrade
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
}
