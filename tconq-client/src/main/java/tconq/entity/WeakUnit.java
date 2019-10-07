package tconq.entity;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import tconq.assets.Assets;
import tconq.io.Window;
import tconq.render.Animation;
import tconq.render.Camera;
import tconq.render.Shader;
import tconq.worldmap.Map;

public class WeakUnit extends Entity {

    public WeakUnit(TransformTc transform) {
		super("weakWarrior", transform);
	}
	
	@Override
	public void update(float delta, Window window, Camera camera, Map world) {
	}

	@Override
	public void render(Shader shader, Camera camera, Map world) {
		Matrix4f target = camera.getProjection();
		target.mul(world.getWorldMatrix());
		
		shader.bind();
		shader.setUniform("sampler", 0);
		shader.setUniform("projection", transform.getProjection(target));
		this.texture.bind(0);
		Assets.getModel().render();
	}
}