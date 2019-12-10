package tconq.gui;

import org.joml.Vector2f;

import tconq.App;
import tconq.assets.Assets;
import tconq.collision.AABB;
import tconq.entity.*;
import tconq.entity.chain_of_responsibility.Null;
import tconq.entity.factory.*;
import tconq.entity.proxy.EntityProxy;
import tconq.entity.strategy.HouseToTower;
import tconq.entity.strategy.MediumToStrong;
import tconq.entity.strategy.TowerToCastle;
import tconq.entity.strategy.WeakToMedium;
import tconq.entity.visitor.DeathTax;
import tconq.entity.visitor.HouseIncome;
import tconq.entity.visitor.WarriorTax;
import tconq.interpreter.Context;
import tconq.interpreter.Gold;
import tconq.interpreter.Points;
import tconq.io.Window;
import tconq.memento.Data;
import tconq.render.Camera;
import tconq.render.Shader;
import tconq.render.TileSheet;
import tconq.server.ServerHandler;
import tconq.worldmap.Map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

import static org.lwjgl.glfw.GLFW.*;


public class Selector {

    public static final int STATE_IDLE = 0;
	public static final int STATE_SELECTED = 1;
	public static final int STATE_CLICKED = 2;
	public boolean printed = false;
	
	private int selectedState;
	public static Map world;
	private Camera camera;
	private AABB boundingBox;
	//private Texture texture; TODO: implement selector textures.
	public static AbstractEntityFactory entityFactory = EntityProducer.getFactory(true);  // changed from private to public static !!!
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	private boolean canUpgrade = true;		//if true unit or building can be upgraded if false can't resets when button is released
	private boolean canMove = true;
	
	public static long entityId = 1;

	public static IEntity selectedEntity = new Null();

	public boolean timerStarted = false;
	public Timer timer = new Timer();

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
		AABB data = world.getEntityBoundungBox((int)coords.x, (int)coords.y);
		this.boundingBox = data;
		if (data == null) {
			selectedState = STATE_SELECTED;

			//System.out.println(data.getCollision(window.getInput().getMousePosition()).toString());
				if (window.getInput().isMouseButtonDown(0)) {
					TransformTc tc = new TransformTc();
					Vector2f v = getTileCoordinates(window);
					tc.pos.x = (float)Math.floor(v.x)*2;
					tc.pos.y =  (float)Math.floor(v.y)*-2;

					//creates weak unit and adds decorators
					IEntity weak = entityFactory.getEntity("weakunit",tc);

					weak.setId(entityId++);

					world.addEntity(weak, ServerHandler.instance.playerID);
					selectedState = STATE_CLICKED;
					printed = false;
				}
		}else{
			selectedState = STATE_SELECTED;
			Vector2f v = getTileCoordinates(window);
			SelectUnit(v);
		}

		//The movement of the unit
		entityMovement(window, world);
		if (window.getInput().isMouseButtonDown(0))
		{
			printed = false;
		}


		if (window.getInput().isMouseButtonDown(1))
		{
			Vector2f v = getTileCoordinates(window);
			UpgradeUnitOrBuilding(v);		//upgrades units or buildings
		}

		//resets can upgrade when button is released
		if (window.getInput().isMouseButtonReleased(1) && !canUpgrade){
			canUpgrade = true;
		}

//		new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent mouseEvent) {
//				final String uri = "http://" + ServerHandler.instance.serverip + "/SEntities";
//				HttpHeaders headers = new HttpHeaders();
//				headers.setContentType(MediaType.APPLICATION_JSON);
//
//				ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
//			};




		//}
		//else selectedState = STATE_IDLE;

