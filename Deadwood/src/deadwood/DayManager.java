package deadwood;

/**
 * @author tyler The DayManager keeps track of the current day, checks for day
 * ends, and triggers new days.
 *
 * Implements Singleton design pattern.
 */
import java.util.Observable;

public class DayManager extends Observable {

    //The singular instance of DayManager as required by Singleton design pattern
    private static DayManager instance = new DayManager();
    
    //The current day
    private static int currentDay;
    
    //The total number of days that will be played
    private static int numberOfDays;

    /**
     * Private constructor, according to Singleton design pattern.
     */
    private DayManager() {
        addObserver(Controller.getInstance());
    }
    
    /**
     * 
     * @return the singular instance of DayManager
     */
    public static DayManager getInstance() {
        return instance;
    }

    /**
     * Initializes the day manger at the beginning of the game
     *
     * @param numPlayers the number of players playing the game
     */
    public void init(int numPlayers) {
        if (numPlayers > 3) {
            setNumberOfDays(3);
        } else {
            setNumberOfDays(4);
        }
        setCurrentDay(0);
        dayEnd(Board.getInstance());

    }

    /**
     * Resets the board for the new day if there is only one active scene left.
     *
     * @return true if there is only one or less active scenes left on the board
     * otherwise false
     */
    public boolean checkForDayEnd() {
        Board b = Board.getInstance();
        int activeScenes = 0;
        for (Scene scene : b.getScenes()) {
            if (scene.getCard() != null) {
                activeScenes++;
            }
        }
        if (activeScenes <= 1) {
            dayEnd(b);
            return true;

        } else {
            return false;
        }
    }

    /**
     * Manages what happens at the end of a day, including incrementing the day
     * and checking for whether the game is over
     *
     * @param b: the game board
     */
    private void dayEnd(Board b) {

        setCurrentDay(getCurrentDay() + 1);
        if (getCurrentDay() > getNumberOfDays()) {
            setChanged();
            notifyObservers("game over");
        } else {
            b.newDay();
            setChanged();
            notifyObservers();
            
        }
    }

    /**
     * Returns the current day
     * @return the current day
     */
    public int getCurrentDay() {
        return currentDay;
    }

    /**
     * Sets the day number
     * @param newDay the new day
     */
    private void setCurrentDay(int newDay) {
        currentDay = newDay;
    }

    /**
     * @return the total number of days that will be played
     */
    public int getNumberOfDays() {
        return numberOfDays;
    }
    
    /**
     * Sets the total number of days that will be played
     * @param n the new total number of days
     */
    private void setNumberOfDays(int n) {
        numberOfDays = n;
    }
}
