package deadwood;

/**
  * @author tyler
  *
  */
public class DayManager{
  private static int currentDay =1;
  private static int numberOfDays;
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
    setCurrentDay(getCurrentDay() + 1);
    if((b.getPlayers().length < 4 && getCurrentDay() >= 3) || getCurrentDay() >= 4) {
        ScoreManager.declareWinner(b.getPlayers());
    } else {
        b.newDay();
    }
  }

  /**
    * Returns the current day
    */
  public static int getCurrentDay() {
      return currentDay;
  }

  /**
    * Sets the day number
    */
  private static void setCurrentDay(int newDay) {
      currentDay = newDay;
  }
  
  public static int getNumberOfDays(){
      return numberOfDays;
  }
  
  public static void setNumberOfDays(int n){
      numberOfDays = n;
  }
}
