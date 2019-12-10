package tconq.entity.factory;

import tconq.App;
import tconq.entity.Entity;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.state.MediumUnitState;
import tconq.entity.state.StateContext;
import tconq.entity.state.WeakUnitState;
import tconq.entity.strategy.WeakToMedium;
import tconq.io.Window;
import tconq.render.Camera;
import tconq.worldmap.Map;

public class WeakUnit extends Entity {

	

    public WeakUnit(TransformTc transform) {
		super("weakWarrior.png", transform);
		upgradeStrategy = new WeakToMedium();
		stateContext = new StateContext(new WeakUnitState());
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

	@Override
	public int getCost(){
		return 1;
	}

	@Override
    public void addPoints() {
        App.player.addPoints(1);
        System.out.println(App.player.getId()+"=================================================");
	}

}