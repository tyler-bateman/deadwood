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
public class Scene {
    private String name;
    private SceneCard card;
    private int remainingShots;
    private int totalShots;
    private Role[] offCardRoles;

    public Scene(){
        //XML
    }

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
        for(Roles: card.getRoles()) {
          if(r == role) {
            return true;
          }
        }
        return false;
    }


    public boolean requestRehearsal(Player player){
        if(this.hasRole(player.getRole())) {
          player.setRehearsal(player.getRehearsalChips() + 1);
        }
    }

    public boolean requestActAttempt(Player player){
        return false;
    }

    public void assignDice(){

    }

    public boolean isSceneActive(){
        return;
    }

    protected void wrap(){

    }

    public String getName(){
        return null;
    }

    public void setName(){

    }

    public SceneCard getCard(){
        return null;
    }

    public void setCard(SceneCard card){

    }

    public int getRemainingShots(){
        return 0;
    }

    public void setRemainingShots(){

    }

    public Role[] getOffCardRoles(){
        return null;
    }

    public void setOffCardRoles(){

    }

}
