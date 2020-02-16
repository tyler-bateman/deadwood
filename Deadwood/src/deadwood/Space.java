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
    protected Set<Player> players; //The set of players currently in this space
    protected int[] adjacentSpaces; //Array containing the IDs of all adjacent spaces
    
    /**
     * 
     * @param player : the player requesting the move
     * @param spaces: the spaces on the board
     * @return : True if move was successful 
     *           False if move was invalid
     */
    public boolean requestMove(Player player, Space[] spaces) {
        for(int i = 0; i < adjacentSpaces.length; i++) {
            if(getAdjacentSpaces()[i] == player.getLocation()) {
                spaces[player.getLocation()].removePlayer(player);
                addPlayer(player);
                return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * @param player : the player to be added to the player set
     */
    public void addPlayer(Player player) {
        getPlayerSet().add(player);
    }
    
    /**
     * 
     * @param player : the player to removed from the player set
     */
    public void removePlayer(Player player) {
        getPlayerSet().remove(player);
    }
    
    /**
     * @param player: the player to look for in the player set
     * @return : true if the player set contains player, false otherwise
     */
    public boolean containsPlayer(Player player) {
        return getPlayerSet().contains(player);
    }
    
    /**
     * 
     * @return : the ID of this space, i.e. the index of this space in the Board
     */
    public int getID() {
        return ID;
    }
    
    /**
     * 
     * @return : the name of this space
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @return the set of players who are in this space
     */
    public Set<Player> getPlayerSet() {
        return players;
    }
    
    /**
     * 
     * @return an array containing the id's of the adjacent spaces
     */
    public int[] getAdjacentSpaces() {
        return adjacentSpaces;
    }
    
    /**
     * 
     * @param spaces : the new array of adjacent spaces
     */
    public void setAdjacentSpaces(int[] spaces) {
        adjacentSpaces = spaces;
    }
}
