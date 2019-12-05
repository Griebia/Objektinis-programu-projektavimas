package tconq.entity.chain_of_responsibility;

import org.joml.Vector2f;
import tconq.collision.AABB;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.io.Window;
import tconq.render.Camera;
import tconq.render.Shader;
import tconq.worldmap.Map;

public class Null implements IEntity {
    @Override
    public void render(Shader shader, Camera camera, Map world) {

    }

    @Override
    public void update(float delta, Window window, Camera camera, Map world) {

    }

    @Override
    public void setPlayerId(Long id) {

    }

    @Override
    public Long getPlayerId() {
        return null;
    }

    @Override
    public void setId(Long id) {

    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public TransformTc getPos() {
        return null;
    }

    @Override
    public boolean move(String direction) {
        return false;
    }

    @Override
    public void move(Vector2f direction) {

    }

    @Override
    public void undo() {

    }

    @Override
    public AABB getEntityBoundingBox() {
        return null;
    }

    @Override
    public void collideWithTiles(Map world) {

    }

    @Override
    public void collideWithEntity(IEntity entity) {

    }

    @Override
    public Class getEntityClass(IEntity entity) {
        return null;
    }

    @Override
    public boolean getAttack(IEntity enemyUnit) {
        return false;
    }

    @Override
    public boolean getDestroyBuilding(IEntity enemyBuilding) {
        return false;
    }

    @Override
    public int getMovement() {
        return 0;
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public boolean attackChain(IEntity opponent) {
        return false;
    }
}
