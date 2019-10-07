package tconq;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;

import tconq.assets.Assets;
import tconq.gui.Gui;
import tconq.io.Timer;
import tconq.render.Camera;
import tconq.render.Shader;
import tconq.io.Window;
import tconq.worldmap.TileRender;
import tconq.worldmap.Map;

import lwjgui.LWJGUI;
import lwjgui.geometry.Insets;
import lwjgui.geometry.Pos;
import lwjgui.paint.Color;
import lwjgui.scene.Scene;
import lwjgui.scene.control.Label;
import lwjgui.scene.layout.BorderPane;
import lwjgui.scene.layout.VBox;


public class App {
	private final int WINDOWX = 1280;
	private final int WINDOWY = 960;

	private static Window window;
	private static lwjgui.scene.Window lwjguiWindow;

	private static void showCursorCoordinates(Map world, Camera cam){
		//AABB box = world.getTileBoundingBox((int) (pos.x), (int) (pos.y));
		// BorderPane bd = (BorderPane) lwjguiWindow.getScene().getRoot();
		// VBox vbox = (VBox)bd.getChildren().get(0);
		// VBox vboxtop = (VBox)vbox.getChildren().get(0);
		// Label label1 = (Label) vboxtop.getChildren().get(0);
		// String s = "null";
		// if (box != null){
		// 	s = box.toString();
		// }else{
		// 	s = "null";
		// }
		// label1.setText( s + "camera x."+cam.getPosition().x + " y."+cam.getPosition().y);
	}
	

	private static void addComponents(Scene scene) {
		// Create a simple pane
		BorderPane pane = new BorderPane();

		pane.setPadding(new Insets(6));
		pane.setBackground(null);
		// Frame
		// Set the pane as the scenes root
		scene.setRoot(pane);

		{
			VBox vbox = new VBox();
			vbox.setAlignment(Pos.BOTTOM_LEFT);
			vbox.setBackground(Color.BLUE.alpha(0.4f));
			vbox.setMinSize(window.getWidth() - 10, 64);
			//vbox.setPadding(new Insets(0, 10, 10, 0));
			pane.setTop(vbox);

			Label label1 = new Label("Gold"); //TODO: write income and balance
			label1.setTextFill(Color.YELLOW);
			label1.setFontSize(32);
			vbox.getChildren().add(label1);
			int i = 0;
		}
		
	 }

	public void run() {
		Window.setCallbacks();
		
		if (!glfwInit()) {
			System.err.println("GLFW Failed to initialize!");
			System.exit(1);
		}
		
		window = new Window();
		window.setSize(WINDOWX, WINDOWY);
		window.setFullscreen(false);
		window.createWindow("Territory Conquest");
		
		GL.createCapabilities();
		
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glEnable(GL_TEXTURE_2D);
		
		TileRender tiles = new TileRender();
		Assets.initAsset();

	    lwjguiWindow = LWJGUI.initialize(window.getWindow());
		lwjguiWindow.setWindowAutoClear(false); // We must call glClear ourselves.
		lwjguiWindow.setWindowAutoDraw(false); // We must call glfwSwapBuffers ourselves.
		lwjguiWindow.show(); // Display window if it's invisible.
		// Add some components
		addComponents(lwjguiWindow.getScene());
		
		
		// float[] vertices = new float[] {
		// -1f, 1f, 0, //TOP LEFT 0
		// 1f, 1f, 0, //TOP RIGHT 1
		// 1f, -1f, 0, //BOTTOM RIGHT 2
		// -1f, -1f, 0,//BOTTOM LEFT 3
		// };
		//
		// float[] texture = new float[] {
		// 0,0,
		// 1,0,
		// 1,1,
		// 0,1,
		// };
		//
		// int[] indices = new int[] {
		// 0,1,2,
		// 2,3,0
		// };
		//
		// Model model = new Model(vertices, texture, indices);
		Shader shader = new Shader("shader");
		
		Map world = new Map("test_level");
		world.calculateView(window);
		Gui gui = new Gui(this.window, world);
		
		//Gui gui = new Gui(window);
		
		double frame_cap = 1.0 / 60.0;
		
		double frame_time = 0;
		int frames = 0;
		
		double time = Timer.getTime();
		double unprocessed = 0;
		
		while (!window.shouldClose()) {
			boolean can_render = false;
			
			double time_2 = Timer.getTime();
			double passed = time_2 - time;
			unprocessed += passed;
			frame_time += passed;
			
			time = time_2;


			
			
			while (unprocessed >= frame_cap) {
				if (window.hasResized()) {
					gui.resizeCamera(window);
					world.calculateView(window);
					glViewport(0, 0, window.getWidth(), window.getHeight());
				}
				
				unprocessed -= frame_cap;
				can_render = true;
				
				if (window.getInput().isKeyReleased(GLFW_KEY_ESCAPE)) {
					glfwSetWindowShouldClose(window.getWindow(), true);
				}
				
				gui.update(window.getInput());

				world.update((float) frame_cap, window, gui.getCamera());
				
				world.correctCamera(gui.getCamera(), window);
				
				window.update();
				
				if (frame_time >= 1.0) {
					frame_time = 0;
					System.out.println("FPS: " + frames);
					frames = 0;
				}
			}
			if (can_render) {
				glClear(GL_COLOR_BUFFER_BIT);
				
				// shader.bind();
				// shader.setUniform("sampler", 0);
				// shader.setUniform("projection",
				// camera.getProjection().mul(target));
				// model.render();
				// tex.bind(0);
				gui.update(window.getInput());
				world.render(tiles, shader, gui.getCamera());
				gui.render();
				//gui.render();
				showCursorCoordinates(world, gui.getCamera());

				LWJGUI.render();
				
				window.swapBuffers();
				frames++;
			}
		}
		
		Assets.deleteAsset();
		
		glfwTerminate();
	}
	
	public static void main(String[] args) {
		new App().run();
	}

	
	
}