package deadwood;

/**
  * @author tyler
  *
  */
public class DayManager{
  private static int day;

  /**
    * Returns true if there is only one active scene left on the board
    */
  public static boolean isDayOver() {
      int activeScenes = 0;
      for(Scene scene: Board.getScenes()) {
        if(SceneCard != null) {
          activeScenes++;
        }
      }
      return activeScenes <= 1;
  }

  /**
    * Returns the current day
    */
  public static int getDay() {
      return 0;
  }

  private static void setDay(int day) {

  }
}
