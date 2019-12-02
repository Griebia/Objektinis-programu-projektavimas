package tconq.worldmap;

import java.util.Hashtable;

import tconq.bridge.Grass;
import tconq.bridge.TextureColorBlue;
import tconq.bridge.TextureColorDefault;
import tconq.bridge.TextureColorYellow;
import tconq.bridge.Water;

public class TileFactory {
    private static Hashtable<Integer, Tile> hash = new Hashtable<>();

    public Tile getTile(int id){
        Tile t = hash.get(id);
        if(t == null){
            switch(id){
                case 0 :
                t = new Tile(new Water( new TextureColorDefault() ) );
                break;
                case 1 :
                t = new Tile(new Grass( new TextureColorDefault() ));
                break;
                case 2 :
                t = new Tile( new Grass( new TextureColorYellow() ));
                break;
                case 3 :
                t = new Tile(new Grass( new TextureColorBlue() ));
                break;
                default:
                t = new Tile(new Water( new TextureColorDefault() ) );

            }
            hash.put(id, t);   

        }

        return t;
    }

    public int getHashLenght(){
        return hash.size();
    }
}