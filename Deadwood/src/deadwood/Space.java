/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadwood;

/**
 *
 * @author tyler
 */

import java.util.Set;

public class Space {
    protected int ID;
    protected String name;
    protected Set<Player> players;
    protected Space[] adjacentSpaces;
    
    public Space(String name) {
        
    }
    
    public boolean requestMove(Player player) {
        
        return false;
    }
    
    public void addPlayer(Player player) {
        
    }
    
    public void removePlayer(Player player) {
        
    }
}
