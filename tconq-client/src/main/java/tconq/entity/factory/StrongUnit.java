package tconq.entity.factory;

import tconq.App;
import tconq.entity.Entity;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.adapter.IEntityCostAdapter;
import tconq.entity.adapter.StrongUnitCostAdapter;
import tconq.entity.state.MediumUnitState;
import tconq.entity.state.StateContext;
import tconq.io.Window;
import tconq.render.Camera;
import tconq.worldmap.Map;

public class StrongUnit extends Entity {

    public StrongUnit(TransformTc transform) {
        super("strongWarrior.png", transform);
        stateContext = new StateContext(new MediumUnitState());
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

    @Override
    public int getCost(){
        return getCostAdapter();
    }

    
    private int getCostAdapter(){
        IEntity WeakUnit = new WeakUnit(transform);
        IEntityCostAdapter strongUnitCostAdapter = new StrongUnitCostAdapter(WeakUnit);
        return strongUnitCostAdapter.getCostAdapter();
    }

    @Override
    public void addPoints() {
        App.player.addPoints(5);
        
    }



}