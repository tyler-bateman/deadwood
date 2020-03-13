package deadwood;

/**
 * @author tyler The DayManager keeps track of the current day, checks for day
 * ends, and triggers new days.
 *
 * Implements Singleton design pattern.
 */
import java.util.Observable;
import java.util.Observer;

public class DayManager extends Observable {

    private static DayManager instance = new DayManager();
    private static int currentDay;
    private static int numberOfDays;

    /**
     * Private constructor, according to Singleton design pattern.
     */
    private DayManager() {
        addObserver(Controller.getInstance());
    }

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
            //Deadwood.sendMessage("There is only one active scene so the day has ended.");
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

        //Deadwood.sendMessage("Wake up! It's a new day! Welcome to day " + getCurrentDay());
    }

    /**
     * Returns the current day
     */
    public int getCurrentDay() {
        return currentDay;
    }

    /**
     * Sets the day number
     */
    private void setCurrentDay(int newDay) {
        currentDay = newDay;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    private void setNumberOfDays(int n) {
        numberOfDays = n;
    }
}
