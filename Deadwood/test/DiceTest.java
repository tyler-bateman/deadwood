/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import deadwood.Dice;
/**
 *
 * @author tyler
 */
public class DiceTest {
    public static void main(String[] args) {
        test1();
    }
    
    public static void test1() {
        Dice d = new Dice(6);
        System.out.println(d.getDice().toString());
    }
}
