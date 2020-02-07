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
        
    }
    
    public Role(int ID){
        this.ID = ID;
    }
    
    public Role(int ID, int rank){
        this.ID = ID;
        this.rank = rank;
    }
    
    public boolean requestRole(Player player){
        return false;
    }
    
    public void increasePayout(int amount){
        
    }
    
    public void removePlayer(){
        
    }
    
    public int payBonus(){
        return 0;
    }
    
    public boolean isRoleTaken(){
        return false;
    }
    
    public String getName(){
        return null;
    }
    
    public void setName(){
        
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
    
    public void setOccupant(){
    
    }
    
    public int getPayout(){
        return 0;
    }
    
    public void setPayout(){
        
    }
    
}
