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
        //TODO
    }
    
}
