package tconq.entity.factory;

import tconq.entity.Entity;
import tconq.entity.TransformTc;
import tconq.io.Window;
import tconq.render.Camera;
import tconq.worldmap.Map;

public class MediumUnit extends Entity {

    public MediumUnit(TransformTc transform) {
        super("mediumWarrior.png", transform);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void update(float delta, Window window, Camera camera, Map world) {
        // TODO Auto-generated method stub

    }

}