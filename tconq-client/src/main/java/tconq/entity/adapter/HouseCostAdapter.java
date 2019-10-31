package tconq.entity.adapter;

import tconq.entity.IEntity;

public class HouseCostAdapter implements IEntityCostAdapter {

    private IEntity entity;

    public HouseCostAdapter(IEntity entity){
        this.entity = entity;
    }

    @Override
    public int getCostAdapter(){
        return getTowerCost(entity.getCost());
    }

    private int getTowerCost(int cost){
        return (cost - 1); // gal det minusine reiksme, tipo nekainuoja o prideda aukso, nereiktu atskiro metodo aukso generavimui?
    }
}