package tconq.entity.decorator;

import tconq.collision.AABB;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.strategy.Upgrade;
import tconq.io.Window;
import tconq.render.Camera;
import tconq.render.Shader;
import tconq.worldmap.Map;

public class AttackBuilding extends UnitDecorator {
    public AttackBuilding(IEntity newUnit) {
        super(newUnit);
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
    public AABB getEntityBoundingBox() {
        return tempUnit.getEntityBoundingBox();
    }

    @Override
    public void collideWithTiles(Map world) {
        tempUnit.collideWithTiles(world);
    }

    @Override
    public void upgrade(Map world, Upgrade newUpgradeStrategy) {
        tempUnit.upgrade(world, newUpgradeStrategy);
    }

    @Override
    public void collideWithEntity(IEntity entity) {
        tempUnit.collideWithEntity(entity);
    }

    @Override
    public Class getEntityClass(IEntity entity) {
        return tempUnit.getEntityClass(entity);
    }

    public boolean getDestroyBuilding(IEntity enemyBuilding) {
        switch (tempUnit.getEntityClass(tempUnit).getSimpleName().toLowerCase()){
            case "weakunit":
                if (enemyBuilding.getEntityClass(enemyBuilding).getSimpleName().toLowerCase().equals("house"))
                    return true;
                else
                    return false;
            case "mediumunit":
                if (enemyBuilding.getEntityClass(enemyBuilding).getSimpleName().toLowerCase().equals("house") ||
                    enemyBuilding.getEntityClass(enemyBuilding).getSimpleName().toLowerCase().equals("tower") ||
                    enemyBuilding.getEntityClass(enemyBuilding).getSimpleName().toLowerCase().equals("castle") )
                    return true;
                else
                    return false;
            case "strongunit":
                if (enemyBuilding.getEntityClass(enemyBuilding).getSimpleName().toLowerCase().equals("house") ||
                    enemyBuilding.getEntityClass(enemyBuilding).getSimpleName().toLowerCase().equals("tower"))
                    return true;
                else
                    return false;
        }

        return false;
    }
}
