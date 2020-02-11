
package deadwood;

/**
 *
 * @author tyler
 */
public class Board {
    private static Space[] spaces;
    private static Scene[] scenes;
    private static int trailorsID;
    private static Player[] players;
    private static Player activePlayer;

    public static void setup() {
      //TODO (XML)
    }

    /**
      * Resets the board for a new day:
      *   Moves players back to the trailers
      *   Deals new scene cards
      */
    public static void newDay() {
      for(Scene s: getScenes()) {
        s.close();
      }
      for(Player p: players) {
        p.setLocation(trailorsID);
        spaces[traidlorsID].addPlayer(p);
      }
      Deck.deal(getScenes());

    }

    //Returns an array containing the Spaces
    public static Space[] getSpaces() {
        return spaces;
    }

    //Returns the space with the given id
    public static Space getSpace(int id) {
        return spaces[id];
    }

    //Setter for the spaces field
    private static void setSpaces(Space[] spaces) {
        this.spaces = spaces;
    }

    //Returns an array containing the list of scenes
    public static Scene[] getScenes() {
        return scenes;
    }

    public static Scene getScene(int id) {
        return scenes[id];
    }

    //Setter for the scenes field
    private static void setScenes(Scene[] scenes) {
        this.scenes = scenes;
    }

    //Returns an array containing the players on the board
    public static Player[] getPlayers() {
        return players;
    }

    //Returns the Player with the given id
    public static Player getPlayer(int id) [
        return players[id];
    ]

    //Setter for the players field
    public static void setPlayers(Player[] players) {
        this.players = players;
    }

}
