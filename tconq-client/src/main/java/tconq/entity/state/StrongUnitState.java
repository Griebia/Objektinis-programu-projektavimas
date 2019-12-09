package tconq.entity.state;

import tconq.entity.Entity;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.factory.AbstractEntityFactory;
import tconq.entity.factory.EntityProducer;
import tconq.worldmap.Map;

public class StrongUnitState implements State {
    @Override
    public void upgrade(StateContext context, TransformTc tc, Map world, Long playerId, Long entityId) {
        System.out.println("You cannot upgrade more form strong unit state");
    }

    @Override
    public void downgrade(StateContext context, TransformTc tc, Map world, Long playerId, Long entityId) {
        AbstractEntityFactory entityFactory = EntityProducer.getFactory(true);     //creates factory for placing units

        //deletes weak unit and places medium unit in it's place
        if (world.removeEntity(tc.pos))
        {
            IEntity strong = entityFactory.getEntity("MediumUnit",tc);
            strong.setId(entityId);

            world.addEntity(strong, playerId);
            System.out.println("Used state to downgrade the strong to medium unit");
            context.setState(new MediumUnitState());
        }
    }
}
