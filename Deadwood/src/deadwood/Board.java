
package deadwood;

/**
 * Class: Board
 * The purpose of the Board class is to manage the spaces and players, 
 * including reseting them to start a new day.
 * 
 * Implements Singleton design pattern with eager initialization since every 
 * game will have one and only one board that is created on startup
 * 
 * @author tyler
 */
public class Board {
    private static Board instance = new Board();
    private Space[] spaces;
    private Scene[] scenes;
    private int trailorsID;
    private Player[] players;

    /**
     * Private constructor as required by Singleton design pattern.
     * Does not set the state of the new board since this is done externally by ParseXML.java
     */
    private Board() {}
    
    /**
     * getInstance() method as required by Singleton design pattern
     * @return the singular instance of Board
     */
    public static Board getInstance() {
        return instance;
    }
    

    /**
      * Resets the board for a new day:
      *   Moves players back to the trailers
      *   Deals new scene cards
      */
    public void newDay() {
      //Close all scenes
      for(Scene s: getScenes()) {
        s.close();
      }
      //Move players to the trailors
      for(Player p: players) {
        p.setLocation(trailorsID);
        spaces[trailorsID].addPlayer(p);
      }
      //Deal new cards
      Deck.getInstance().deal(getScenes());

    }

    /**
     * Gets the array of spaces
     * @return the array of spaces
     */
    public Space[] getSpaces() {
        return spaces;
    }

    /**
     * Gets a specific space
     * @param id the id of the desired space
     * @return the space with the given id
     */
    public Space getSpace(int id) {
        return spaces[id];
    }

    /**
     * Sets the array of spaces
     * @param s the new array of spaces
     */
    public void setSpaces(Space[] s) {
        spaces = s;
    }

    /**
     * Gets the array of scenes
     * @return the array of scenes
     */
    public Scene[] getScenes() {
        return scenes;
    }
    
    //Returns the scene with the given id
    public Scene getScene(int id) {
        return scenes[id];
    }

    /**
     * Sets the scene array
     * @param s the new scene array
     */
    public void setScenes(Scene[] s) {
        scenes = s;
    }

    /**
     * Gets the array of players
     * @return the array of players
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Gets a specific player
     * @param id the id of the desired player
     * @return the player with the given id
     */
    public Player getPlayer(int id) {
        return getPlayers()[id];
    }

    /**
     * Sets the player array
     * @param ps the array of players
     */
    public void setPlayers(Player[] ps) {
        players = ps;
    }
    
    /**
     * Gets the id of the trailers
     * @return id of the trailors space
     */
    public int getTrailorsID() {
        return trailorsID;
    }
    
    /**
     * Sets the id of the trailors space
     * @param newID the new id for the trailors
     */
    private void setTrailorsID(int newID) {
        trailorsID = newID;
    }

}
