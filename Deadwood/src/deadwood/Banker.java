  
package deadwood;

/**
 * CLASS: Banker
 * The purpose of the Banker class is to manage the giving and taking of dollars and credits
 *
 * @author tyler
 */
public class Banker {
    
    /**
     * Charges the given player an amount of dollars if they have enough.
     * @return: true if the charge was successful, (i.e. the player had enough money)
     *          false if the charge was unsuccessful, (i.e. the player didn't have enough money)
     * @param player: the player to be charged
     * @param amount: the number of dollars to charge the player
     */
    public static boolean chargeDollars(Player player, int amount) {
        if(player.getDollars() < amount) {
            //Deadwood.sendMessage("Player " + player.getID() + " cannot afford $" + amount);
            return false;
        } else {
            player.setDollars(player.getDollars() - amount);
            //Deadwood.sendMessage("Player " + player.getID() + " has been charged $" + amount + " and now has $" + player.getDollars());
            return true;
        }
    }
    
    
    /**
     * Charges the given player an amount of credits if they have enough.
     * @return: true if the charge was successful, (i.e. the player had enough credits)
     *          false if the charge was unsuccessful, (i.e. the player didn't have enough credits)
     * @param player: the player to be charged
     * @param amount: the number of credits to charge the player
     */    
    public static boolean chargeCredits(Player player, int amount) {
        if(player.getCredits() < amount) {
            //Deadwood.sendMessage(player + " cannot afford " + amount + " credits");
            return false;
        } else {
            //Deadwood.sendMessage(player + " has been charged " + amount + " credits and now has " + player.getDollars() + " credits");
            player.setCredits(player.getCredits() - amount);
            return true;
        }
    }
    
    /**
     * Charges the given player an amount of of currency specified by the CurrencyType
     * @return: true if the charge was successful
     *          false otherwise
     * @param player: the player to be charged
     * @param amount: the amount to be charged
     * @param CurrencyType: the type of currency to be charged
     */
    public static boolean charge(Player player, int amount, CurrencyType type) {
        if(type == CurrencyType.DOLLARS) {
            return chargeDollars(player, amount);   
        } else if(type == CurrencyType.CREDITS) {
            return chargeCredits(player, amount);
        } else {
            return false;
        }
    }
    
    /**
     * Gives the given player a number of dollars
     * @param player: the player to be given money
     * @param amount: the number of dollars to be given to the player
     */
    public static void giveDollars(Player player, int amount) {
        
        player.setDollars(player.getDollars() + amount);
        //Deadwood.sendMessage(player + " has been given $" + amount + " and now has $" + player.getDollars());
    }
    
    /**
     * Gives the given player a number of credits
     * @param player: the player to be given credits
     * @param amount: the number of credits to be given to the player
     */
    public static void giveCredits(Player player, int amount) {
        
        player.setCredits(player.getCredits() + amount);
        //Deadwood.sendMessage(player + " has been given " + amount + " credits and now has " + player.getCredits() + " credits.");
    }
}

