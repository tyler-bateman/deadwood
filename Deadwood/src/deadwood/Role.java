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
public class Role {
    private String name;
    private String description;
    private int ID;
    private int rank;
    private Player occupant;
    private int payout;


    public Role(){
        //TODO: Parse xml
    }

    /**
      * If the role is not taken and the player has the appropriate rank,
      * the player occupies this role.
      * @param player: the player requesting the role
      * Precondition: The player is in the space that contains this role.
      */
    public boolean requestRole(Player player){
        if(!isRoleTaken() && player.getRank() >= rank) {
          setOccupant(player);
          return true;
        } else {
          return false;
        }
    }

    /**
      * Increases the payout for this role
      */
    public void increasePayout(int amount){
        payout += amount;
    }

    /**
      * Removes the player from this role
      */
    public void removePlayer(){
        occupant = null;
        payout = 0;
    }

    /**
      * Pays the bonus for this role
      */
    public int payBonus(){
        if(occupant != null) {
          Banker.giveDollars(getPayout());
          return getPayout();
          setPayout(0);
        } else {
          setPayout(0);
          return 0;
        }
    }

    public boolean isRoleTaken(){
        return false;
    }

    public String getName(){
        return null;
    }

    private void setName(){

    }

    public String getDescription(){
        return null;
    }

    public void setDescription(){

    }

    public int getID(){
        return 0;
    }

    public void setID(){

    }

    public int getRank(){
        return 0;
    }

    public void setRank(){

    }

    public Player getOccupant(){
        return null;
    }

    private void setOccupant(){

    }

    public int getPayout(){
        return 0;
    }

    public void setPayout(){

    }

}
