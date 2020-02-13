/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadwood;
import java.util.LinkedList;

/**
 *
 * @author nada
 */
public class SceneCard {
    private int ID;
    private String title;
    private String description;
    private LinkedList<Role> roles;
    private int budget;
    private boolean faceUp;

    public SceneCard(){
        //TODO: XML
    }

    public void flip(){
        faceUp = true;
    }

    public boolean hasPlayers(){
        for(Role r: roles) {
            if(r.isRoleTaken()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasRole(Role role) {
      for(Role r: roles) {
        if(r == role) {
          return true;
        }
      }
      return false;
    }

    public int getID(){
        return ID;
    }

    private void setID(int newID){
        ID = newID;
    }

    public String getTitle(){
        return title;
    }

    private void setTitle(String t){
        title = t;
    }

    public String getDescription(){
        return description;
    }

    private void setDescription(String d){
        description = d;
    }

    /**
      * Returns a list of roles sorted by rank high to low
      */
    public LinkedList<Role> getRoles(){
        return roles;
    }

    private void setRoles(LinkedList<Role> roleList){
        roles = roleList;
    }

    public int getBudget(){
        return budget;
    }

    public void setBudget(int b){
        budget = b;
    }

    public boolean getFaceUp(){
        return faceUp;
    }

    public void setFaceUp(boolean b){
        faceUp = b;
    }

}
