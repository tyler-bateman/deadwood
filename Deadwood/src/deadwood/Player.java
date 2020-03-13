
package deadwood;

/**
 * Player.java
 * Stores all data for each individual player
 * 
 * Observed by the Controller. All changes to the data stored in this notify the controller
 */

import java.util.Observable;
public class Player extends Observable{
    //The player's ID, aka their index in Board's player array
    private int ID;
    
    //The ID of the player's current space
    private int location;
    
    //The amount of dollars and credits the player has
    private int dollars;
    private int credits;
    
    //The player's current rank
    private int rank;
    
    //The player's current role
    //If they don't have a role, it is null
    private Role currRole;
    
    //The number of rehearsal chips the player has
    private int rehearsalChips;
    
    
    /*
     * Constructor for a Player object
     * @param ID: The player's ID
     * @param name: the player's name
    */
    public Player(int ID) {
        addObserver(Controller.getInstance());
        this.ID = ID;
        dollars = 0;
        credits = 0;
        rank = 1;
        currRole = null;
        rehearsalChips = 0;
    }
    
    /**
     * Returns the player's current score
     * @return the player's score
     */
    public int getScore() {
        return ScoreManager.getScore(ID);
    }
    
    /**
     * Gets the player's ID
     * @return the Player's ID
     */
    public int getID() {
        return ID;
    }
    
    /**
     * Returns the ID of player's location
     * @return the ID of player's location
     */
    public int getLocation() {
        return location;
    }
    
    /**
     * Sets the location of the player and notifies observers.
     * Note: Does not update the set of players in the space classes.
     * @param newLocID the ID of the new location of the player
     */
    public void setLocation(int newLocID) {
        location = newLocID;
        setChanged();
        notifyObservers();
    }
    
    /**
     * Gets the number of dollars that the player has
     * @return The number of dollars the player has
     */
    public int getDollars() {
        return dollars;
    }
    
    /**
     * Sets the number of dollars the player has
     * @param amount the new number of dollars
     */
    public void setDollars(int amount) {
        dollars = amount;
        setChanged();
        notifyObservers();
    }
    
    /**
     * Gets the number of credits the player has
     * @return the number of credits the player has
     */
    public int getCredits() {
        return credits;
    }
    
    /**
     * Sets the number of credits the player has
     * @param amount the number of credits the player has
     */
    public void setCredits(int amount) {
        credits = amount;
        setChanged();
        notifyObservers();
    }
    
    /**
     * Gets the rank of the player
     * @return the rank of the player
     */
    public int getRank() {
        return rank;
    }
    
    /**
     * Sets the player's rank
     * @param rank the player's new rank
     */
    public void setRank(int rank) {
        this.rank = rank;
        setChanged();
        notifyObservers();
    }
    
    /**
     * Gets the player's current role
     * If the player isn't working, returns null
     * @return the player's role
     */
    public Role getRole() {
        return currRole;
    }
    
    /**
     * Sets the player's current role
     * @param role the new role
     */
    public void setRole(Role role) {
        currRole = role;
        setChanged();
        notifyObservers();
    }
    
    /**
     * Gets the number of rehearsal chips the player has
     * @return the number of rehearsal chips
     */
    public int getRehearsalChips() {
        return rehearsalChips;
    }
    
    /**
     * Sets the number of times the player has rehearsed
     * @param amount the new number of rehearsals
     */
    public void setRehearsal(int amount) {
        rehearsalChips = amount;
        setChanged();
        notifyObservers();
    }
    
    
    /**
     * Returns a string representation of this player
     * @return the a string representation of this player
     */
    @Override
    public String toString() {
        return "Player " + (getID() + 1);
    }
    
    /**
     * Determines if two player objects are the same
     * @param other
     * @return 
     */
    public boolean equals(Player other) {
        return this.getID() == other.getID();
    }
}
