package tconq.worldmap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import lwjgui.event.EventHandler;
import lwjgui.event.MouseEvent;
import tconq.App;
import tconq.collision.AABB;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.strategy.MediumToStrong;
import tconq.entity.strategy.WeakToMedium;
import tconq.gui.Selector;
import tconq.io.Window;
import tconq.render.Camera;
import tconq.render.Shader;



public class Map {
	private int viewX;
	private int viewY;
	private byte[] tiles;
	private AABB[] bounding_boxes;
	// private AABB selectedTile;
	private static List<IEntity> entities;
	private static List<IEntity> entitiesOpponent;
	private int width;
	private int height;
	private int scale;

	private Matrix4f Map;

	private final String playerURL = "http://localhost:8080/Players/";
	HttpURLConnection playerCon;



	//public static Long opponentId = 1l; // change 2l to 1l in opponents instance
	// private static AbstractEntityFactory entityFactory;

	public Map(String Map) {
		try {
			URI tilesmap = getClass().getResource("/levels/" + Map + "/tiles.png").toURI();
			// URI entitymap = getClass().getResource("/levels/" + Map +
			// "/entities.png").toURI();
			BufferedImage tile_sheet = ImageIO.read(new File(tilesmap));
			// BufferedImage entity_sheet = ImageIO.read(new File(entitymap));

			width = tile_sheet.getWidth();
			height = tile_sheet.getHeight();
			scale = 32;

			this.Map = new Matrix4f().setTranslation(new Vector3f(0));
			this.Map.scale(scale);

			int[] colorTileSheet = tile_sheet.getRGB(0, 0, width, height, null, 0, width);
			// int[] colorEntitySheet = entity_sheet.getRGB(0, 0, width, height, null, 0,
			// width);

			tiles = new byte[width * height];
			bounding_boxes = new AABB[width * height];
			entities = new ArrayList<>();
			entitiesOpponent = new ArrayList<>();

			// TransformTc transform;

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					int red = (colorTileSheet[x + y * width] >> 16) & 0xFF;
					// int entity_index = (colorEntitySheet[x + y * width] >> 16) & 0xFF;
					// int entity_alpha = (colorEntitySheet[x + y * width] >> 24) & 0xFF;

					Tile t;
					try {
						t = Tile.tiles[red];
					} catch (ArrayIndexOutOfBoundsException e) {
						t = null;
					}

					if (t != null)
						setTile(t, x, y);

					// if (entity_alpha > 0) {
					// transform = new TransformTc();
					// transform.pos.x = x * 2;
					// transform.pos.y = -y * 2;
					// switch (entity_index) {
					// case 1 : // Player
					// Player player = new Player(transform);
					// entities.add(player);
					// camera.getPosition().set(transform.pos.mul(-scale, new Vector3f()));
					// break;
					// default :
					// break;
					// }
					// }
				}
			}

		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static void addEntity(IEntity entity, Long playerId) {
		entity.setPlayerId(playerId);

		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println(entity.getMovement());
		System.out.println(entity.getAttack(entity));
		System.out.println(entity.getDestroyBuilding(entity));
		System.out.println("-------------------------------------------------------------------------------------");

		entities.add(entity);
	}

	public static void addEntityOpponent(IEntity entity, Long playerId) {
		entity.setPlayerId(playerId);
		entitiesOpponent.add(entity);
	}

	public IEntity getEntity(Vector3f pos) {
		for (IEntity ent : entities) {
			if (ent.getPos().pos.equals(pos))
				return ent;
		}
		return null;
	}

