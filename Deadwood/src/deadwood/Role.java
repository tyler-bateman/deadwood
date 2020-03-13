
package deadwood;

/**
 * Role
 * Instances of this class store the information for a role, and perform related 
 * actions, such as requesting roles and paying bonuses
 * 
 * 
 * @author nada & tyler
 */

import java.util.Observable;
public class Role extends Observable{
    /**
     * The name of this role
     */
    private String name;
    
    /**
     * The line that this role says
     */
    private String line;
    
    /**
     * The ID of this role
     */
    private int ID;
    
    /**
     * The minimum rank required to take this role
     */
    private int rank;
    
    /**
     * The current occupant of this role
     * null if there is no occupant
     */
    private Player occupant;
    
    /**
     * The bonus payout of this role after the scene wraps
     */
    private int payout;
    
    /**
     * The x and y coordinates of this role in the GUI
     */
    private int xCoordinates;
    private int yCoordinates;
    
    /**
     * Whether this role is on-card (true) or off-card (false)
     */
    private boolean onCard;

    /**
     * Constructor: Adds the controller as an observer
     */
    public Role() {
        addObserver(Controller.getInstance());
    }
    /**
      * If the role is not taken and the player has the appropriate rank,
      * the player occupies this role.
      * @param player: the player requesting the role
      * @return true if the player successfully took this role
      * Precondition: The player is in the space that contains this role.
      */
    public boolean requestRole(Player player){
        if(!isRoleTaken() && player.getRank() >= rank) {
          setOccupant(player);
          player.setRole(this);
          TurnManager.getInstance().setHasTakenRole(true);
          return true;
        } else {
          return false;
        }
    }

    /**
      * Increases the payout for this role
      * @param amount: the amount by which to increase the payout
      */
    public void increasePayout(int amount){
        payout += amount;
    }

    /**
      * Removes the player from this role
      */
    public void removePlayer(){
        if(occupant != null) {
            occupant.setRole(null);
            occupant.setRehearsal(0);
            setOccupant(null);
        }
        payout = 0;
    }

    /**
      * Pays the bonus for this role
      * @return the amount that was payed out
      */
    public int payBonus(){
        if(occupant != null) {
          Banker.giveDollars(getOccupant(), getPayout());
          int tmp = getPayout();
          setPayout(0);
          return tmp;
          
        } else {
          setPayout(0);
          return 0;
        }
    }

    /**
     * @return true if the role is taken
     */
    public boolean isRoleTaken(){
        return occupant != null;
        }

    /**
     * 
     * @return the name of this role
     */
    public String getName(){
        return name;
    }

    /**
     * 
     * @param n the new name for this role 
     */
    public void setName(String n){
        name = n;
    }

    /**
     *
     * @return the line for this role
     */
    public String getLine(){
        return line;
    }

    /**
     * 
     * @param l the new line for this role
     */
    public void setLine(String l){
        line = l;
    }

    /**
     * 
     * @return the id of this role
     */
    public int getID(){
        return ID;
    }

    /**
     * 
     * @param newID the new ID for this role
     */
    private void setID(int newID){
        ID = newID;
    }

    /**
     * 
     * @return the rank required to take this role
     */
    public int getRank(){
        return rank;
    }

    /**
     * 
     * @param r the new rank required to take this role
     */
    public void setRank(int r){
        rank = r;
    }

    /**
     * 
     * @return the occupant of this role
     */
    public Player getOccupant(){
        return occupant;
    }

    /**
     * 
     * @param p the new occupant of this role
     */
    public void setOccupant(Player p){
        occupant = p;
        setChanged();
        notifyObservers();
    }
    
    /**
     * Sets whether this role is on a card
     * @param b the flag for whether this role is on a card
     */
    public void setOnCard(boolean b) {
        onCard = b;
    }
    
    /**
     * 
     * @return true if this role is on-card, false otherwise
     */
    public boolean getOnCard() {
        return onCard;
    }

    /**
     * 
     * @return the amount that this role will pay as a bonus
     */
    public int getPayout(){
        return payout;
    }

    /**
     * Sets the payout that the occupant will receive upon scene wrap
     * @param newPayout the new amount that this role pays as a bonus
     */
    private void setPayout(int newPayout){
        payout = newPayout;
    }
    
    /**
     * Gets the x coordinate of this role in the GUI
     * @return the x coordinate of this role
     */
    public int getXCoordinates(){
        return xCoordinates;
    }
    
    /**
     * Sets the x coordinate of this role in the GUI
     * @param x the new x coordinate
     */
    public void setXCoordinates(int x){
        xCoordinates = x;
    }
    
    /**
     * Gets the y coordinate of this role in the GUI
     * @return the y coordinate
     */
    public int getYCoordinates(){
        return yCoordinates;
    }
    
    /**
     * Sets the y coordinate of this role in the GUI
     * @param y the y coordinate
     */
    public void setYCoordinates(int y){
        yCoordinates = y;
    }

}
