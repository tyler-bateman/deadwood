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
public class CastingOffice extends Space{
    private int[] dollarPrices;
    private int[] creditPrices;
    
    public CastingOffice(){
        
    }
    
    public boolean purchaseRank(Player player, int rank) {
        return false;
    }
    
    public int getDollarPrice(int rank) {
        return 0;
    }
    
    public int getCreditPrice(int rank) {
        return 0;
    }
}
