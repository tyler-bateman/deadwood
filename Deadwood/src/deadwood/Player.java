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
public class Player {
    private int ID;
    private String name;
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
    public Player(int ID, String name, int startingLocation) {
        this.ID = ID;
        this.name = name;
        location = startingLocation;
        dollars = 0;
        credits = 0;
        rank = 0;
        currRole = null;
        rehearsalChips= 0;
        Board.getInstance().getSpaces()[location].addPlayer(this);
    }
    public int getScore() {
        return ScoreManager.getScore(ID);
    }
    public int getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    public int getLocation() {
        return location;
    }
    public void setLocation(int newLocID) {
        location = newLocID;
    }
    public int getDollars() {
        return dollars;
    }
    public void setDollars(int amount) {
        dollars = amount;
    }
    public int getCredits() {
        return credits;
    }
    public void setCredits(int amount) {
        credits = amount;
    }
    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }
    public Role getRole() {
        return currRole;
    }
    public void setRole(Role role) {
        currRole = role;
    }
    public int getRehearsalChips() {
        return rehearsalChips;
    }
    public void setRehearsal(int amount) {
        rehearsalChips = amount;
    }
}
