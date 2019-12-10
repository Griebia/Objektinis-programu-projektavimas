package tconq.entity.state;

import tconq.entity.Entity;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.factory.AbstractEntityFactory;
import tconq.entity.factory.EntityProducer;
import tconq.worldmap.Map;

public class MediumUnitState implements State {
    @Override
    public void upgrade(StateContext context, TransformTc tc, Map world, Long playerId, Long entityId) {
        AbstractEntityFactory entityFactory = EntityProducer.getFactory(true);     //creates factory for placing units

        //deletes weak unit and places medium unit in it's place
        if (world.removeEntity(tc.pos))
        {
            IEntity strong = entityFactory.getEntity("StrongUnit",tc);
            strong.setId(entityId);

            world.addEntity(strong, playerId);
            System.out.println("Used state to upgrade the medium to strong unit");
            context.setState(new StrongUnitState());
        }

    }

    @Override
    public void downgrade(StateContext context, TransformTc tc, Map world, Long playerId, Long entityId) {
        AbstractEntityFactory entityFactory = EntityProducer.getFactory(true);     //creates factory for placing units

        //deletes weak unit and places medium unit in it's place
        if (world.removeEntity(tc.pos))
        {
            IEntity strong = entityFactory.getEntity("WeakUnit",tc);
            strong.setId(entityId);

            world.addEntity(strong, playerId);
            System.out.println("Used state to upgrade the medium to strong unit");
            context.setState(new WeakUnitState());
        }
    }
}
