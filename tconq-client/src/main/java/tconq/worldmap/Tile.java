package tconq.worldmap;

import tconq.bridge.*;

public class Tile {
	public static byte not = 0;
	
	private byte id;
	private boolean solid;
	private String texture;

	public Tile(TextureType texture) {
		this.id = not;
		not++;
		this.texture = texture.getName();
		this.solid = false;
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