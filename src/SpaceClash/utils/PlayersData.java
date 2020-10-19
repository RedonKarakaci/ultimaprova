package SpaceClash.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class PlayersData {

    //---------------------------------------------------------------
    // STATIC FIELDS
    //---------------------------------------------------------------
    private static PlayersData instance = null;

    //---------------------------------------------------------------
    // INSTANCE ATTRIBUTES
    //---------------------------------------------------------------
    private LinkedList<Player> playerLst;

    private PlayersData() {
        this.playerLst = new LinkedList<Player>();
    }

    //---------------------------------------------------------------
    // PUBLIC INSTANCE METHODS
    //---------------------------------------------------------------
    public void add(Player player) {
        this.playerLst.add(player);
    }

    public LinkedList<Player> getListOfPlayers() {
        return this.playerLst;
    }

    public void getReorderedListOfPlayers() {
        Collections.sort(playerLst);
    }

    public int highscore(){
        int n=0;
        for (Player player : playerLst) {
            if (player.getScore() > n) {
                n = player.getScore();
            }
        }
        return n;
    }

    public void clearList(){
        playerLst.clear();
    }

    public int getNumberOfPlayers(){
        return playerLst.size();
    }
    //---------------------------------------------------------------
    // STATIC METHODS
    //---------------------------------------------------------------
    public static PlayersData getInstance() {
        if (instance == null)
            instance = new PlayersData();
        return instance;
    }

}
