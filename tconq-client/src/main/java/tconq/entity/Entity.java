package tconq.entity;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import tconq.assets.Assets;
import tconq.collision.AABB;
import tconq.collision.Collision;
import tconq.entity.chain_of_responsibility.*;
import tconq.entity.command.MovementControl;
import tconq.entity.state.State;
import tconq.entity.state.StateContext;
import tconq.entity.strategy.Upgrade;
import tconq.io.Window;
import tconq.render.Animation;
import tconq.render.Camera;
import tconq.render.Shader;
import tconq.render.Texture;
import tconq.server.ServerHandler;
import tconq.worldmap.Map;


public abstract class Entity implements IEntity, IEntityUpgrade{
	protected AABB bounding_box;
	
	protected Animation[] animations;
	private int use_animation;
	protected Texture texture;
	protected Upgrade upgradeStrategy;
	protected StateContext stateContext;

	
	protected TransformTc transform;
	protected Long playerId;
	protected Long id;

	protected MovementControl movementControl;
	
	public void setPlayerId(Long id){
		this.playerId = id;
	}
	public Long getPlayerId(){
		return playerId;
	}

	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return this.id;
	}

	public Entity(int max_animations, TransformTc transform) {
		this.animations = new Animation[max_animations];
		
		this.transform = transform;
		this.use_animation = 0;
		
		bounding_box = new AABB(new Vector2f(transform.pos.x, transform.pos.y), new Vector2f(transform.scale.x, transform.scale.y));

	}

	public Entity(String texturepath, TransformTc transform){
		this.texture = new Texture(texturepath);
		this.transform = transform;
		bounding_box = new AABB(new Vector2f(transform.pos.x, transform.pos.y), new Vector2f(transform.scale.x, transform.scale.y));
		movementControl = new MovementControl(this);
	}


	protected void setTexture(String texturepath){
		this.texture = new Texture(texturepath);
	}
	
	protected void setAnimation(int index, Animation animation) {
		animations[index] = animation;
	}

	public TransformTc getPos(){
		return this.transform;
	}

	public AABB getEntityBoundingBox(){
		return this.bounding_box;
	}
	
	public void useAnimation(int index) {
		this.use_animation = index;
	}
	
	public void move(Vector2f direction) {
		transform.pos.add(new Vector3f(direction, 0));
		
		bounding_box.getCenter().set(transform.pos.x, transform.pos.y);
	}
	public boolean move(String direction)
	{
		return movementControl.move(direction);
	}
	public void undo(){movementControl.undo();};
	
	public void collideWithTiles(Map world) {
		AABB[] boxes = new AABB[25];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				boxes[i + j * 5] = world.getTileBoundingBox((int) (((transform.pos.x / 2) + 0.5f) - (5 / 2)) + i, (int) (((-transform.pos.y / 2) + 0.5f) - (5 / 2)) + j);
			}
		}
		
		AABB box = null;
		for (int i = 0; i < boxes.length; i++) {
			if (boxes[i] != null) {
				if (box == null) box = boxes[i];
				
				Vector2f length1 = box.getCenter().sub(transform.pos.x, transform.pos.y, new Vector2f());
				Vector2f length2 = boxes[i].getCenter().sub(transform.pos.x, transform.pos.y, new Vector2f());
				
				if (length1.lengthSquared() > length2.lengthSquared()) {
					box = boxes[i];
				}
			}
		}
		if (box != null) {
			Collision data = bounding_box.getCollision(box);
			if (data.isIntersecting) {
				bounding_box.correctPosition(box, data);
				transform.pos.set(bounding_box.getCenter(), 0);
			}
			
			for (int i = 0; i < boxes.length; i++) {
				if (boxes[i] != null) {
					if (box == null) box = boxes[i];
					
					Vector2f length1 = box.getCenter().sub(transform.pos.x, transform.pos.y, new Vector2f());
					Vector2f length2 = boxes[i].getCenter().sub(transform.pos.x, transform.pos.y, new Vector2f());
					
					if (length1.lengthSquared() > length2.lengthSquared()) {
						box = boxes[i];
					}
				}
			}
			
			data = bounding_box.getCollision(box);
			if (data.isIntersecting) {
				bounding_box.correctPosition(box, data);
				transform.pos.set(bounding_box.getCenter(), 0);
			}
		}
	}


	public void upgrade(Map world, Long entityId) {
// Strategy Upgrade
//		if(upgradeStrategy != null)
//			upgradeStrategy.upgrade(this.transform, world, ServerHandler.instance.playerID, entityId);
		//TODO: implement
		this.stateContext.upgrade(this.transform, world, ServerHandler.instance.playerID, entityId);
	}

	public abstract void update(float delta, Window window, Camera camera, Map world);
	
	public void render(Shader shader, Camera camera, Map world) {
		Matrix4f target = camera.getProjection();
		target.mul(world.getWorldMatrix());
		
		shader.bind();
		shader.setUniform("sampler", 0);
		shader.setUniform("projection", transform.getProjection(target));
		texture.bind(0);
		Assets.getModel().render();
	}
	
	public void collideWithEntity(Entity entity) {
		Collision collision = bounding_box.getCollision(entity.bounding_box);
		
		if (collision.isIntersecting) {
			collision.distance.x /= 2;
			collision.distance.y /= 2;
			
			bounding_box.correctPosition(entity.bounding_box, collision);
			transform.pos.set(bounding_box.getCenter().x, bounding_box.getCenter().y, 0);
			
			entity.bounding_box.correctPosition(bounding_box, collision);
			entity.transform.pos.set(entity.bounding_box.getCenter().x, entity.bounding_box.getCenter().y, 0);
		}
	}

	public Class getEntityClass(IEntity entity){
		return entity.getClass();
	}

	public boolean getAttack(IEntity enemyUnit){
		return false;
	}

	public boolean getDestroyBuilding(IEntity enemyBuilding){
		return false;
	}

	public int getMovement() {
		return 5;
	}

	public abstract void addPoints();

	public final void processEntity(){
		addPoints();
	}



	public boolean attackChain(IEntity opponent){
		Chain weakUnit = new AttackWeak();
		Chain mediumUnit = new AttackMedium();
		Chain stringUnit = new AttackStrong();
		Chain house = new AttackHouse();
		Chain tower = new AttackTower();
		Chain castle = new AttackCastle();


		weakUnit.setNextChain(mediumUnit);
		mediumUnit.setNextChain(stringUnit);
		stringUnit.setNextChain(house);
		house.setNextChain(tower);
		tower.setNextChain(castle);

		return weakUnit.attack(this, opponent);
	}
	
}