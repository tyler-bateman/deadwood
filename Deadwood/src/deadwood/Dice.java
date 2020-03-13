package deadwood;
import java.util.LinkedList;
import java.util.Random;

/**
 * Dice.java
 * This class represents Dice and performs all operations involving Dice in the model
 * 
 * @author nada & tyler
 */
public class Dice {
    
    /**
     * The linked list of dice represented by this dice object
     */
    private LinkedList<Integer> dice;
    
    /**
     * Constructor
     * Initializes the linked list of dice
     */
    public Dice(){
        setDice(new LinkedList<Integer>());
    }
    
    /**
     * Constructor
     * Initializes the linked list of dice and rolls dice (populates the linked list)
     * @param num the number of dice to roll
     */
    public Dice(int num){
        dice = new LinkedList<Integer>();
        roll(num);
    }
    
    
    /**
    * Populates dice with num random integers in descending order using insertion sort
    * @param num: The number of dice to roll
    */
    public void roll(int num){
      
       Random r = new Random();
       for(int i = 0; i < num; i++) {
           insertDie(r.nextInt(6) + 1);
       }
    }
    
    /**
    * Inserts a die into the dice linked list
    * Uses the same process as insertion sort to maintain the linked list being in descending order
    * Invariant: dice is in descending order
    * @param val: the value of the die to be inserted
    */
    private void insertDie(int val) {
        LinkedList<Integer> d = getDice();
        d.addFirst(val);
        for(int i = 0; i < d.size() - 1; i++) {
            if(val < d.get(i + 1)) {
                d.set(i, d.get(i + 1));
                d.set(i + 1, val);
            } else {
                break;
            }
        }
    }
    
    /**
     * Determines if there are any more dice 
     * @return true if the dice linked list is not empty
     */
    public boolean hasNextDie() {
        return !dice.isEmpty();
    }
    
    /**
     * Removes the first die and returns its value
     * @return the value of the remaining die with the greatest value
     */
    public int nextDie() {
        return dice.pop();
    }
    
    /**
     * Gets the linked list of dice
     * @return the linked list of dice
     */
    private LinkedList<Integer> getDice(){
        return dice;
    }
    
    /**
     * Replaces the dice linked list with a new list of dice
     * 
     * Precondition: d is in descending order (or empty)
     * @param d the new list of dice
     */
    private void setDice(LinkedList<Integer> d) {
        dice = d;
    }
    
}
