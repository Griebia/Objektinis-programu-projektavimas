package tconq.worldmap;

public abstract class TylePrototype implements Cloneable { 
    public static byte not = 0;

    private byte id;
	private boolean solid;
    private String texture;
    
    public TylePrototype clone(){
        try {
            return (TylePrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return this;
        }
    }
}