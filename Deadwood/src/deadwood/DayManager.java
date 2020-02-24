package deadwood;

/**
  * @author tyler
  *
  */
public class DayManager{
  private static int currentDay;
  private static int numberOfDays;
  
  /**
   * Initialises the day manger at the beginning of the game
   * @param numPlayers the number of players playing the game
   */
  public static void init(int numPlayers) {
      if(numPlayers > 3) {
          numberOfDays = 3;
      } else {
          numberOfDays = 4;
      }
      setCurrentDay(0);
      dayEnd(Board.getInstance());
      
  }
  
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
        Deadwood.sendMessage("There is only one active scene so the day has ended.");
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
    if(getCurrentDay() > getNumberOfDays()) {
        ScoreManager.declareWinner(b.getPlayers());
    } else {
        b.newDay();
    }
    Deadwood.sendMessage("Wake up! It's a new day! Welcome to day " + getCurrentDay());
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
