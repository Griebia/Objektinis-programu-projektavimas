package tconq.entity.factory;

import tconq.entity.Entity;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.io.Window;
import tconq.render.Camera;
import tconq.render.Shader;
import tconq.worldmap.Map;

public class Tower extends Entity {

    public Tower( TransformTc transform) {
        super("tower.png", transform);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void update(float delta, Window window, Camera camera, Map world) {
        // TODO Auto-generated method stub

    }

    @Override
    public void collideWithEntity(IEntity entity) {

    }

    @Override
    public Class getEntityClass(IEntity entity) {
        return this.getClass();
    }
}