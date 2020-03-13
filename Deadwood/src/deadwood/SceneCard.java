package deadwood;
import java.util.LinkedList;

/**
 * SceneCard
 * This class represents the scene cards 
 * 
 * Contains all data and behaviors relevant to the scene card including:
 *      
 * 
 * @author nada & tyler
 */
public class SceneCard {
    private int number;
    private String name;
    private String description;
    private LinkedList<Role> roles;
    private String iconID;
    private int budget;

    /**
     * Determines whether the card has any players on it
     * @return true if this card has any players in it
     */
    public boolean hasPlayers(){
        for(Role r: roles) {
            if(r.isRoleTaken()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the card has a given role
     * @param role the role to be searched for
     * @return true if the card contains the given role
     */
    public boolean hasRole(Role role) {
      for(Role r: roles) {
        if(r == role) {
          return true;
        }
      }
      return false;
    }

    /**
     * gets the number
     * @return the number
     */
    public int getNumber(){
        return number;
    }

    /**
     * Sets the number
     * @param newNumber the new number
     */
    public void setNumber(int newNumber){
        number = newNumber;
    }

    /**
     * Gets the name of this scene card
     * @return the name of this scene card
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name of this scene card
     * @param n the new name
     */
    public void setName(String n){
        name = n;
    }

    /**
     * Gets the description of this scene card
     * @return the description
     */
    public String getDescription(){
        return description;
    }

    /**
     * Sets the description of this scene card
     * @param d the new description
     */
    public void setDescription(String d){
        description = d;
    }

    /**
      * @return the list of roles sorted by rank from high to low
      */
    public LinkedList<Role> getRoles(){
        return roles;
    }

    /**
     * Sets the roleList
     * @param roleList the new roleList
     * precondition: roleList is sorted by rank from low to high
     */
    public void setRoles(LinkedList<Role> roleList){
        roles = roleList;
    }

    /**
     * Gets the budget of the scene card
     * @return the budget
     */
    public int getBudget(){
        return budget;
    }

    /**
     * Sets the budget of the scene card
     * @param b the new budget
     */
    public void setBudget(int b){
        budget = b;
    }

    public String getIconID(){
        return iconID;
    }
    
    public void setIconID(String id){
        iconID = id;
    }
    


}
