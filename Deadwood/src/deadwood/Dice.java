/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadwood;
import java.util.LinkedList;

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
    
    public void roll(int num){
        
    }
    
    public LinkedList<Integer> getDice(){
        return null;
    }
    
    public void setDice(){
        
    }
    
}
