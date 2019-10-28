package tconq.worldmap;

import tconq.bridge.*;

public class Tile {
	public static Tile tiles[] = new Tile[255];
	public static byte not = 0;
	
	public static final Tile water = new Tile( new Water( new TextureColorDefault() ) );
	public static final Tile grass = new Tile( new Grass( new TextureColorDefault() ) );//.setSelectable();
	public static final Tile yellowgrass = new Tile( new Grass( new TextureColorYellow() ) ).setSelectable();
	public static final Tile bluegrass = new Tile( new Grass( new TextureColorBlue() ) ).setSelectable();
	
	private byte id;
	private boolean solid;
	private String texture;

	public Tile(TextureType texture) {
		this.id = not;
		not++;
		this.texture = texture.getName();
		this.solid = false;
		if (tiles[id] != null) throw new IllegalStateException("Tiles at [" + id + "] is already being used!");
		tiles[id] = this;
	}
	
	public Tile setSelectable() {
		this.solid = true;
		return this;
	}
	
	public boolean isSelectable() {
		return solid;
	}
	
	public byte getId() {
		return id;
	}
	
	public String getTexture() {
		return texture;
	}
}