		if (!timerStarted){
			timerStarted = true;
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					TaxAndIncome(world);
				}
			},  10000, 10000 );

		}

		try {
			if (reader.ready()){
				String input = reader.readLine();

				Context context = input.equals("") ? null : new Context(input);
				if (context != null && context.getWhatToAdd() != null && !context.getWrongInput()) {
					switch (context.getWhatToAdd()) {
						case "gold":
							Gold gold = new Gold();
							switch (context.getCommand()) {
								case "add":
									gold.add(context.getAmount());
									break;
								case "remove":
									gold.remove(context.getAmount());
									break;
							}
							break;
						case "points":
							Points points = new Points();
							switch (context.getCommand()) {
								case "add":
									points.add(context.getAmount());
									break;
								case "remove":
									points.remove(context.getAmount());
									break;
							}
							break;
						case "move":
							switch(context.getCommand()) {
								case "left":
									moveAndAttack(world, "Left", context.getAmount());
									break;
								case "right":
									moveAndAttack(world, "Right", context.getAmount());
									break;
								case "up":
									moveAndAttack(world, "Up", context.getAmount());
									break;
								case "down":
									moveAndAttack(world, "Down", context.getAmount());
									break;									
							}
					}
				}
			}

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void TaxAndIncome(Map world){
		HouseIncome houseIncome = new HouseIncome();
		WarriorTax warriorTax = new WarriorTax();
    	for (IEntity ent : world.getEntities()){
			if (ent.getEntityClass(ent).getSimpleName().toLowerCase().contains("unit")){
				App.player.addGold(ent.accept(warriorTax));
			}
			else{
				App.player.addGold(ent.accept(houseIncome));
			}

		}

    	System.out.println("Current gold: " + App.player.getGold());
	}

	public void entityMovement(Window window, Map world)
	{
		if(selectedState == STATE_SELECTED  && canMove && selectedEntity != null && !selectedEntity.getClass().equals(Null.class) )
		{
			if(window.getInput().isKeyPressed(GLFW_KEY_UP)) {
				moveAndAttack(world, "Up");
			}
			if(window.getInput().isKeyPressed(GLFW_KEY_DOWN)) {
				moveAndAttack(world, "Down");
			}
			if(window.getInput().isKeyPressed(GLFW_KEY_RIGHT)) {
				moveAndAttack(world, "Right");
			}
			if(window.getInput().isKeyPressed(GLFW_KEY_LEFT)) {
				moveAndAttack(world, "Left");
			}
		}
		if(window.getInput().isKeyReleased(GLFW_KEY_UP) || window.getInput().isKeyReleased(GLFW_KEY_RIGHT) || window.getInput().isKeyReleased(GLFW_KEY_LEFT) || window.getInput().isKeyReleased(GLFW_KEY_DOWN)) {
			canMove = true;
		}
	}

	public void moveAndAttack(Map world, String direction){
		TransformTc tc = new TransformTc();

		switch (direction){
			case "Up":
				tc.pos.x = selectedEntity.getPos().pos.x;
				tc.pos.y = selectedEntity.getPos().pos.y + 2;
				break;
			case "Down":
				tc.pos.x = selectedEntity.getPos().pos.x;
				tc.pos.y = selectedEntity.getPos().pos.y - 2;
				break;
			case "Left":
				tc.pos.x = selectedEntity.getPos().pos.x - 2;
				tc.pos.y = selectedEntity.getPos().pos.y;
				break;
			case "Right":
				tc.pos.x = selectedEntity.getPos().pos.x + 2;
				tc.pos.y = selectedEntity.getPos().pos.y;
				break;
			default:
				tc.pos.x = selectedEntity.getPos().pos.x;
				tc.pos.y = selectedEntity.getPos().pos.y;
				break;
		}

		IEntity opponent = world.getEntity(tc.pos);

		boolean canAttack = selectedEntity.move(direction);

		if (opponent != null && canAttack){
			DeathTax deathTax = new DeathTax();
			if (selectedEntity.attackChain(opponent)){
				undoMove();
				App.player.addGold(opponent.accept(deathTax));
				world.removeEntity(opponent.getPos().pos);
				selectedEntity.move(direction);
			}
			else {
				undoMove();
				App.player.addGold(selectedEntity.accept(deathTax));
				world.removeEntity(selectedEntity.getPos().pos);
				selectedEntity = new Null();
			}
			System.out.println("Current gold: " + App.player.getGold());
		}
		canMove = false;
	}

	public void moveAndAttack(Map world, String direction, int entityId){
		TransformTc tc = new TransformTc();
		IEntity foundEntity = null;
		foundEntity = world.getEntity(entityId);
		if(foundEntity.equals(null))
			return;
		switch (direction){
			case "Up":
				tc.pos.x = foundEntity.getPos().pos.x;
				tc.pos.y = foundEntity.getPos().pos.y + 2;
				break;
			case "Down":
				tc.pos.x = foundEntity.getPos().pos.x;
				tc.pos.y = foundEntity.getPos().pos.y - 2;
				break;
			case "Left":
				tc.pos.x = foundEntity.getPos().pos.x - 2;
				tc.pos.y = foundEntity.getPos().pos.y;
				break;
			case "Right":
				tc.pos.x = foundEntity.getPos().pos.x + 2;
				tc.pos.y = foundEntity.getPos().pos.y;
				break;
			default:
				tc.pos.x = foundEntity.getPos().pos.x;
				tc.pos.y = foundEntity.getPos().pos.y;
				break;
		}

		IEntity opponent = world.getEntity(tc.pos);

		boolean canAttack = foundEntity.move(direction);

		if (opponent != null && canAttack){
			DeathTax deathTax = new DeathTax();
			if (foundEntity.attackChain(opponent)){
				undoMove();
				App.player.addGold(opponent.accept(deathTax));
				world.removeEntity(opponent.getPos().pos);
				foundEntity.move(direction);
			}
			else {
				undoMove();
				App.player.addGold(foundEntity.accept(deathTax));
				world.removeEntity(foundEntity.getPos().pos);
				foundEntity = new Null();
			}
			System.out.println("Current gold: " + App.player.getGold());
		}
		canMove = false;
	}


	public static void undoMove()
	{
		selectedEntity.undoMove();
	}

	public static void undo(Data data){
    	data.undo(world);
	}

	//Selects an entity in the v vector position and makes it selected entity
	public void SelectUnit(Vector2f v)
	{
		TransformTc tc = new TransformTc();
		tc.pos.x = (float)Math.floor(v.x)*2;
		tc.pos.y =  (float)Math.floor(v.y)*-2;

		//checks what type of unit is on the tile and upgrades it
		selectedEntity = world.getEntity(tc.pos);
		if(!printed){
			System.out.println("Entity id: " + selectedEntity.getId());
			printed = true;
		}
			
	}

	public void printEntityCoordinates(Vector2f v){
		TransformTc tc = new TransformTc();
			tc.pos.x = (float)Math.floor(v.x)*2;
			tc.pos.y =  (float)Math.floor(v.y)*-2;

			//checks what type of unit is on the tile and upgrades it
			IEntity entity = world.getEntity(tc.pos);
			if(!entity.equals(null)) System.out.println("Entity id: "+ entity.getId() + 
			"COORDS: x=" + entity.getPos().pos.x +" y=" + entity.getPos().pos.x);
	}

	//upgrades units and buildings
	public void UpgradeUnitOrBuilding(Vector2f v){
		if (canUpgrade) {
			TransformTc tc = new TransformTc();
			tc.pos.x = (float)Math.floor(v.x)*2;
			tc.pos.y =  (float)Math.floor(v.y)*-2;

			//checks what type of unit is on the tile and upgrades it
			IEntity entity = world.getEntity(tc.pos);
			//IEntityUpgrade entityupgrade = (IEntityUpgrade)entity;
			EntityProxy proxy = new EntityProxy((IEntityUpgrade)entity);
			if (entity != null) {
				proxy.upgrade(world, entity.getId());
				canUpgrade = false;
			}
			selectedState = STATE_CLICKED;
		}


	}

	public void render(Camera camera, TileSheet sheet, Shader shader) {
		Vector2f position = new Vector2f(), scale = new Vector2f();
		if (boundingBox != null){
			position = boundingBox.getCenter();
			scale = boundingBox.getHalfExtent();
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
		//renderSides(position, scale, camera, sheet, shader);
	}

	// private void renderSides(Vector2f position, Vector2f scale, Camera camera, TileSheet sheet, Shader shader) {
	// 	transform.identity().translate(position.x, position.y + scale.y - 16, 0).scale(scale.x, 16, 1); // Top
		
	// 	shader.setUniform("projection", camera.getProjection().mul(transform));
	// 	switch (selectedState) {
	// 		case STATE_SELECTED :
	// 			sheet.bindTile(shader, 4, 0);
	// 			break;
	// 		case STATE_CLICKED :
	// 			sheet.bindTile(shader, 7, 0);
	// 			break;
	// 		default :
	// 			sheet.bindTile(shader, 1, 0);
	// 			break;
	// 	}
	// 	Assets.getModel().render();
		
	// 	transform.identity().translate(position.x, position.y - scale.y + 16, 0).scale(scale.x, 16, 1); // Bottom
		
	// 	shader.setUniform("projection", camera.getProjection().mul(transform));
	// 	switch (selectedState) {
	// 		case STATE_SELECTED :
	// 			sheet.bindTile(shader, 4, 2);
	// 			break;
	// 		case STATE_CLICKED :
	// 			sheet.bindTile(shader, 7, 2);
	// 			break;
	// 		default :
	// 			sheet.bindTile(shader, 1, 2);
	// 			break;
	// 	}
	// 	Assets.getModel().render();
		
	// 	transform.identity().translate(position.x - scale.x + 16, position.y, 0).scale(16, scale.y, 1); // Left
		
	// 	shader.setUniform("projection", camera.getProjection().mul(transform));
	// 	switch (selectedState) {
	// 		case STATE_SELECTED :
	// 			sheet.bindTile(shader, 3, 1);
	// 			break;
	// 		case STATE_CLICKED :
	// 			sheet.bindTile(shader, 6, 1);
	// 			break;
	// 		default :
	// 			sheet.bindTile(shader, 0, 1);
	// 			break;
	// 	}
	// 	Assets.getModel().render();
		
	// 	transform.identity().translate(position.x + scale.x - 16, position.y, 0).scale(16, scale.y, 1); // Right
		
	// 	shader.setUniform("projection", camera.getProjection().mul(transform));
	// 	switch (selectedState) {
	// 		case STATE_SELECTED :
	// 			sheet.bindTile(shader, 5, 1);
	// 			break;
	// 		case STATE_CLICKED :
	// 			sheet.bindTile(shader, 8, 1);
	// 			break;
	// 		default :
	// 			sheet.bindTile(shader, 2, 1);
	// 			break;
	// 	}
	// 	Assets.getModel().render();
	// }
	
	// private void renderCorners(Vector2f position, Vector2f scale, Camera camera, TileSheet sheet, Shader shader) {
	// 	transform.identity().translate(position.x - scale.x + 16, position.y + scale.y - 16, 0).scale(16, 16, 1); // Top
	// 																																				 // Left
		
	// 	shader.setUniform("projection", camera.getProjection().mul(transform));
	// 	switch (selectedState) {
	// 		case STATE_SELECTED :
	// 			sheet.bindTile(shader, 3, 0);
	// 			break;
	// 		case STATE_CLICKED :
	// 			sheet.bindTile(shader, 6, 0);
	// 			break;
	// 		default :
	// 			sheet.bindTile(shader, 0, 0);
	// 			break;
	// 	}
	// 	Assets.getModel().render();
		
	// 	transform.identity().translate(position.x + scale.x - 16, position.y + scale.y - 16, 0).scale(16, 16, 1); // Top
	// 																																				 // Right
		
	// 	shader.setUniform("projection", camera.getProjection().mul(transform));
	// 	switch (selectedState) {
	// 		case STATE_SELECTED :
	// 			sheet.bindTile(shader, 5, 0);
	// 			break;
	// 		case STATE_CLICKED :
	// 			sheet.bindTile(shader, 8, 0);
	// 			break;
	// 		default :
	// 			sheet.bindTile(shader, 2, 0);
	// 			break;
	// 	}
	// 	Assets.getModel().render();
		
	// 	transform.identity().translate(position.x - scale.x + 16, position.y - scale.y + 16, 0).scale(16, 16, 1); // Bottom
	// 																																				 // Left
		
	// 	shader.setUniform("projection", camera.getProjection().mul(transform));
	// 	switch (selectedState) {
	// 		case STATE_SELECTED :
	// 			sheet.bindTile(shader, 3, 2);
	// 			break;
	// 		case STATE_CLICKED :
	// 			sheet.bindTile(shader, 6, 2);
	// 			break;
	// 		default :
	// 			sheet.bindTile(shader, 0, 2);
	// 			break;
	// 	}
	// 	Assets.getModel().render();
		
	// 	transform.identity().translate(position.x + scale.x - 16, position.y - scale.y + 16, 0).scale(16, 16, 1); // Bottom
	// 																																				 // Right
		
	// 	shader.setUniform("projection", camera.getProjection().mul(transform));
	// 	switch (selectedState) {
	// 		case STATE_SELECTED :
	// 			sheet.bindTile(shader, 5, 2);
	// 			break;
	// 		case STATE_CLICKED :
	// 			sheet.bindTile(shader, 8, 2);
	// 			break;
	// 		default :
	// 			sheet.bindTile(shader, 2, 2);
	// 			break;
	// 	}
	// 	Assets.getModel().render();
	// }

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