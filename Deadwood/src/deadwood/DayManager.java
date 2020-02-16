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
    * @param scenes: the array of all scenes on the board
    */
  public static boolean checkForDayEnd(Board b) {
      int activeScenes = 0;
      for(Scene scene: b.getScenes()) {
        if(scene.getCard() != null) {
          activeScenes++;
        }
      }
      if(activeScenes <= 1) {
        b.newDay();
        setDay(getDay() + 1);
        return true;
        
      } else {
        return false;
      }
  }

  /**
    * Returns the current day
    */
  public static int getDay() {
      return day;
  }

  /**
    * Sets the day number
    */
  private static void setDay(int newDay) {
      day = newDay;
  }
}
