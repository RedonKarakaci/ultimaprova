package SpaceClash.utils;
public class Player implements Comparable<Player>{

    //---------------------------------------------------------------
    // INSTANCE ATTRIBUTES
    //---------------------------------------------------------------
    private String playerName;
    private int score;

    public Player(String playerName, int score){
        this.playerName=playerName;
        this.score=score;
    }

    //---------------------------------------------------------------
    // PUBLIC INSTANCE METHODS
    //---------------------------------------------------------------

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Player o) {
        int retVal=0;
        if(this.getScore()>o.getScore()) {
            retVal=-1;
        }
        else if (this.getScore()<o.getScore()){
            retVal=1;
        }
        return retVal;
    }

}

