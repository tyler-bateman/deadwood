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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Observable;

public class Space extends Observable{
    protected int ID;
    protected String name;
    protected Set<Player> players = new HashSet<Player>(); //The set of players currently in this space
    protected LinkedList<Space> adjacentSpaces; //Array containing the IDs of all adjacent spaces
    protected int xCoordinates;
    protected int yCoordinates;
    
    /**
     * Constructor
     * Adds the controller as an observer.
     */
    public Space() {
        addObserver(Controller.getInstance());
    }
    /**
     * 
     * @param player : the player requesting the move
     * @param index: the ID of the new space
     * @return : True if move was successful 
     *           False if move was invalid
     */
    public boolean moveTo(Player player, int index) {    
        player.setLocation(index);          
        this.removePlayer(player);
        Board.getInstance().getSpace(index).addPlayer(player);
        TurnManager.getInstance().setHasMoved(true);
        return true;
    }
    
    /**
     * 
     * @param player : the player to be added to the player set
     */
    public void addPlayer(Player player) {
        getPlayerSet().add(player);
        notifyObservers();

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
        return players.contains(player);
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
     * @param Name the new name
     */
    public void setName(String Name){
        name = Name;
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
    public LinkedList<Space> getAdjacentSpaces() {
        return adjacentSpaces;
    }
    
    /**
     * 
     * @param spaces : the new array of adjacent spaces
     */
    public void setAdjacentSpaces(LinkedList<Space> spaces) {
        adjacentSpaces = spaces;
    }
    
    public int getXCoordinates(){
        return xCoordinates;
    }
    
    public void setXCoordinates(int x){
        xCoordinates = x;
    }
    
    public int getYCoordinates(){
        return yCoordinates;
    }
    public void setYCoordinates(int y){
        yCoordinates = y;
    }
}
