package tconqserver.tconqserv.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GameSession {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    private String gameName;
    private String password;
    private boolean gameover;

    public GameSession(){

    }

    public GameSession(long id, String gamename, String password, boolean gameover){
        this.id = id;
        this.gameName = gamename;
        this.password = password;
        this.gameover = gameover;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isGameover() {
        return gameover;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }
    
}