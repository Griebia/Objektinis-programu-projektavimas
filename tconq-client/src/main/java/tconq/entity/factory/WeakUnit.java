package tconq.entity.factory;

import tconq.entity.Entity;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.io.Window;
import tconq.render.Camera;
import tconq.worldmap.Map;

public class WeakUnit extends Entity {

    public WeakUnit(TransformTc transform) {
		super("weakWarrior.png", transform);
	}
	
	@Override
	public void update(float delta, Window window, Camera camera, Map world) {
	}

	@Override
	public void collideWithEntity(IEntity entity) {

	}

	@Override
	public Class getEntityClass(IEntity entity) {
		return this.getClass();
	}
}