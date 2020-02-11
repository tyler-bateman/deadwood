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
        test2();
        test3();
    }
    
    public static void test1() {
        Dice d = new Dice(10);
        System.out.println(d.getDice().toString());
    }
    
    public static void test2() {
        Dice d = new Dice();
        d.insertDie(6);
        d.insertDie(5);
        d.insertDie(4);
        d.insertDie(3);
        d.insertDie(2);
        d.insertDie(1);
        System.out.println(d.getDice().toString());
    }
    
    public static void test3() {
        Dice d = new Dice();
        d.insertDie(2);
        d.insertDie(4);
        d.insertDie(3);
        d.insertDie(1);
        d.insertDie(5);
        System.out.println(d.getDice().toString());
    }
}
