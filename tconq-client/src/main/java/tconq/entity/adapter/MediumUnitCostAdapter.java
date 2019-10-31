package tconq.entity.adapter;

import tconq.entity.IEntity;

public class MediumUnitCostAdapter implements IEntityCostAdapter {

    private IEntity entity;

    public MediumUnitCostAdapter(IEntity entity){
        this.entity = entity;
    }

    @Override
    public int getCostAdapter(){
        return getMediumUnitCost(entity.getCost());
    }

    private int getMediumUnitCost(int cost){
        return (cost + 2);
    }
}