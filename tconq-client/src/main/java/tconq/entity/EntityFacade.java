package tconq.entity;

import org.springframework.boot.autoconfigure.web.ResourceProperties.Strategy;

import tconq.entity.factory.AbstractEntityFactory;
import tconq.entity.factory.EntityProducer;
import tconq.entity.strategy.HouseToTower;
import tconq.entity.strategy.MediumToStrong;
import tconq.entity.strategy.TowerToCastle;
import tconq.entity.strategy.Upgrade;
import tconq.entity.strategy.WeakToMedium;

public class EntityFacade {

    private AbstractEntityFactory unitfactory = EntityProducer.getFactory(true);
    private AbstractEntityFactory buildingfactoryl = EntityProducer.getFactory(false);
    
    private Upgrade upgrade;

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

    public Entity createEntity(EntityType type, TransformTc tc){
        switch(type){
            case HOUSE :
                return (Entity) buildingfactoryl.getEntity("house", tc);
            case TOWER :
                return (Entity) buildingfactoryl.getEntity("tower", tc);
            case CASTLE :
                return (Entity) buildingfactoryl.getEntity("castle", tc);
            case WEAKUNIT :
                return (Entity) unitfactory.getEntity("weakunit", tc);
            case MEDIUMUNIT :
                return (Entity) unitfactory.getEntity("mediumunit", tc);
            case STRONGUNIT :
                return (Entity) unitfactory.getEntity("strongunit", tc);  
            default :
                return null; //will not execute
        }
    }

    public Upgrade changeUpdateBehavior(UpdateBehavior behavior){
        switch(behavior){
            case HOUSETOTOWER :
                return new HouseToTower();
            case TOWERTOCASTLE :
                return new TowerToCastle();
            case WEAKTOMEDIUM :
                return new WeakToMedium();
            case MEDIUMTOSTRONG :
                return new MediumToStrong();
            default :
                return null; //will not execute.
        }
    }

    public Upgrade showUpgrade(){
        return this.upgrade;
    }
}