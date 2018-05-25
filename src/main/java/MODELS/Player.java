package MODELS;

/**
 * Class for player. Set nickname and get score.
 */
public class Player {

    private String nickName;
    private Integer score;

    public Player() {
        this.score = 0;
    }

    public String getNickName() {
        return nickName;
    }

    /**
     * Set player nickname
     * @param nickName set player nickname
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Get player score
     * @return score
     */
    public int getScore() {
        return score;
    }
}
