package tconqserver.tconqserv.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
class PlayerCommandLineRunner implements CommandLineRunner{
    @Override
    public void run(String... args) throws Exception{

    }
}

@Data
@Entity
class Player{
    private @Id @GeneratedValue Long id;
    private String name;
    private Integer gold;
    private boolean playing;
    private Integer points;
    
    public Player(String name, Integer gold, boolean playing, Integer points){
        super();
        this.name = name;
        this.gold = gold;
        this.playing = playing;
        this.points = points;
    }
    public Player(){

    }
    public Long getId(){
        return id;
    }

    public void setId(Long id2) {
        this.id = id2;
    }
    
    public String getPlayerName(){
        return name;
    }
    @Override
    public String toString(){
        return "Player [ id = "+ id + ", name = " + name + ", gold = " + gold + ", playing = " + playing + ", points = " + points + " ]";
    }
	public String getName() {
		return this.name;
	}
	public Integer getGold() {
		return this.gold;
	}
	public boolean isPlaying() {
		return this.playing;
	}
	public Integer getPoints() {
		return this.getPoints();
	}
	public void setName(String name2) {
        this.name = name2;
	}
	public void setGold(Integer gold2) {
        this.gold = gold2;
	}
	public void setPlaying(boolean playing2) {
        this.playing = playing2;
	}
	public void setPoints(Integer points2) {
        this.points = points2;
    }
}