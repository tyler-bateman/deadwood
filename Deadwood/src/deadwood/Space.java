
package deadwood;

/**
 * Space
 * This class represents a space, or a location on the board
 * A space can be a scene, the casting office, or the trailors
 * 
 * Contains all data and behaviors relevant to a space, notably moving players around
 * 
 * @author tyler
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Observable;

public class Space extends Observable{
    /**
     * The ID of this space, i.e. it's index in the spaces array in Board
     */
    protected int ID;
    
    /**
     * The name of this space
     */
    protected String name;
    
    /**
     * The set of players in this space
     */
    protected Set<Player> players = new HashSet<Player>(); 
    
    /**
     * Array containing the IDs of all spaces a player could move to from this space
     */
    protected LinkedList<Space> adjacentSpaces; 
    
    /**
     * The x and y coordinates of this space;
     */
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
     * Moves a player from this space to one of its adjacent spaces
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
     * Adds a player to this space
     * @param player : the player to be added to the player set
     */
    public void addPlayer(Player player) {
        getPlayerSet().add(player);
        setChanged();
        notifyObservers("move");

    }
    
    /**
     * Removes a player from this space
     * 
     * @param player : the player to removed from the player set
     */
    public void removePlayer(Player player) {
        getPlayerSet().remove(player);
    }
    
    /**
     * Determines wither this space contains a given player
     * 
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
    
    /**
     * 
     * @return the x coordinate of this space
     */
    public int getXCoordinates(){
        return xCoordinates;
    }
    
    /**
     * Sets the x coordinate of this space
     * @param x the new x coordinate
     */
    public void setXCoordinates(int x){
        xCoordinates = x;
    }
    
    /**
     * Gets the y coordinate of this space
     * @return the y coordinate of this space
     */
    public int getYCoordinates(){
        return yCoordinates;
    }
    
    /**
     * Sets the the y coordinate of this space
     * @param y the new y coordinate
     */
    public void setYCoordinates(int y){
        yCoordinates = y;
    }
}
