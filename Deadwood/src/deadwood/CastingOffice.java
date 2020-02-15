
package deadwood;
import java.lang.ArrayIndexOutOfBoundsException;
/**
 *
 * @author tyler
 */
public class CastingOffice extends Space{
    private int[] dollarPrices;
    private int[] creditPrices;
    
    public CastingOffice(){
        dollarPrices = new int[]{4, 10, 18, 28, 40};
        creditPrices = new int[]{5, 10, 15, 20, 25};
    }
    
    public boolean purchaseRank(Player player, int rank) {
        //Todo
        return false;
    }
    
    public int getDollarPrice(int rank) {
        
        try{
            return dollarPrices[rank - 2];
        }
        catch(ArrayIndexOutOfBoundsException e) {
            return Integer.MAX_VALUE;
        }
        
    }
    
    public int getCreditPrice(int rank) {
        try{
            return creditPrices[rank - 2];
        }
        catch(ArrayIndexOutOfBoundsException e) {
            return Integer.MAX_VALUE;
        }
        
    }
}

