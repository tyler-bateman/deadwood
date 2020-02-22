/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadwood;

/**
 *
 * @author nada, tyler
 */

import java.util.LinkedList;
import java.util.List;

public class Scene extends Space {
    private String name;
    private SceneCard card;
    private int remainingShots;
    private int totalShots;
    private LinkedList<Role> offCardRoles;
   
    /**
     * resets the shot counter
     */
    public void resetShots(){
        remainingShots = totalShots;
    }
    
    /**
     * Removes all players from their roles in this scene and removes the card
     */
    public void close(){
        if(this.isSceneActive()){
            
        
        for(Role r: card.getRoles()) {
          r.removePlayer();
        }
        for(Role r: offCardRoles) {
          r.removePlayer();
        }
        card = null;
        resetShots();
        }
    }

    /**
     * Requests a role in this scene
     * @param player the player requesting the role
     * @param role the role that the player is requesting
     * @return true if the player successfully claimed the role
     */
    public boolean requestRole(Player player, Role role){
        if(this.hasRole(role) && this.containsPlayer(player)) {
          return role.requestRole(player);
        } else {
          return false;
        }
    }

    /**
     * 
     * @param role the role to be checked
     * @return true if this scene has the given role
     */
    public boolean hasRole(Role role) {
        boolean result = false;
        for(Role r: offCardRoles) {
          if(r == role) {
            return true;
          }
        }
        if(card.hasRole(role)) {
          return true;
        }
        return false;
    }


    /**
     * Increments the rehearsal chips for the given player
     * @param player the player requesting rehearsal
     * @return true if rehearsal was successful
     */
    public boolean requestRehearsal(Player player){
        if(this.hasRole(player.getRole()) && player.getRehearsalChips() < this.getCard().getBudget()) {
            player.setRehearsal(player.getRehearsalChips() + 1);
        }
        return true;
    }

    /**
      * Requests and act attempt for the player.
      * @return: true if the player was allowed to attempt to act
      * @return: false if the player was not allowed an act attempt.
      * Note: Returns true if the attempt went through regardless of whether the
      *       acting was successful or not.
      */
    public boolean requestActAttempt(Player player){
        if(this.hasRole(player.getRole())) {
          Dice d = new Dice(1);
          int dieVal = d.nextDie();
          if(dieVal + player.getRehearsalChips() >= card.getBudget()) {
              setRemainingShots(getRemainingShots() - 1);
              if(card.hasRole(player.getRole())) {
                Banker.giveCredits(player, 2);
              } else {
                Banker.giveCredits(player, 1);
                Banker.giveDollars(player, 1);
              }

              if(getRemainingShots() == 0) {
                wrap();
              }
          } else {
              if(!card.hasRole(player.getRole())) {
                Banker.giveDollars(player, 1);
              }
          }
          return true;
        } else {
          return false;
        }
    }


    /**
     * Wraps the scene: assigns bonus amounts, pays bonus, 
     */
    private void wrap(){
        if(card.hasPlayers()) {
          System.out.println("There are players on the card. Players will receive a bonus");
          Dice d = new Dice(card.getBudget());
          int currRoleIndex = 0;
          List<Role> roleList = card.getRoles();

          //Assign payout values and pay bonuses for each on-card role
          while(d.hasNextDie()) {
            int dieVal = d.nextDie();
            roleList.get(currRoleIndex).increasePayout(dieVal);
            currRoleIndex++;
            if(currRoleIndex >= roleList.size()) {
              currRoleIndex = 0;
            }
          }
          for(Role r: roleList) {
            if(r.getOccupant()!=null){
              r.payBonus();
              r.getOccupant().setRole(null);
              r.removePlayer();
            }
          }

          //Assigns payout values and pay bonuses for each off-card role
          for(Role r: getOffCardRoles()) {
            if(r.getOccupant() != null) {
              r.increasePayout(2);
              r.payBonus();
              r.getOccupant().setRole(null);
              r.removePlayer();
            }

          }

        } 
        else { //There are no players on the card
            System.out.println("There are no players on the card. There is no bonus");
          for(Role r: getOffCardRoles()) {
            if(r.getOccupant() != null) {
              r.getOccupant().setRole(null);
              r.removePlayer();
            }
          }
        }
        setCard(null);
        DayManager.checkForDayEnd();
    }
   

    /**
     * 
     * @return true if the scene is active, i.e it has a scene card
     */
    public boolean isSceneActive(){
        return getCard()!=null;
    }

    /**
     * 
     * @return the name of the scene
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the scene name
     * @param name the new name for the scene
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * 
     * @return the card for this scene
     */
    public SceneCard getCard(){
        return card;
    }

    /**
     * Sets the card field
     * @param card the new card for this scene
     */
    public void setCard(SceneCard card){
        this.card = card;
    }

    /**
     * 
     * @return the number of remaining shots
     */
    public int getRemainingShots(){
        return remainingShots;
    }

    /**
     * Sets the number of remaining shots
     * @param shots the new number of remaining shots
     */
    private void setRemainingShots(int shots){
        remainingShots = shots;
    }

    /**
     * Gets the list of off-card Roles
     * @return the list of off-card Roles
     */
    public LinkedList<Role> getOffCardRoles(){
        return offCardRoles;
    }

    /**
     * Sets the list of off-card roles
     * @param roles the new list of off-card roles
     */
    public void setOffCardRoles(LinkedList<Role> roles){
        offCardRoles = roles;
    }
    
    /**
     * 
     * @return the total number of shots needed for this scene
     */
    public int getTotalShots(){
        return totalShots;
    }
    
    /**
     * Sets the number of shots needed for this scene
     * @param t the new number of shots needed for this scene
     */
    public void setTotalShots(int t){
        totalShots = t;
    }

}
