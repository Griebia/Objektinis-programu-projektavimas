package tconqserver.tconqserv.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public @Id @GeneratedValue Long id;
    public enum Type {
        HOUSE,
        TOWER,
        CASTLE,
        WEAK,
        MEDIUM,
        STRONG
    }
    Type type;
    //Position on the map
    int x;
    int y;
    
    @ManyToOne
    @JoinColumn(name="player_id")
    private Player player;

    public SEntity(){
//        id = 0;
        x = 0;
        y = 0;
    }

    public SEntity(long id, int x, int y, Type type){
        this.id = id;
        this.type = type;
    }

    public void setPos(int x, int y){
        x = this.x;
        y = this.y;
    }

    public void setId(Long id) {this.id = id;}

    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public Long getId(){return this.id;}

    
}