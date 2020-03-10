/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadwood;

/**
 *
 * @author nada
 */

import java.util.Observable;
public class Role extends Observable{
    private String name;
    private String line;
    private int ID;
    private int rank;
    private Player occupant;
    private int payout;
    private int xCoordinates;
    private int yCoordinates;

    /**
     * Constructor: Adds an observer
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
        notifyObservers();
    }

    /**
     * 
     * @return the amount that this role will pay as a bonus
     */
    public int getPayout(){
        return payout;
    }

    /**
     * 
     * @param newPayout the new amount that this role pays as a bonus
     */
    private void setPayout(int newPayout){
        payout = newPayout;
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
