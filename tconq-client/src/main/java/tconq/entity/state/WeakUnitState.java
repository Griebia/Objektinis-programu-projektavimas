package tconq.entity.state;

import tconq.entity.Entity;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.factory.AbstractEntityFactory;
import tconq.entity.factory.EntityProducer;
import tconq.worldmap.Map;

public class WeakUnitState implements State {

    @Override
    public void upgrade(StateContext context,TransformTc tc, Map world, Long playerId, Long entityId) {
        AbstractEntityFactory entityFactory = EntityProducer.getFactory(true);     //creates factory for placing units

        //deletes weak unit and places medium unit in it's place
        if (world.removeEntity(tc.pos))
        {
            IEntity medium = entityFactory.getEntity("MediumUnit",tc);
            medium.setId(entityId);

            world.addEntity(medium, playerId);
            System.out.println("Used state to upgrade the weak unit to medium unit");
            context.setState(new MediumUnitState());
        }

    }
}
