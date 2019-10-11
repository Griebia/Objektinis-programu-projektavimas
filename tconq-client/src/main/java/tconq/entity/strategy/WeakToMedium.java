package tconq.entity.strategy;

import tconq.entity.Entity;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.factory.AbstractEntityFactory;
import tconq.entity.factory.EntityProducer;
import tconq.worldmap.Map;

public class WeakToMedium implements Upgrade {

    @Override
    public void upgrade(TransformTc tc, Map world) {
        AbstractEntityFactory entityFactory = EntityProducer.getFactory(true);     //creates factory for placing units

        //deletes weak unit and places medium unit in it's place
        if (world.removeEntity(tc.pos))
        {
            IEntity medium = entityFactory.getEntity("MediumUnit",tc);
            world.addEntity((Entity)medium);
        }
    }

}