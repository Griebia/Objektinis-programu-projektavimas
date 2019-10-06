package tconq.entity;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import tconq.io.Window;
import tconq.render.Animation;
import tconq.render.Camera;
import tconq.worldmap.Map;

public class Unit extends Entity {

    public Unit(TransformTc transform) {
		super("texture", transform); //TODO:set textures
	}
	
	@Override
	public void update(float delta, Window window, Camera camera, Map world) {
	}
}