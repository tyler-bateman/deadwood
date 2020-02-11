/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadwood;

/**
 *
 * @author tyler
 */
public class Banker {
    
    public static boolean chargeDollars(Player player, int amount) {
        if(player.getDollars() < amount) {
            return false;
        } else {
            player.setDollars(player.getDollars() - amount);
            return true;
        }
    }
    
    public static boolean chargeCredits(Player player, int amount) {
        if(player.getCredits() < amount) {
            return false;
        } else {
            player.setCredits(player.getCredits() - amount);
            return true;
        }
    }
    
    public static void giveDollars(Player player, int amount) {
        player.setDollars(player.getDollars() + amount);
    }
    
    public static void giveCredits(Player player, int amount) {
        player.setCredits(player.getCredits() + amount);
    }
}