	public static boolean removeEntity(Vector3f pos) {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getPos().pos.equals(pos)) {
				entities.remove(i);
				return true;
			}
		}
		return false;
	}

	public List<IEntity> getEntities() {
		return entities;
	}

	public Map() {
		width = 64;
		height = 64;
		scale = 16;

		tiles = new byte[width * height];
		bounding_boxes = new AABB[width * height];

		Map = new Matrix4f().setTranslation(new Vector3f(0));
		Map.scale(scale);
	}

	public void calculateView(Window window) {
		viewX = (window.getWidth() / (scale * 2)) + 4;
		viewY = (window.getHeight() / (scale * 2)) + 4;
	}

	public Matrix4f getWorldMatrix() {
		return Map;
	}

	public void render(TileRender render, Shader shader, Camera cam) {
		int posX = (int) cam.getPosition().x / (scale * 2);
		int posY = (int) cam.getPosition().y / (scale * 2);

		for (int i = 0; i < viewX; i++) {
			for (int j = 0; j < viewY; j++) {
				Tile t = getTile(i - posX - (viewX / 2) + 1, j + posY - (viewY / 2));
				if (t != null)
					render.renderTile(t, i - posX - (viewX / 2) + 1, -j - posY + (viewY / 2), shader, Map, cam);
			}
		}

		for (IEntity entity : entities) {
			entity.render(shader, cam, this);
		}
	}

	public void update(float delta, Window window, Camera camera) {
		for (IEntity entity : entities) {
			entity.update(delta, window, camera, this);
		}

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).collideWithTiles(this);
			for (int j = i + 1; j < entities.size(); j++) {
				entities.get(i).collideWithEntity(entities.get(j));
			}
			entities.get(i).collideWithTiles(this);
		}
	}

	public void correctCamera(Camera camera, Window window) {
		Vector3f pos = camera.getPosition();

		int w = -width * scale * 2;
		int h = height * scale * 2;

		if (pos.x > -(window.getWidth() / 2) + scale)
			pos.x = -(window.getWidth() / 2) + scale;
		if (pos.x < w + (window.getWidth() / 2) + scale)
			pos.x = w + (window.getWidth() / 2) + scale;

		if (pos.y < (window.getHeight() / 2) - scale)
			pos.y = (window.getHeight() / 2) - scale;
		if (pos.y > h - (window.getHeight() / 2) - scale)
			pos.y = h - (window.getHeight() / 2) - scale;
	}

	public void setTile(Tile tile, int x, int y) {
		tiles[x + y * width] = tile.getId();
		if (tile.isSelectable()) {
			bounding_boxes[x + y * width] = new AABB(new Vector2f(x * 2, -y * 2), new Vector2f(1, 1));
		} else {
			bounding_boxes[x + y * width] = null;
		}
	}

	public Tile getTile(int x, int y) {
		try {
			return Tile.tiles[tiles[x + y * width]];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public AABB getEntityBoundungBox(int x, int y) {
		for (IEntity var : entities) {
			int tcx = (int) Math.floor(var.getPos().pos.x / 2);
			int tcy = (int) Math.floor(var.getPos().pos.y / 2);
			if (tcx == x && tcy == -y) {
				return var.getEntityBoundingBox();
			}
		}
		return null;
	}

	public AABB getTileBoundingBox(int x, int y) {
		try {
			return bounding_boxes[x + y * width];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public int getScale() {
		return scale;
	}

	// after pressing end turn button passes all netities to server
	public static EventHandler<MouseEvent> endTurnPressed() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				final String uri = "http://localhost:8080/SEntities";
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);

				ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

				for (IEntity ent : entities) {
					if (ent.getPlayerId() == App.playerID) {
						HashMap<String, Object> map = new HashMap<>();

						String type = "";
						if (ent.getEntityClass(ent).getSimpleName().contains("Unit"))
							type = ent.getEntityClass(ent).getSimpleName().replace("Unit", "").toUpperCase();
						else
							type = ent.getEntityClass(ent).getSimpleName().toUpperCase();

						// map.put("id", ent.getId());
						map.put("type", type);
						map.put("x", (int) ent.getPos().pos.x);
						map.put("y", (int) ent.getPos().pos.y);
						map.put("player", App.playerID);
						list.add(map);
					}
				}

				/*
				 * HashMap<String, Object> map = new HashMap<>(); map.put("type", "STRONG");
				 * map.put("x", 3); map.put("y", 4); map.put("player", 1); list.add(map);
				 * HashMap<String, Object> map1 = new HashMap<>(); map1.put("type", "STRONG");
				 * map1.put("x", 1); map1.put("y", 2); map1.put("player", 1); list.add(map1);
				 */

				// build the request
				HttpEntity<ArrayList<HashMap<String, Object>>> entity = new HttpEntity<>(list, headers);

				RestTemplate restTemplate = new RestTemplate();
				restTemplate.postForObject(uri, entity, String.class);

				//-----------------------NEXT TURN STUFF--------------------------
				final String uriPlayer = "http://localhost:8080/NextTurn/" + App.playerID.toString();

				// create a map for post parameters
				HashMap<String, Object> playerMap = new HashMap<>();
				playerMap.put("nextTurn","true");
				playerMap.put("id",App.playerID);

				// build the request
				HttpEntity<HashMap<String, Object>> palyerEntity = new HttpEntity<>(playerMap, headers);

				RestTemplate restTemplatePlayer = new RestTemplate();
				restTemplatePlayer.postForObject(uriPlayer, palyerEntity, String.class);

				//fromDbToMap(App.getEntities(opponentId));
			}
		};
	}

	public static void fromDbToMap(String dbEntities, Long opponentId) { // gets entities from database and adds them to map
		JSONArray jsonArray = new JSONArray(dbEntities); // changes string to jsonArray
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonEntity = jsonArray.getJSONObject(i); // gets one json onject form array

			TransformTc tc = new TransformTc();
			tc.pos.x = jsonEntity.getFloat("x");
			tc.pos.y = jsonEntity.getFloat("y");
			String entityType = new StringBuilder().append(jsonEntity.getString("type")).append("unit").toString();
			IEntity Ient = Selector.entityFactory.getEntity(entityType, tc);
			Ient.setId(jsonEntity.getLong("id"));
			JSONObject player = (JSONObject)jsonEntity.get("player");
			Long playerId = player.getLong("id");

			ArrayList<Long> tempIdList = new ArrayList<Long>();
			if(!entities.isEmpty()){					// checks if local entity list is empty, if yes - adds opponent entity, if no - proceeds with logic
				IEntity upgradedEnt = null;
				for (IEntity entLocal : entities) {		// gets all local entities ids
					tempIdList.add(entLocal.getId());	
					if(entLocal.getId() == Ient.getId())	// gets dublicate entity from local entity list
						upgradedEnt = entLocal;
				}
				if(!tempIdList.contains(Ient.getId()))	// checks if this entity is already in map
					addEntity(Ient, playerId);			// changed addEntity fuction to static bcs it told me to :)
				else{									// if it is, checks if its type has changed	
					String upgradedEntType = upgradedEnt.getEntityClass(upgradedEnt).getSimpleName().toUpperCase();				
					if(!entityType.toLowerCase().equals(upgradedEntType.toLowerCase())){
						for (IEntity entLocal : entities) {	
							if(entLocal.getId() == upgradedEnt.getId()){
								switch(upgradedEntType.toLowerCase())	{
									case "weakunit":
										entLocal.upgrade(Selector.world, new WeakToMedium(), entLocal.getId());
										break;
									case "mediumunit":
										entLocal.upgrade(Selector.world, new MediumToStrong(), entLocal.getId());
										break;
								}							
								break;
							}								
						}
					}
				}
			}
			else
				addEntity(Ient, playerId);
		}			
	}

	/*
	public static EventHandler<MouseEvent> endTurnPressedOpponent() { // replace with some event listener when normal multiplayer is implemented
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {

				// Simulates opponent's actions, delete this when normal multiplayer is implemented
				int x = new Random().nextInt(4)+1;
				for(int i = 0;i < x;i++){
					TransformTc tc = new TransformTc();
					tc.pos.x = (float) new Random().nextInt(50);
					tc.pos.y =  (float)new Random().nextInt(50) - 51;
					IEntity unit = Selector.entityFactory.getEntity("StrongUnit",tc);
					addEntity((Entity)unit, 1l);
				}							

				final String uri = "http://localhost:8080/SEntities";
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);

				ArrayList<Long> opponentIdList = new ArrayList<Long>();

				ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
				for (Entity ent : entities) {		// adds to list all opponents entities (should work even if there are more then one opponent)
					if(ent.getPlayerId() != App.playerID){
						if(!opponentIdList.contains(ent.getPlayerId()))	// adds all opponent ids to a list
							opponentIdList.add(ent.getPlayerId());

						HashMap<String, Object> map = new HashMap<>();

						String type = "";
						if (ent.getClass().getSimpleName().contains("Unit"))
							type = ent.getClass().getSimpleName().replace("Unit", "").toUpperCase();
						else
							type = ent.getClass().getSimpleName().toUpperCase();

						map.put("id", entityId++);
						map.put("type", type);
						map.put("x", (int) ent.getPos().pos.x);
						map.put("y", (int) ent.getPos().pos.y);
						map.put("player", ent.getPlayerId());
						list.add(map);
					}					
				}
				 
				// build the request
				HttpEntity<ArrayList<HashMap<String, Object>>> entity = new HttpEntity<>(list, headers);

				RestTemplate restTemplate = new RestTemplate();
				restTemplate.postForObject(uri, entity, String.class);

				//-------------------------------------------------
				//entitiesOpponent.clear();
				//for(Long id: opponentIdList)
				//	fromDbToMap(App.getEntities(id));		// adds all opponent entities to the map
					
			}
		};
		
	}
	*/
}