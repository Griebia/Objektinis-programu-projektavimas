package tconq.entity.factory;

import tconq.entity.Entity;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.adapter.HouseCostAdapter;
import tconq.entity.adapter.IEntityCostAdapter;
import tconq.entity.strategy.HouseToTower;
import tconq.io.Window;
import tconq.render.Camera;
import tconq.worldmap.Map;

public class House extends Entity implements IEntityCostAdapter{

    public House(TransformTc transform) {
        super("house.png", transform);
        upgradeStrategy = new HouseToTower();
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
        IEntityCostAdapter houseCostAdapter = new HouseCostAdapter(WeakUnit);
        return houseCostAdapter.getCostAdapter();
    }

}