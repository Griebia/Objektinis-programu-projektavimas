package tconq.gui;

import org.joml.Vector2f;

import tconq.collision.AABB;



public class Selector {

    public static final int STATE_IDLE = 0;
	public static final int STATE_SELECTED = 1;
	public static final int STATE_CLICKED = 2;
	
	private AABB boundingBox;
	
    private int selectedState;
    
    public Selector(Vector2f position, Vector2f scale) {
		this.boundingBox = new AABB(position, scale);
		selectedState = STATE_IDLE;
	}
}