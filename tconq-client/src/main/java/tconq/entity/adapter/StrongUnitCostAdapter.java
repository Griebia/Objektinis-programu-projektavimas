package tconq.entity.adapter;

import tconq.entity.IEntity;

public class StrongUnitCostAdapter implements IEntityCostAdapter {

    private IEntity entity;

    public StrongUnitCostAdapter(IEntity entity){
        this.entity = entity;
    }

    @Override
    public int getCostAdapter(){
        return getStrongUnitCost(entity.getCost());
    }

    private int getStrongUnitCost(int cost){
        return (cost + 7);
    }
}