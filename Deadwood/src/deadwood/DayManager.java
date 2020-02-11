package deadwood;

/**
  * @author tyler
  *
  */
public class DayManager{
  private static int day;

  /**
    * Resets the board for the new day if there is only one active scene left.
    * Returns true if there is only one active scene left on the board
    */
  public static boolean checkForDayEnd() {
      int activeScenes = 0;
      for(Scene scene: Board.getScenes()) {
        if(SceneCard != null) {
          activeScenes++;
        }
      }
      if(activeScenes <= 1) {
        Board.newDay();
        return true;
        setDay(getDay() + 1);
      }
  }

  /**
    * Returns the current day
    */
  public static int getDay() {
      return day;
  }

  private static void setDay(int day) {
      this.day = day;
  }
}
