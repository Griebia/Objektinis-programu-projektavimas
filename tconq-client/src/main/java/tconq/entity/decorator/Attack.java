package tconq.entity.decorator;

import tconq.collision.AABB;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.decorator.UnitDecorator;
import tconq.entity.strategy.Upgrade;
import tconq.io.Window;
import tconq.render.Camera;
import tconq.render.Shader;
import tconq.worldmap.Map;

public class Attack extends UnitDecorator {
    public Attack(IEntity newUnit) {
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
    public void upgrade(Map world, Upgrade newUpgradeStrategy, Long entityId) {
        tempUnit.upgrade(world, newUpgradeStrategy, entityId);
    }


    @Override
    public void collideWithEntity(IEntity entity) {
        tempUnit.collideWithEntity(entity);
    }

    @Override
    public Class getEntityClass(IEntity entity) {
        return tempUnit.getEntityClass(entity);
    }

    public boolean getAttack(IEntity enemyUnit) {
        switch (tempUnit.getEntityClass(tempUnit).getSimpleName().toLowerCase()){
            case "weakunit":
                if (enemyUnit.getEntityClass(enemyUnit).getSimpleName().toLowerCase().equals("weakunit"))
                    return true;
                else
                    return false;
            case "mediumunit":
                if (enemyUnit.getEntityClass(enemyUnit).getSimpleName().toLowerCase().equals("weakunit") ||
                    enemyUnit.getEntityClass(enemyUnit).getSimpleName().toLowerCase().equals("mediumunit"))
                    return true;
                else
                    return false;
            case "strongunit":
                if (enemyUnit.getEntityClass(enemyUnit).getSimpleName().toLowerCase().equals("weakunit") ||
                    enemyUnit.getEntityClass(enemyUnit).getSimpleName().toLowerCase().equals("mediumunit") ||
                    enemyUnit.getEntityClass(enemyUnit).getSimpleName().toLowerCase().equals("strongunit"))
                    return true;
                else
                    return false;
        }

        return false;
    }
}
