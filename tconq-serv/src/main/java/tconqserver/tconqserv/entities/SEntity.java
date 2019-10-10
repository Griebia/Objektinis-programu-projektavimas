package tconqserver.tconqserv.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
class SEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private enum Type {
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
        id = 0;
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

    public int getX(){return this.x;}
    public int getY(){return this.y;}

    
}