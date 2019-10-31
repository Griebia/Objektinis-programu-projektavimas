package tconq.entity.adapter;

import tconq.entity.IEntity;

public class CastleCostAdapter implements IEntityCostAdapter {

    private IEntity entity;

    public CastleCostAdapter(IEntity entity){
        this.entity = entity;
    }

    @Override
    public int getCostAdapter(){
        return getCastleCost(entity.getCost());
    }

    private int getCastleCost(int cost){
        return (cost + 9);
    }
}