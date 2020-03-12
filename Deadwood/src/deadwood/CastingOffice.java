
package deadwood;

/**
 * Class: CastingOffice
 * An extention of Space that manages the purchasing of ranks
 * 
 * Implements Singleton design pattern
 *
 * @author tyler
 */

import java.lang.ArrayIndexOutOfBoundsException;
import java.util.LinkedList;

public class CastingOffice extends Space{
    private int[] dollarPrices;
    private int[] creditPrices;
    private LinkedList<Integer> dollarCoordinates;
    private LinkedList<Integer> creditCoordinates;
    private static CastingOffice instance = new CastingOffice();
    
    private CastingOffice(){
        dollarPrices = new int[]{4, 10, 18, 28, 40};
        creditPrices = new int[]{5, 10, 15, 20, 25};
        
    }
    
    public static CastingOffice getInstance() {
        return instance;
    }
    
    /**
     * Allows the player to purchase a new rank 
     * @param player: the player who is purchasing the new rank
     * @param rank: the desired rank
     * @param payment: the payment type for the purchase
     */
    public boolean purchaseRank(Player player, int rank, CurrencyType payment) {
        if(rank <= player.getRank() || rank > 6) {
            //Deadwood.sendMessage("The selected rank is out of range");
            return false;
        }
           
        if(this.containsPlayer(player)) {
            int price = (payment == CurrencyType.DOLLARS)? getDollarPrice(rank) : getCreditPrice(rank);
            boolean successful = Banker.charge(player, price, payment);
            //System.out.println(successful);
            if(successful) {
                player.setRank(rank);
                TurnManager.getInstance().setHasUpgraded(true);
                return true;
            } else {
                return false;
            }
            
        }
        
        return false;
    }
    
    public int getMaxRank(int wealth, CurrencyType payment) {
        for(int i = 0; i < dollarPrices.length; i++) {
            if(getPrice(i, payment) > wealth) {
                return i + 1;
            }
        }
        return 6;
    }
    
    public int getPrice(int rank, CurrencyType payment) {
        if(payment == CurrencyType.CREDITS) {
            return getCreditPrice(rank);
        } else {
            return getDollarPrice(rank);
        }
    }
    
    /**
     * @return: the dollar price of a rank
     * If the given price is out of range, returns max integer
     */
    public int getDollarPrice(int rank) {
        
        try{
            return dollarPrices[rank - 2];
        }
        catch(ArrayIndexOutOfBoundsException e) {
            return Integer.MAX_VALUE;
        }
        
    }
    
    /** 
     * @return: the credit price of a rank
     * If the given price is out of rank, returns max integer
     */
    public int getCreditPrice(int rank) {
        try{
            return creditPrices[rank - 2];
        }
        catch(ArrayIndexOutOfBoundsException e) {
            return Integer.MAX_VALUE;
        }
    }
    
    /**
     * @return: the array of dollar prices
     */
    public int[] getDollarPrices() {
        return dollarPrices;   
    }
    
    /**
     * @return: the array of credit prices
     */
    public int[] getCreditPrices() {
        return creditPrices;
    }
    
    public LinkedList<Integer> getDollarCoordinates(){
        return dollarCoordinates;
    }
    
    public void setDollarCoordinates(LinkedList<Integer> d){
        dollarCoordinates = d;
    }
    
    public LinkedList<Integer> getCreditCoordinates(){
        return creditCoordinates;
    }
    
    public void setCreditCoordinates(LinkedList<Integer> c){
        creditCoordinates = c;
    }
        
}

