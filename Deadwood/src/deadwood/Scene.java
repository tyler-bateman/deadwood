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
   
    
    public void resetShots(){
        remainingShots = totalShots;
    }
    
    public void close(){
        for(Role r: card.getRoles()) {
          r.removePlayer();
        }
        for(Role r: offCardRoles) {
          r.removePlayer();
        }
        card = null;
    }

    public boolean requestRole(Player player, Role role){
        if(this.hasRole(role)) {
          return role.requestRole(player);
        } else {
          return false;
        }
    }

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


    public boolean requestRehearsal(Player player){
        if(this.hasRole(player.getRole())) {
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


    private void wrap(){
        if(card.hasPlayers()) {
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

        } else { //There are no players on the card
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
    
    @Override
    public boolean requestMove(Player player, Space[] spaces) {
        if(super.requestMove(player, spaces)) {
            getCard().flip();
            return true;
        } else {
            return false;
        }
    }


    public boolean isSceneActive(){
        return getCard()!=null;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public SceneCard getCard(){
        return card;
    }

    public void setCard(SceneCard card){
        this.card = card;
    }

    public int getRemainingShots(){
        return remainingShots;
    }

    private void setRemainingShots(int shots){
        remainingShots = shots;
    }

    public LinkedList<Role> getOffCardRoles(){
        return offCardRoles;
    }

    public void setOffCardRoles(LinkedList<Role> roles){
        offCardRoles = roles;
    }
    
    public int getTotalShots(){
        return totalShots;
    }
    
    public void setTotalShots(int t){
        totalShots = t;
    }

}
