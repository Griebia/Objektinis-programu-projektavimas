package tconq.entity.factory;

import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.io.DebugHandler;

public class UnitFactory extends AbstractEntityFactory {

    @Override
    public IEntity getEntity(String entityType, TransformTc transform){
        switch(entityType.toLowerCase()){
            case "strongunit":
                if(DebugHandler.debugmode.factoryDebug) System.out.println("Spawning strong unit using Unit Factory");
                StrongUnit newStrongUnit = new StrongUnit(transform);
                newStrongUnit.processEntity();      // execute template method
                return newStrongUnit;
            case "mediumunit":
                if(DebugHandler.debugmode.factoryDebug) System.out.println("Spawning medium unit using Unit Factory");
                MediumUnit newMediumUnit = new MediumUnit(transform);
                newMediumUnit.processEntity();      // execute template method
                return newMediumUnit;
            case "weakunit":
                if(DebugHandler.debugmode.factoryDebug) System.out.println("Spawning weak unit using Unit Factory");
                WeakUnit newWeakUnit = new WeakUnit(transform);
                newWeakUnit.processEntity();        // execute template method
                return newWeakUnit;
            default:
                if(DebugHandler.debugmode.factoryDebug) System.out.println("No such unit type");
                return null;
        }
    }

    @Override
    public IEntity getEntityFromDb(String entityType, TransformTc transform){   // sita padariau del template metodo, nes kai is duomazes pasiima enticius juos sukuria is naujo, todel prideda tasku zaidejui nereikalingai
        switch(entityType.toLowerCase()){
            case "strongunit":
                if(DebugHandler.debugmode.factoryDebug) System.out.println("Spawning strong unit using Unit Factory");
                StrongUnit newStrongUnit = new StrongUnit(transform);
                return newStrongUnit;
            case "mediumunit":
                if(DebugHandler.debugmode.factoryDebug) System.out.println("Spawning medium unit using Unit Factory");
                MediumUnit newMediumUnit = new MediumUnit(transform);
                return newMediumUnit;
            case "weakunit":
                if(DebugHandler.debugmode.factoryDebug) System.out.println("Spawning weak unit using Unit Factory");
                WeakUnit newWeakUnit = new WeakUnit(transform);
                return newWeakUnit;
            default:
                if(DebugHandler.debugmode.factoryDebug) System.out.println("No such unit type");
                return null;
        }
    }
}