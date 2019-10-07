package tconq.gui;

import org.joml.Vector2f;

import tconq.assets.Assets;
import tconq.collision.AABB;
import tconq.collision.Collision;
import tconq.io.Input;
import tconq.io.Window;
import tconq.render.Camera;
import tconq.render.Shader;
import tconq.render.Texture;
import tconq.render.TileSheet;
import tconq.worldmap.Map;



public class Selector {

    public static final int STATE_IDLE = 0;
	public static final int STATE_SELECTED = 1;
	public static final int STATE_CLICKED = 2;
	
	private int selectedState;
	private Map world;
	private Camera camera;
	private AABB boundingBox;
	private Texture texture;
    
    public Selector( Map world2, Camera camera) {
		//this.boundingBox = new AABB(position, scale);
		this.world = world2;
		this.camera = camera;
		selectedState = STATE_IDLE;
	}

	public Vector2f getTileCoordinates(Window window){
		Vector2f v = new Vector2f();
		Vector2f pos = window.getInput().getMousePosition();
		pos.x = (pos.x + 32 - (camera.getPosition().x))/(world.getScale()*2);
		pos.y = (pos.y - 32 - (camera.getPosition().y))/(world.getScale()*-2);
		v.set(pos);
		return v;
	}

	public void update(Window window) {
		Vector2f coords = getTileCoordinates(window);
		AABB data = world.getTileBoundingBox((int)coords.x, (int)coords.y);
		this.boundingBox = data;
		if (data != null) {
			selectedState = STATE_SELECTED;
			//System.out.println(data.getCollision(window.getInput().getMousePosition()).toString());
			if (window.getInput().isMouseButtonDown(0)) {
				selectedState = STATE_CLICKED;

			}
		}
		else selectedState = STATE_IDLE;
	}

	public void render(Camera camera, TileSheet sheet, Shader shader) {
		if (boundingBox != null){
			Vector2f position = boundingBox.getCenter(), scale = boundingBox.getHalfExtent();
		}

		switch (selectedState) {
			case STATE_SELECTED :
				sheet.bindTile(shader, 1, 1);
				break;
			case STATE_CLICKED :
				sheet.bindTile(shader, 2, 1);
				break;
			default :
				sheet.bindTile(shader, 1, 1);
				break;
		}
		Assets.getModel().render();
	}

	private void renderSides(Vector2f position, Vector2f scale, Camera camera, TileSheet sheet, Shader shader) {
		transform.identity().translate(position.x, position.y + scale.y - 16, 0).scale(scale.x, 16, 1); // Top
		
		shader.setUniform("projection", camera.getProjection().mul(transform));
		switch (selectedState) {
			case STATE_SELECTED :
				sheet.bindTile(shader, 4, 0);
				break;
			case STATE_CLICKED :
				sheet.bindTile(shader, 7, 0);
				break;
			default :
				sheet.bindTile(shader, 1, 0);
				break;
		}
		Assets.getModel().render();
		
		transform.identity().translate(position.x, position.y - scale.y + 16, 0).scale(scale.x, 16, 1); // Bottom
		
		shader.setUniform("projection", camera.getProjection().mul(transform));
		switch (selectedState) {
			case STATE_SELECTED :
				sheet.bindTile(shader, 4, 2);
				break;
			case STATE_CLICKED :
				sheet.bindTile(shader, 7, 2);
				break;
			default :
				sheet.bindTile(shader, 1, 2);
				break;
		}
		Assets.getModel().render();
		
		transform.identity().translate(position.x - scale.x + 16, position.y, 0).scale(16, scale.y, 1); // Left
		
		shader.setUniform("projection", camera.getProjection().mul(transform));
		switch (selectedState) {
			case STATE_SELECTED :
				sheet.bindTile(shader, 3, 1);
				break;
			case STATE_CLICKED :
				sheet.bindTile(shader, 6, 1);
				break;
			default :
				sheet.bindTile(shader, 0, 1);
				break;
		}
		Assets.getModel().render();
		
		transform.identity().translate(position.x + scale.x - 16, position.y, 0).scale(16, scale.y, 1); // Right
		
		shader.setUniform("projection", camera.getProjection().mul(transform));
		switch (selectedState) {
			case STATE_SELECTED :
				sheet.bindTile(shader, 5, 1);
				break;
			case STATE_CLICKED :
				sheet.bindTile(shader, 8, 1);
				break;
			default :
				sheet.bindTile(shader, 2, 1);
				break;
		}
		Assets.getModel().render();
	}
	
	private void renderCorners(Vector2f position, Vector2f scale, Camera camera, TileSheet sheet, Shader shader) {
		transform.identity().translate(position.x - scale.x + 16, position.y + scale.y - 16, 0).scale(16, 16, 1); // Top
																																					 // Left
		
		shader.setUniform("projection", camera.getProjection().mul(transform));
		switch (selectedState) {
			case STATE_SELECTED :
				sheet.bindTile(shader, 3, 0);
				break;
			case STATE_CLICKED :
				sheet.bindTile(shader, 6, 0);
				break;
			default :
				sheet.bindTile(shader, 0, 0);
				break;
		}
		Assets.getModel().render();
		
		transform.identity().translate(position.x + scale.x - 16, position.y + scale.y - 16, 0).scale(16, 16, 1); // Top
																																					 // Right
		
		shader.setUniform("projection", camera.getProjection().mul(transform));
		switch (selectedState) {
			case STATE_SELECTED :
				sheet.bindTile(shader, 5, 0);
				break;
			case STATE_CLICKED :
				sheet.bindTile(shader, 8, 0);
				break;
			default :
				sheet.bindTile(shader, 2, 0);
				break;
		}
		Assets.getModel().render();
		
		transform.identity().translate(position.x - scale.x + 16, position.y - scale.y + 16, 0).scale(16, 16, 1); // Bottom
																																					 // Left
		
		shader.setUniform("projection", camera.getProjection().mul(transform));
		switch (selectedState) {
			case STATE_SELECTED :
				sheet.bindTile(shader, 3, 2);
				break;
			case STATE_CLICKED :
				sheet.bindTile(shader, 6, 2);
				break;
			default :
				sheet.bindTile(shader, 0, 2);
				break;
		}
		Assets.getModel().render();
		
		transform.identity().translate(position.x + scale.x - 16, position.y - scale.y + 16, 0).scale(16, 16, 1); // Bottom
																																					 // Right
		
		shader.setUniform("projection", camera.getProjection().mul(transform));
		switch (selectedState) {
			case STATE_SELECTED :
				sheet.bindTile(shader, 5, 2);
				break;
			case STATE_CLICKED :
				sheet.bindTile(shader, 8, 2);
				break;
			default :
				sheet.bindTile(shader, 2, 2);
				break;
		}
		Assets.getModel().render();
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