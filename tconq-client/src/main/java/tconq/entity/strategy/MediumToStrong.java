package tconq.entity.strategy;

import tconq.entity.IEntity;
import tconq.entity.TransformTc;
import tconq.entity.decorator.Attack;
import tconq.entity.decorator.AttackBuilding;
import tconq.entity.decorator.Movement;
import tconq.entity.factory.AbstractEntityFactory;
import tconq.entity.factory.EntityProducer;
import tconq.worldmap.Map;

public class MediumToStrong implements Upgrade {

    @Override
    public void upgrade(TransformTc tc, Map world, Long playerId, Long entityId)  {
        AbstractEntityFactory entityFactory = EntityProducer.getFactory(true);     //creates factory for placing units

        //deletes medium unit and places strong unit in it's place
        if (world.removeEntity(tc.pos))
        {
            IEntity strong = new Attack( new AttackBuilding( new Movement( entityFactory.getEntity("StrongUnit",tc) )));
            strong.setId(entityId);


            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println(strong.getMovement());
            System.out.println(strong.getAttack(strong));
            System.out.println(strong.getDestroyBuilding(strong));
            System.out.println("-------------------------------------------------------------------------------------");


            world.addEntity(strong, playerId);
        }

    }

}