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
        if(source.equals(View.class)) {
            //TODO
        } else if(source.equals(Player.class)){
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
    
}
