/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadwood;

/**
 *
 * @author tyler
 */

//TODO: Implement Observable operations
import java.util.Observable;
public class Player extends Observable{
    private int ID;
    private int location;
    private int dollars;
    private int credits;
    private int rank;
    private Role currRole;
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
    
    public int getScore() {
        return ScoreManager.getScore(ID);
    }
    public int getID() {
        return ID;
    }
    public int getLocation() {
        return location;
    }
    public void setLocation(int newLocID) {
        location = newLocID;
        setChanged();
        notifyObservers();
    }
    public int getDollars() {
        return dollars;
    }
    public void setDollars(int amount) {
        dollars = amount;
        setChanged();
        notifyObservers();
    }
    public int getCredits() {
        return credits;
    }
    public void setCredits(int amount) {
        credits = amount;
        setChanged();
        notifyObservers();
    }
    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
        setChanged();
        notifyObservers();
    }
    public Role getRole() {
        return currRole;
    }
    public void setRole(Role role) {
        currRole = role;
        setChanged();
        notifyObservers();
    }
    public int getRehearsalChips() {
        return rehearsalChips;
    }
    public void setRehearsal(int amount) {
        rehearsalChips = amount;
        setChanged();
        notifyObservers();
    }
    
    @Override
    public String toString() {
        return "Player " + (getID() + 1);
    }
    
    public boolean equals(Player other) {
        return this.getID() == other.getID();
    }
}
