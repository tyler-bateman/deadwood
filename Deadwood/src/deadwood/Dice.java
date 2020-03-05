/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadwood;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author nada
 */
public class Dice {
    private LinkedList<Integer> dice;
    
    public Dice(){
        dice = new LinkedList<Integer>();
    }
    
    public Dice(int num){
        dice = new LinkedList<Integer>();
        roll(num);
    }
    
    
    /*
    * Populates dice with num random integers in descending order using insertion sort
    */
    public void roll(int num){
      
       Random r = new Random();
       for(int i = 0; i < num; i++) {
           insertDie(r.nextInt(6) + 1);
       }
       //Deadwood.sendMessage("You rolled the following dice: " + dice.toString());
    }
    
    /*
    * Inserts the int val into dice
    * Invariant: dice is in descending order
    */
    public void insertDie(int val) {
        dice.addFirst(val);
        for(int i = 0; i < dice.size() - 1; i++) {
            if(val < dice.get(i + 1)) {
                dice.set(i, dice.get(i + 1));
                dice.set(i + 1, val);
            } else {
                break;
            }
        }
    }
    
    public boolean hasNextDie() {
        return !dice.isEmpty();
    }
    
    public int nextDie() {
        return dice.pop();
    }
        
    public LinkedList<Integer> getDice(){
        return dice;
    }
    
    public void setDice(){
        
    }
    
}
