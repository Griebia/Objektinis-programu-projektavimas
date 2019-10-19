package tconqserver.tconqserv.entities;

import javax.persistence.*;

@Entity
@Table(name = "sentities")
public class SEntity {

    private @Id @GeneratedValue Long id;
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
        id = 0l;
        x = 0;
        y = 0;
    }

    public SEntity(Long id, int x, int y, Type type){
        this.id = id;
        this.type = type;
    }

    public void setPos(int x, int y){
        x = this.x;
        y = this.y;
    }

    public void setId(Long id) {this.id = id;}

    public void setPlayer(Player player) {this.player = player;}

    public Type getType() {return this.type;}

    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public Long getId(){return this.id;}

    public Player getPlayer() {return this.player;}

    
}