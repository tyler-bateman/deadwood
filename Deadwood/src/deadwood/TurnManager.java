package deadwood;

/**
  * @author tyler
  *
  */


public class TurnManager {
  private static int activePlayerID;
  private static int[] playOrder;


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



}
