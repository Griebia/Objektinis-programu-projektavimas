package tconq.entity.factory;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import tconq.assets.Assets;
import tconq.entity.Entity;
import tconq.entity.TransformTc;
import tconq.io.Window;
import tconq.render.Animation;
import tconq.render.Camera;
import tconq.render.Shader;
import tconq.worldmap.Map;

public class WeakUnit extends Entity {

    public WeakUnit(TransformTc transform) {
		super("weakWarrior.png", transform);
	}
	
	@Override
	public void update(float delta, Window window, Camera camera, Map world) {
	}
}