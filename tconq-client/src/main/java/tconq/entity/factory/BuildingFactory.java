package tconq.entity.factory;

import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.io.DebugHandler;
import tconq.mediator.Mediator;

public class BuildingFactory extends AbstractEntityFactory {
    @Override
    public IEntity getEntity(String entityType, TransformTc transform){
        Mediator mediator = new Mediator();
        switch(entityType.toLowerCase()){
            case "castle":
                if(DebugHandler.debugmode.factoryDebug) System.out.println("Spawning castle using Building Factory");
                Castle newCastle = new Castle(transform);
                mediator.executeCastleTemplate(newCastle);
                //newCastle.processEntity();      // execute template method
                return newCastle;
            case "tower":
                if(DebugHandler.debugmode.factoryDebug) System.out.println("Spawning tower using Building Factory");
                Tower newTower = new Tower(transform);
                mediator.executeTowerTemplate(newTower);
                //newTower.processEntity();       // execute template method
                return newTower;
            case "house":
                if(DebugHandler.debugmode.factoryDebug) System.out.println("Spawning house using Building Factory");
                House newHouse = new House(transform);
                mediator.executeHouseTemplate(newHouse);
                //newHouse.processEntity();       // execute template method   
                return newHouse;
            default:
                System.out.println("no such building type");
                return null;
        }
    }

    @Override
    public IEntity getEntityFromDb(String entityType, TransformTc transform){       // sita padariau del template metodo, nes kai is duomazes pasiima enticius juos sukuria is naujo, todel prideda tasku zaidejui nereikalingai
        switch(entityType.toLowerCase()){
            case "castle":
                if(DebugHandler.debugmode.factoryDebug) System.out.println("Spawning castle using Building Factory");
                Castle newCastle = new Castle(transform);
                return newCastle;
            case "tower":
                if(DebugHandler.debugmode.factoryDebug) System.out.println("Spawning tower using Building Factory");
                Tower newTower = new Tower(transform);
                return newTower;
            case "house":
                if(DebugHandler.debugmode.factoryDebug) System.out.println("Spawning house using Building Factory");
                House newHouse = new House(transform);
                return newHouse;
            default:
                System.out.println("no such building type");
                return null;
        }
    }
}