package tconq.factories;

import tconq.entity.Building;
import tconq.entity.Castle;
import tconq.entity.House;
import tconq.entity.Tower;

public class BuildingFactory implements AbstractFactory<Building>{
    
    @Override
    public Building create(String buildingType){
        if("Tower".equalsIgnoreCase(buildingType))
            return new Tower();
        else if("Castle".equalsIgnoreCase(buildingType))
            return new Castle();
        else if("House".equalsIgnoreCase(buildingType))
            return new House();
        return null;
    }
}