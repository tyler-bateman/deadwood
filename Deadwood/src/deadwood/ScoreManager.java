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
    
    
    /**
     * Decides on a winner at the end of the game
     * @param playerList the list of players
     */
    public static void declareWinner(Player[] playerList){  //ends the game
        calculateScores(playerList);
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
    
    /**
     * Calculates the score of each player in the list
     * @param playerList An array containing the players
     * @return the updated array of player scores
     */
    public static int[] calculateScores(Player[] playerList) {
        for(Player p: playerList) {
            calculateScore(p);
        }
        return getPlayerScores();
    }
    
    /**
     * Calculates the score of a given player
     * @param p the player whose score is to be calculated
     * returns the new score for the player
     */
    public static int calculateScore(Player p) {
        return playerScores[p.getID()] = p.getCredits() + p.getDollars() + p.getRank() * 5;
    }
    
    
    /**
     * Gets the array containing the player scores
     * @return the array containing the scores
     */
    public static int[] getPlayerScores(){
        return playerScores;
    }
    
    /**
     * Gets the score of a specific player
     * @param ID the ID of the player, i.e. their index in the player array
     * @return the score of given player
     */
    public static int getScore(int ID) {
        return playerScores[ID];
    }
    
    /**
     * Gets the score of a specific player
     * @param p the player whose score will be retrieved
     * @return the score of the given player
     */
    public static int getScore(Player p) {
        return playerScores[p.getID()];
    }
    
}
