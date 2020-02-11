package deadwood;

/**
  * @author tyler
  *
  */


public class TurnManager {
    private static int activePlayerID;
    private static int[] playOrder;
    private static int currTurn;

    /** Overloaded wrapper method for init with default play order
      * @param players: the list of players
      */
    public static void init(Player[] players) {
      init(players, new int[0]);
    }

    /** Sets the play order and selects the first active player
      * @param players: the list of players
      * @param playOrder: the desired play order
      */
    public static void init(Player[] players, int[] playOrder) {
      //if the provided playOrder is valid, use it. Otherwise, use the default play order
      if(players.length == playOrder.length) {
        this.playOrder = playOrder;
      } else {
        playOrder = new int[players.length];
        for(int i = 0; i < playOrder.length; i++) {
          playOrder[i] = i;
        }
      }
      setTurn(0);
      setActivePlayer(playOrder[0]);

    }

    /**
      * Selects the next player to take their turn
      *   updates activePlayer
      *   returns that player
      *
      * @param players: the list of players
      */
    public static Player nextTurn(Player[] players) {
      //TODO
      return null;
    }

    /**
      * Returns the active player
      *
      * @param players: the list of players
      */
    public static Player getActivePlayer(Player[] players) {
      return players[activePlayerID];
    }

    /**
      * Returns the active player's id
      */
    public static int getActivePlayer() {
      return activePlayerID;
    }

    /**
      * Sets the activePlayerID
      * @param player: the player to be the active player
      */
    public static void setActivePlayer(Player player) {
        activePlayerID = player.getID();
    }

    /**
      * Sets the activePlayerID
      * @param id: the id of the player to be the active player
      */
    public static void setActivePlayer(int id) {
      activePlayerID = id;
    }

    /**
      * Gets the current turn number
      */
    public static int getTurn() {
      return currTurn;
    }

    private static int setTurn(int turn) {
      currTurn = turn;
    }



}
