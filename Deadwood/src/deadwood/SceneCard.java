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
public class SceneCard {
    private int ID;
    private String title;
    private String description;
    private LinkedList<Role> roles;
    private int budget;
    private boolean faceUp;
    
    public SceneCard(){
        
    }
    
    public void flip(){
        
    }
    
    public boolean hasPlayers(){
        return false;
    }
    
    public int getID(){
        return 0;
    }
    
    public void setID(){
        
    }
    
    public String getTitle(){
        return null;
    }
    
    public void setTitle(){
        
    }
    
    public String getDescription(){
        return null;
    }
    
    public void setDescription(){
        
    }
    
    public LinkedList<Role> getRoles(){
        return null;
    }
    
    public void setRoles(){
        
    }
    
    public int getBudget(){
        return 0;
    }
    
    public void setBudget(){
        
    }
    
    public boolean getFaceUp(){
        return false;
    }
    
    public void setFaceUp(){
        
    }
    
}
