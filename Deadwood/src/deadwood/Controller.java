package deadwood;

/**
 * 
 * 
 * Implements Singleton design pattern
 * @author tyler
 */

import java.util.Observable;
import java.util.Observer;

public class Controller  implements Observer {
    private static Controller instance = new Controller();
    
    private Controller() {
        
    }
    
    public static Controller getInstance() {
        return instance;
    }
    
    @Override
    public void update(Observable o, Object obj) {
        Class source = o.getClass();
        if(o instanceof Player){
            Player p = (Player)o;
            if(p.equals(TurnManager.getInstance().getActivePlayer())) {
                //TODO: Call update active player info method
            }
            
        } else if(o instanceof Space) {
            //TODO: Update players in the space
            // Maybe handle which roles the players have?
        } else if(o instanceof DayManager) {
            //TODO: Update day counter
        } else if(o instanceof Role) {
            //TODO: Update role graphic to have or not have the player
        } else if(o instanceof Scene) {
            Scene s = (Scene)o;
            if(obj instanceof SceneCard) {
                //TODO: Call set card method 
            } else if(obj instanceof Integer) {
                switch ((Integer)obj) {
                    case 1:
                        //TODO: Act attempt successful, active player on card
                        break;
                    case 2:
                        //TODO: Act attempt successful, active player off card
                        break;
                    case 3:
                        //TODO: Act attempt unsuccessful, active player on card
                    case 4:
                        //TODO: Act attempt unsuccessful, active player off card
                    case 5:
                        //TODO: Scene wrapped with bonus
                    case 6:
                        //TODO: Scene wrapped without bonus
                }
            } else {
                //TODO: Redraw shot counters
            }
                
        } else if (source.equals(TurnManager.class)) {
            //TODO: Update active player info
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
