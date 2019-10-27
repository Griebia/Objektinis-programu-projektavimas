package tconq.entity.strategy;

import tconq.entity.Entity;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.decorator.Attack;
import tconq.entity.decorator.AttackBuilding;
import tconq.entity.decorator.Movement;
import tconq.entity.factory.AbstractEntityFactory;
import tconq.entity.factory.EntityProducer;
import tconq.worldmap.Map;

public class WeakToMedium implements Upgrade {

    @Override
    public void upgrade(TransformTc tc, Map world, Long playerId) {
        AbstractEntityFactory entityFactory = EntityProducer.getFactory(true);     //creates factory for placing units

        //deletes weak unit and places medium unit in it's place
        if (world.removeEntity(tc.pos))
        {
            IEntity medium = new AttackBuilding( new Attack( new Movement( entityFactory.getEntity("MediumUnit",tc) )));

            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println(medium.getMovement());
            System.out.println(medium.getAttack(medium));
            System.out.println(medium.getDestroyBuilding(medium));
            System.out.println("-------------------------------------------------------------------------------------");

            world.addEntity(medium, playerId);
        }
    }

}