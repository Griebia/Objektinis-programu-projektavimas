package tconq.entity.visitor;

import tconq.entity.IEntity;

public class WarriorTax implements Visitor {
    @Override
    public int visit(IEntity entity) {
        switch (entity.getEntityClass(entity).getSimpleName().toLowerCase()){
            case "weakunit":
                return -1;
            case "mediumunit":
                return -2;
            case "strongunit":
                return -3;
            default:
                return 0;
        }
    }
}
