
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
    
    /**
     * The prices of each rank in dollars and credits starting at rank 2
     */
    private int[] dollarPrices;
    private int[] creditPrices;
    
    /**
     * The coordinates of the upgrade buttons in the GUI
     */
    private LinkedList<Integer> dollarCoordinates;
    private LinkedList<Integer> creditCoordinates;
    
    /**
     * The singular instance of CastingOffice
     */
    private static CastingOffice instance = new CastingOffice();
    
    /**
     * The private constructor as required by Singleton design pattern
     */
    private CastingOffice(){
        dollarPrices = new int[]{4, 10, 18, 28, 40};
        creditPrices = new int[]{5, 10, 15, 20, 25};
        
    }
    
    /**
     * 
     * @return the singular instance of the CastingOffice
     */
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
            return false;
        }
           
        if(this.containsPlayer(player)) {
            int price = getPrice(rank, payment);
            boolean successful = Banker.charge(player, price, payment);
            if(successful) {
                player.setRank(rank);
                
                return true;
            } else {
                return false;
            }
            
        }
        
        return false;
    }
    
    /**
     * Returns the maximum rank that can be afforded
     * @param wealth the amount of dollars or credits that are available to spend
     * @param payment the type of currency that would be used
     * @return the maximum rank that can be afforded minus 2
     */
    public int getMaxRankIndex(int wealth, CurrencyType payment) {
        for(int i = 0; i < dollarPrices.length; i++) {
            if(getPrice(i + 2, payment) > wealth) {
                return i - 1;
            }
        }
        return 6;
    }
    
    /**
     * Gets the price of a desired rank of a certain payment type
     * @param rank the desired rank
     * @param payment the payment type for which the price will be retrieved
     * @return the price of a desired rank of in a certain payment type
     */
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
    
    /**
     * @return the coordinates of the dollar buttons in the upgrade menu
     */
    public LinkedList<Integer> getDollarCoordinates(){
        return dollarCoordinates;
    }
    
    /**
     * Sets the coordinates of the dollar buttons in the upgrade menu
     * @param d A linked list containing the new coordinates
     */
    public void setDollarCoordinates(LinkedList<Integer> d){
        dollarCoordinates = d;
    }
    
    /**
     * Gets the coordinates of the credit buttons in the upgrade menu
     * @return A linked list containing the coordinates
     */
    public LinkedList<Integer> getCreditCoordinates(){
        return creditCoordinates;
    }
    
    /**
     * Sets the coordinates of the credit buttons in the upgrade menu
     * @param c A linked list containing the new coordinates
     */
    public void setCreditCoordinates(LinkedList<Integer> c){
        creditCoordinates = c;
    }
        
}

