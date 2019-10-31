package tconq.entity.factory;

import tconq.entity.Entity;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.adapter.IEntityCostAdapter;
import tconq.entity.adapter.StrongUnitCostAdapter;
import tconq.io.Window;
import tconq.render.Camera;
import tconq.worldmap.Map;

public class StrongUnit extends Entity implements IEntityCostAdapter{

    public StrongUnit(TransformTc transform) {
        super("strongWarrior.png", transform);
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

    @Override
    public int getCostAdapter(){
        IEntity WeakUnit = new WeakUnit(transform);
        IEntityCostAdapter strongUnitCostAdapter = new StrongUnitCostAdapter(WeakUnit);
        return strongUnitCostAdapter.getCostAdapter();
    }

}