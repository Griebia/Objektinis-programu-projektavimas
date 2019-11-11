package tconq.entity;

import org.springframework.boot.autoconfigure.web.ResourceProperties.Strategy;

import tconq.entity.factory.AbstractEntityFactory;
import tconq.entity.factory.EntityProducer;
import tconq.entity.strategy.HouseToTower;
import tconq.entity.strategy.MediumToStrong;
import tconq.entity.strategy.TowerToCastle;
import tconq.entity.strategy.Upgrade;
import tconq.entity.strategy.WeakToMedium;
import tconq.io.DebugHandler;

public class EntityFacade {

    private static AbstractEntityFactory unitfactory = EntityProducer.getFactory(true);
    private static AbstractEntityFactory buildingfactoryl = EntityProducer.getFactory(false);
    
    private static Upgrade upgrade;

    public EntityFacade(){
    }

    public static enum EntityType {
        HOUSE,
        TOWER,
        CASTLE,
        WEAKUNIT,
        MEDIUMUNIT,
        STRONGUNIT
    }

    public static enum UpdateBehavior {
        HOUSETOTOWER,
        TOWERTOCASTLE,
        WEAKTOMEDIUM,
        MEDIUMTOSTRONG
    }

    public static Entity createEntity(EntityType type, TransformTc tc){
        switch(type){
            case HOUSE :
                if(DebugHandler.debugmode.facadeDebug) System.out.println("Spawning house using Facade");
                return (Entity) buildingfactoryl.getEntity("house", tc);
            case TOWER :
                if(DebugHandler.debugmode.facadeDebug) System.out.println("Spawning tower using Facade");
                return (Entity) buildingfactoryl.getEntity("tower", tc);
            case CASTLE :
                if(DebugHandler.debugmode.facadeDebug) System.out.println("Spawning castle using Facade");
                return (Entity) buildingfactoryl.getEntity("castle", tc);
            case WEAKUNIT :
                if(DebugHandler.debugmode.facadeDebug) System.out.println("Spawning weak unit using Facade");
                return (Entity) unitfactory.getEntity("weakunit", tc);
            case MEDIUMUNIT :
                if(DebugHandler.debugmode.facadeDebug) System.out.println("Spawning medium unit using Facade");
                return (Entity) unitfactory.getEntity("mediumunit", tc);
            case STRONGUNIT :
                if(DebugHandler.debugmode.facadeDebug) System.out.println("Spawning strong unit using Facade");
                return (Entity) unitfactory.getEntity("strongunit", tc);  
            default :
                return null; //will not execute
        }
    }

    public static Upgrade changeUpdateBehavior(UpdateBehavior behavior){
        switch(behavior){
            case HOUSETOTOWER :
            if(DebugHandler.debugmode.facadeDebug) System.out.println("Upgrading house to tower using Facade");
                return new HouseToTower();
            case TOWERTOCASTLE :
                if(DebugHandler.debugmode.facadeDebug) System.out.println("Upgrading tower to castle using Facade");
                return new TowerToCastle();
            case WEAKTOMEDIUM :
                if(DebugHandler.debugmode.facadeDebug) System.out.println("Upgrading weak to medium using Facade");
                return new WeakToMedium();
            case MEDIUMTOSTRONG :
                if(DebugHandler.debugmode.facadeDebug) System.out.println("Upgrading medium to strong using Facade");
                return new MediumToStrong();
            default :
                return null; //will not execute.
        }
    }

    public static Upgrade showUpgrade(){
        return upgrade;
    }
}