package tconq.entity.adapter;

import tconq.entity.IEntity;

public class TowerCostAdapter implements IEntityCostAdapter {

    private IEntity entity;

    public TowerCostAdapter(IEntity entity){
        this.entity = entity;
    }

    @Override
    public int getCostAdapter(){
        return getTowerCost(entity.getCost());
    }

    private int getTowerCost(int cost){
        return (cost + 4);
    }
}