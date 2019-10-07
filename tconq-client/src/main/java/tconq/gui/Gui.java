package tconq.gui;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;


import org.joml.Vector2f;
import org.joml.Vector3f;

import tconq.io.Input;
import tconq.io.Window;
import tconq.render.*;
import tconq.worldmap.Map;

public class Gui {
	private Shader shader;
	private Camera camera;
	private TileSheet sheet;
	
	private Selector select;
	private Window window;
	
	public Gui(Window window, Map world) {
		this.window = window;
		this.shader = new Shader("gui");
		sheet = new TileSheet("select.png", 2);
		camera = new Camera(window.getWidth(), window.getHeight());
		select = new Selector(world, camera);
	}
	
	public void resizeCamera(Window window) {
		camera.setProjection(window.getWidth(), window.getHeight());
	}

	public Camera getCamera(){
		return this.camera;
	}
	
	public void update(Input input) {
		select.update(this.window);
		if (input.isKeyDown(GLFW_KEY_A)){
			camera.getPosition().sub(new Vector3f(-5, 0,0));
		}

		if (input.isKeyDown(GLFW_KEY_D)){
			camera.getPosition().sub(new Vector3f(5, 0,0));
		}

		if (input.isKeyDown(GLFW_KEY_W)){
			camera.getPosition().sub(new Vector3f(0, 5,0));
		}

		if (input.isKeyDown(GLFW_KEY_S)){
			camera.getPosition().sub(new Vector3f(0, -5,0));
		}
	}
	
	public void render() {
		shader.bind();
		select.render(camera, sheet, shader);
	}
}