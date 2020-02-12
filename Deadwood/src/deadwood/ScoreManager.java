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
public class ScoreManager {
    private static int[] playerScores = new int[8];
    
    
    public static void declareWinner(){  //ends the game
        calculateScores();
        int maxScore = 0;
        LinkedList<Integer> winningPlayerIDs = new LinkedList<Integer>();
        for(int i = 0; i < playerScores.length; i++) {
            if(playerScores[i] == maxScore) {
                winningPlayerIDs.add(i);
            } else if(playerScores[i] > maxScore) {
                winningPlayerIDs = new LinkedList<Integer>();
                winningPlayerIDs.add(i);
            }
        }
        //TODO: Notify controller
    }
    
    public static void calculateScores() {
        for(Player p: Board.getPlayers()) {
            calculateScore(p);
        }
    }
    
    public static void calculateScore(Player p) {
        playerScores[p.getID()] = p.getCredits() + p.getDollars() + p.getRank() * 5;
    }
    
               
    public static int[] getPlayerScores(){
        return playerScores;
    }
    
    public static int getScore(int ID) {
        return playerScores[ID];
    }
    
    public static int getScore(Player p) {
        return playerScores[p.getID()];
    }
    
}
