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
    private Role[] offCardRoles;
    
    public Scene(){
        
    }
    
    public Scene(String name){
        this.name = name;
    }
    
    public void resetShots(){
        
    }
    
    public void close(){
        
    }
    
    public boolean requestRole(Player player, Role role){
        return false;
    }
    
    public boolean requestRehearsle(Player player){
        return false;
    }
    
    public boolean requestActAttempt(Player player){
        return false;
    }
    
    public void assignDice(){
        
    }
    
    public boolean isSceneActive(){
        return false;
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
    
    public void setCard(){
        
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
