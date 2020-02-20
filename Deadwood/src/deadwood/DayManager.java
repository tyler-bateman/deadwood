package deadwood;

/**
 * Class: DayManager
 * Keeps track of the day number and triggers events that relate to the end of a day
 * @author tyler
 *
 */
public class DayManager{
  private static int day;

  /**
    * Resets the board for the new day if there is only one active scene left.
    * @return true if there is only one or less active scenes left on the board
    *         otherwise false
    */
  public static boolean checkForDayEnd() {
      Board b = Board.getInstance();
      int activeScenes = 0;
      for(Scene scene: b.getScenes()) {
        if(scene.getCard() != null) {
          activeScenes++;
        }
      }
      if(activeScenes <= 1) {
        dayEnd(b);
        return true;
        
      } else {
        return false;
      }
  }
  
  /**
   * Manages what happens at the end of a day, including incrementing the day and checking for whether the game is over
   * @param b: the game board
   */
  private static void dayEnd(Board b) {
    setDay(getDay() + 1);
    if((b.getPlayers().length < 4 && getDay() >= 3) || getDay() >= 4) {
        ScoreManager.declareWinner(b.getPlayers());
    } else {
        b.newDay();
    }
  }

  /**
    * @return the current day
    */
  public static int getDay() {
      return day;
  }

  /**
    * Sets the day number
    * @param newDay: the new day number
    */
  private static void setDay(int newDay) {
      day = newDay;
  }
}
