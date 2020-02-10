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
        roll(1);
    }
    
    public Dice(int num){
        dice = new LinkedList<Integer>();
        roll(num);
    }
    
    
    /*
    * Populates dice with num random integers in descending order
    */
    public void roll(int num){
       Random r = new Random();
       for(int i = 0; i < num; i++) {
           insertDie(r.nextInt(6) + 1);
       }
    }
    
    /*
    * Inserts the int val into dice
    * Invariant: dice is in descending order
    */
    private void insertDie(int val) {
        int insertPosition = 0;
        while(insertPosition < dice.size() || dice.get(insertPosition) > val){
            insertPosition++;
        }
        dice.add(insertPosition, val);
    }
        
    public LinkedList<Integer> getDice(){
        return null;
    }
    
    public void setDice(){
        
    }
    
}
