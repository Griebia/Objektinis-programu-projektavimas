package tconq.entity.visitor;

import tconq.entity.IEntity;

public class WarriorTax implements Visitor {
    @Override
    public int visit(IEntity entity) {
        switch (entity.getEntityClass(entity).getSimpleName().toLowerCase()){
            case "weakunit":
                System.out.println("Warrior tax 1");
                return -1;
            case "mediumunit":
                System.out.println("Warrior tax 2");
                return -2;
            case "strongunit":
                System.out.println("Warrior tax 3");
                return -3;
            default:
                return 0;
        }
    }
}
