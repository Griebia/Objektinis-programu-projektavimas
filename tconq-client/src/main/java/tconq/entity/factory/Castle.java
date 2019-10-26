package tconq.entity.factory;

import tconq.entity.Entity;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.io.Window;
import tconq.render.Camera;
import tconq.worldmap.Map;

public class Castle extends Entity {

    public Castle(TransformTc transform) {
        super("castle.png", transform);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void update(float delta, Window window, Camera camera, Map world) {
        // TODO Auto-generated method stub

    }

    @Override
    public void collideWithEntity(IEntity entity) {

    }
}