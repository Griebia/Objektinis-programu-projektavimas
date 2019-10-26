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
    public void upgrade(Map world, Upgrade newUpgradeStrategy) {
        tempUnit.upgrade(world, newUpgradeStrategy);
    }

    @Override
    public void collideWithEntity(IEntity entity) {
        tempUnit.collideWithEntity(entity);
    }

    public String getAttack() {
        return tempUnit.getAttack() + "more dmg";
    }
}
