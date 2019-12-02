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
                newStrongUnit.processEntity();
                return newStrongUnit;
            case "mediumunit":
                if(DebugHandler.debugmode.factoryDebug) System.out.println("Spawning medium unit using Unit Factory");
                MediumUnit newMediumUnit = new MediumUnit(transform);
                newMediumUnit.processEntity();
                return newMediumUnit;
            case "weakunit":
                if(DebugHandler.debugmode.factoryDebug) System.out.println("Spawning weak unit using Unit Factory");
                WeakUnit newWeakUnit = new WeakUnit(transform);
                newWeakUnit.processEntity();
                return newWeakUnit;
            default:
                if(DebugHandler.debugmode.factoryDebug) System.out.println("No such unit type");
                return null;
        }
    }
}