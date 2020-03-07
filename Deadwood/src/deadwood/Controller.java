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
    
    
    public void takeRole(Role r) {
        r.requestRole(TurnManager.getInstance().getActivePlayer());
    }
    
    public void move(int index) {
        Player p = TurnManager.getInstance().getActivePlayer();
        Board.getInstance().getSpace(p.getLocation()).moveTo(p, index);
    }
    
    public void act() {
        
    }
    
    public void rehearse() {
        
    }
    
    public void upgrade(int rank, CurrencyType type) {
        
    }
    
    public void endTurn() {
        
    }
}
