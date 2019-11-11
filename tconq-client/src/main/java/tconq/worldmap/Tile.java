package tconq.worldmap;

import tconq.bridge.*;
import tconq.io.DebugHandler;

public class Tile {
	public TilePrototype tiles[] = new TilePrototype[255];
	
	public static Tile tile = new Tile();
	
	public Tile() {
		if(DebugHandler.debugmode.prototypeDebug) System.out.println("Cloning tiles");
		tiles[0] = TilePrototype.water.clone();
		tiles[1] = TilePrototype.grass.clone();
		tiles[2] = TilePrototype.yellow.clone();
		tiles[3] = TilePrototype.blue.clone();
	}
	

}