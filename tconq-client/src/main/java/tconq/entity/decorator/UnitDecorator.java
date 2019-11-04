package tconq.entity.decorator;

import org.joml.Vector2f;
import tconq.collision.AABB;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.strategy.Upgrade;
import tconq.io.Window;
import tconq.render.Camera;
import tconq.render.Shader;
import tconq.worldmap.Map;

public abstract class UnitDecorator implements IEntity{

    protected IEntity tempUnit;

    public UnitDecorator(IEntity newUnit){
        tempUnit = newUnit;
    }

    //checks if current unit can beat passed unit
    public boolean getAttack(IEntity enemyUnit){
        return tempUnit.getAttack(enemyUnit);
    }

    //checks if current unit can destroy passed building
    public boolean getDestroyBuilding(IEntity enemyBuilding){
        return tempUnit.getDestroyBuilding(enemyBuilding);
    }

    //gets amount of spaces unit can move
    public int getMovement() {
        return tempUnit.getMovement();
    }




    @Override
    public void render(Shader shader, Camera camera, Map world) {
        tempUnit.render(shader, camera, world);
    }

    @Override
    public void update(float delta, Window window, Camera camera, Map world) {
        tempUnit.update(delta, window, camera, world);
    }

    @Override
    public void setPlayerId(Long id) {
        tempUnit.setPlayerId(id);
    }

    @Override
    public Long getPlayerId() {
        return tempUnit.getPlayerId();
    }

    @Override
    public void setId(Long id) {
        tempUnit.setId(id);
    }

    @Override
    public Long getId() {
        return tempUnit.getId();
    }

    @Override
    public TransformTc getPos() {
        return tempUnit.getPos();
    }

    @Override
    public void move(String direction) {
        tempUnit.move(direction);
    }
    
    @Override
    public void move(Vector2f direction) {
        tempUnit.move(direction);
    }

    @Override
    public void undo() {
        tempUnit.undo();
    }

    @Override
    public AABB getEntityBoundingBox() {
        return tempUnit.getEntityBoundingBox();
    }

    @Override
    public void collideWithTiles(Map world) {
        tempUnit.collideWithTiles(world);
    }


    @Override
    public void upgrade(Map world, Long entityId) {
        tempUnit.upgrade(world, entityId);
    }


    @Override
    public void collideWithEntity(IEntity entity) {
        tempUnit.collideWithEntity(entity);
    }

    @Override
    public Class getEntityClass(IEntity entity) {
        return tempUnit.getEntityClass(entity);
    }

    @Override
    public int getCost(){
        return tempUnit.getCost();
    }



}
