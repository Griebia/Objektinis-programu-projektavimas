package tconq.entity;

public class Player {
    private Long id;
    private String name;
    private int gold;
    private boolean playing;
    private int points;
    private String playerName;

    public Player(){

    }

    public Player(Long id, String name, int gold, boolean playing, int points, String playerName){
        this.id = id;
        this.name = name;
        this.gold = gold;
        this.playing = playing;
        this.points = points;
        this.playerName = playerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public boolean getPLaying(){
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
