
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

    public static Player nextTurn() {
        return null;
    }

    public static Space[] getSpaces() {
        return null;
    }

    public static void setSpaces(Space[] spaces) {

    }

    public static Scene[] getScenes() {
        return null;
    }

    public static Player[] getPlayers() {
        return null;
    }

    public static void setPlayers(Player[] players) {

    }

    public static Player getActivePlayer() {
        return null;
    }

    public static void setActivePlayer(Player player) {

    }
}
