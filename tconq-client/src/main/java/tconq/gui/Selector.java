package tconq.gui;

import org.joml.Vector2f;

import tconq.collision.AABB;
import tconq.collision.Collision;
import tconq.io.Input;
import tconq.worldmap.Map;



public class Selector {

    public static final int STATE_IDLE = 0;
	public static final int STATE_SELECTED = 1;
	public static final int STATE_CLICKED = 2;
	
    private int selectedState;
    
    public Selector(Vector2f position, Vector2f scale) {
		//this.boundingBox = new AABB(position, scale);
		selectedState = STATE_IDLE;
	}

	// public void update(Input input, Map world) {
	// 	Collision data = 
		
	// 	if (data.isIntersecting) {
	// 		selectedState = STATE_SELECTED;
			
	// 		if (input.isMouseButtonDown(0)) {
	// 			selectedState = STATE_CLICKED;
	// 		}
	// 	}
	// 	else selectedState = STATE_IDLE;
	// }

	//world.getTileBoundingBox((int) (((transform.pos.x / 2) + 0.5f) - (5 / 2)) + i, (int) (((-transform.pos.y / 2) + 0.5f) - (5 / 2)) + j);
}