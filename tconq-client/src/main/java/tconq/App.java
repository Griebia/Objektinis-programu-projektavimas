package tconq;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.lwjgl.opengl.GL;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import lwjgui.LWJGUI;
import lwjgui.geometry.Insets;
import lwjgui.geometry.Pos;
import lwjgui.paint.Color;
import lwjgui.scene.Scene;
import lwjgui.scene.control.Button;
import lwjgui.scene.control.Label;
import lwjgui.scene.layout.BorderPane;
import lwjgui.scene.layout.VBox;
import tconq.assets.Assets;
import tconq.entity.Player;
import tconq.gui.Gui;
import tconq.io.Timer;
import tconq.io.Window;
import tconq.render.Camera;
import tconq.render.Shader;
import tconq.worldmap.Map;
import tconq.worldmap.TileRender;

//import java.net.http.HttpRequest;


@EnableWebMvc
@SpringBootApplication
public class App {
	public static Long playerID = 0L;

	private final int WINDOWX = 1280;
	private final int WINDOWY = 960;

	private static Window window;
	private static lwjgui.scene.Window lwjguiWindow;

	private static Boolean sendRequest = true;

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


			//end turn button
			VBox vbButtons = new VBox();
			vbButtons.setAlignment(Pos.CENTER_LEFT);
			vbButtons.setBackground(Color.TRANSPARENT);
			vbButtons.setMinSize(window.getWidth() - 10, 64);
			pane.setBottom(vbButtons);

			Button endTurn = new Button("End turn");
			endTurn.setFontSize(32);
			endTurn.setMinSize(128, 64);
			endTurn.setOnMouseReleased(Map.endTurnPressed());
			vbButtons.getChildren().add(endTurn);

			//undo turn button
			VBox vbButtonUndo = new VBox();
			vbButtonUndo.setAlignment(Pos.CENTER_LEFT);
			vbButtonUndo.setBackground(Color.TRANSPARENT);
			vbButtonUndo.setMinSize(window.getWidth() - 10, 64);
			pane.setCenter(vbButtonUndo);

			Button undo = new Button("Undo move");
			undo.setFontSize(32);
			undo.setMinSize(128, 64);
			undo.setOnMouseReleased(Map.endTurnPressed());
			vbButtonUndo.getChildren().add(undo);

		}
		
	 }


	public void run() {
		Long startTime = System.currentTimeMillis();

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

			endTurnLogic();
			/*long estimatedTime = (System.currentTimeMillis() - startTime) / 1000;
			if((estimatedTime & 2) != 0 && sendRequest == true){
				sendRequest = false;
				endTurnLogic();
			}
			if((estimatedTime & 2) == 0)
				sendRequest = true;*/
			
		}
		
		Assets.deleteAsset();
		
		glfwTerminate();

		
			
	}



	public void endTurnLogic(){		// puts all opponents entities to map when they've ended theyr turn
		String opponents = getOpponents();
		ArrayList<Long> opponentIds = getOpponentIds(opponents);

		if(checkForNextTurns(opponentIds) == true){						
			for (Long oppId : opponentIds) {
				Map.fromDbToMap(getEntities(oppId), oppId);				
			
				// ------------------Change nextTurn value to false--------------------------------
				final String uriPlayer = "http://localhost:8080/NextTurn/" + App.playerID.toString();
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);

				// create a map for post parameters
				HashMap<String, Object> playerMap = new HashMap<>();
				playerMap.put("nextTurn","false");
				playerMap.put("id", oppId);

				// build the request
				HttpEntity<HashMap<String, Object>> palyerEntity = new HttpEntity<>(playerMap, headers);

				RestTemplate restTemplatePlayer = new RestTemplate();
				restTemplatePlayer.postForObject(uriPlayer, palyerEntity, String.class);
			}
		}
	}

	public static Boolean checkForNextTurns(ArrayList<Long> opponentIds){
		
		for (Long oppId : opponentIds) {
			final String uri = "http://localhost:8080/Player/" + oppId.toString();
			RestTemplate  restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(uri, String.class);	
			JSONObject jsonEntity = new JSONObject(result);
			
			if(jsonEntity.getBoolean("nextTurn") == false)		// checks if opponent kas ended his turn
				return false;
			else
				return true;
		}
		
		return false;
	}

	public static String getOpponents(){
		final String uri = "http://localhost:8080/Players/" + playerID.toString();
		RestTemplate  restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);


//		System.out.println(result);



		return result;
	}

	public static ArrayList<Long> getOpponentIds(String opponents){
		JSONArray jsonArray = new JSONArray(opponents); // changes string to jsonArray
		ArrayList<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonEntity = jsonArray.getJSONObject(i); // gets one json onject form array
			ids.add(jsonEntity.getLong("id"));
		}
		return ids;
	}

	public static String getEntities(Long playerId){
		final String uri = "http://localhost:8080/SEntities/" + playerId.toString();
		RestTemplate  restTemplate = new RestTemplate();
		return restTemplate.getForObject(uri, String.class);
		//System.out.println(result);
	}

	private static void addEntities() {
		final String uri = "http://localhost:8080/SEntities";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// create a map for post parameters
		HashMap<String, Object> map = new HashMap<>();
		map.put("x",5);
		map.put("type","WEAK");
		map.put("y",1);
		map.put("player",2);
		HashMap<String, Object> map2 = new HashMap<>();
		map2.put("x",8);
		map2.put("type","STRONG");
		map2.put("y",4);
		map2.put("player",2);

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		list.add(map);
		list.add(map2);

		// build the request
		HttpEntity<ArrayList<HashMap<String, Object>>> entity = new HttpEntity<>(list, headers);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(uri, entity, String.class);
	}

	private static void addEntity() {
		final String uri = "http://localhost:8080/SEntity";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// create a map for post parameters
		HashMap<String, Object> map = new HashMap<>();
		map.put("x",12);
		map.put("type","CASTLE");
		map.put("y",10);
		map.put("player",2);

		// build the request
		HttpEntity<HashMap<String, Object>> entity = new HttpEntity<>(map, headers);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(uri, entity, String.class);
	}

	private static void addPlayer(String name){
		final String uri = "http://localhost:8080/Player";

		
		// create headers
		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
		headers.setContentType(MediaType.APPLICATION_JSON);

		// create a map for post parameters
		HashMap<String, Object> map = new HashMap<>();
		map.put("points",1);
		map.put("name",name);
		map.put("gold",2);
		map.put("playing","true");
		map.put("nextTurn","false");

		// build the request
		HttpEntity<HashMap<String, Object>> entity = new HttpEntity<>(map, headers);



		RestTemplate restTemplate = new RestTemplate();
		// send POST request
		ResponseEntity<Player> response = restTemplate.postForEntity(uri, entity, Player.class);

		//sets player id
		playerID = response.getBody().getId();


	}
	
	public static void main(String[] args) {
		//getPlayers();
		addPlayer("arturas");
		//addPlayer("Tadas");
//		addEntity();
		//addEntities();
		
		new App().run();
	}

	
	
}