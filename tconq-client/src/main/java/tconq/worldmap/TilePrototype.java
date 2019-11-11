package tconq.worldmap;

public class TilePrototype implements Cloneable { 
    public static byte not = 0;

    protected byte id;
	protected boolean solid;
    protected String texture;

    public static TilePrototype water = new WaterTile();
    public static TilePrototype grass = new GrassTile();
    public static TilePrototype yellow = new YellowGrassTile();
    public static TilePrototype blue = new BlueGrassTile();
    
    public TilePrototype clone(){
        try {
            return (TilePrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return this;
        }
    }

    public TilePrototype setSelectable() {
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