package tconq.entity.factory;

import tconq.entity.Entity;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.adapter.IEntityCostAdapter;
import tconq.entity.adapter.MediumUnitCostAdapter;
import tconq.entity.strategy.MediumToStrong;
import tconq.io.Window;
import tconq.render.Camera;
import tconq.worldmap.Map;

public class MediumUnit extends Entity {

    public MediumUnit(TransformTc transform) {
        super("mediumWarrior.png", transform);
        upgradeStrategy = new MediumToStrong();
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
        IEntityCostAdapter mediumCostAdapter = new MediumUnitCostAdapter(WeakUnit);
        return mediumCostAdapter.getCostAdapter();
    }

}