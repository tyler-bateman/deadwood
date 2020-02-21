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
      * @param newOrder: the desired play order
      */
    public static void init(Player[] players, int[] newOrder) {
      //if the provided playOrder is valid, use it. Otherwise, use the default play order
      if(players.length == newOrder.length) {
        playOrder = newOrder;
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
      * @return the new active player
      */
    public static Player nextTurn(Player[] players) {
      currTurn++;
      setActivePlayer(playOrder[currTurn]);
      return getActivePlayer(Board.getInstance().getPlayers());
    }

    /**
      * Returns the active player
      *
      * @param players: the list of players
      * @return the active player
      */
    public static Player getActivePlayer(Player[] players) {
      return players[activePlayerID];
    }

    /**
      * @return the active player's id
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
      * @return the current turn number
      */
    public static int getTurn() {
      return currTurn;
    }

    private static void setTurn(int turn) {
      currTurn = turn;
    }



}
