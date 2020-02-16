
package deadwood;

/**
 *
 * @author tyler
 */
public class Board {
    private Space[] spaces;
    private Scene[] scenes;
    private int trailorsID;
    private Player[] players;

    public void setup() {

    }

    /**
      * Resets the board for a new day:
      *   Moves players back to the trailers
      *   Deals new scene cards
      */
    public void newDay() {
      for(Scene s: getScenes()) {
        s.close();
      }
      for(Player p: players) {
        p.setLocation(trailorsID);
        spaces[trailorsID].addPlayer(p);
      }
      Deck.deal(getScenes());

    }

    //Returns an array containing the Spaces
    public Space[] getSpaces() {
        return spaces;
    }

    //Returns the space with the given id
    public Space getSpace(int id) {
        return spaces[id];
    }

    //Setter for the spaces field
    private void setSpaces(Space[] s) {
        spaces = s;
    }

    //Returns an array containing the list of scenes
    public Scene[] getScenes() {
        return scenes;
    }

    public Scene getScene(int id) {
        return scenes[id];
    }

    //Setter for the scenes field
    public void setScenes(Scene[] s) {
        scenes = s;
    }

    //Returns an array containing the players on the board
    public Player[] getPlayers() {
        return players;
    }

    //Returns the Player with the given id
    public Player getPlayer(int id) {
        return getPlayers()[id];
    }

    //Setter for the players field
    public void setPlayers(Player[] ps) {
        players = ps;
    }

    public int getTrailorsID() {
        return trailorsID;
    }

    private void setTrailorsID(int newID) {
        trailorsID = newID;
    }

}
