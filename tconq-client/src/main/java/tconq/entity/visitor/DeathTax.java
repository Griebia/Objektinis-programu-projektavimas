package tconq.entity.visitor;

import tconq.entity.IEntity;

public class DeathTax implements Visitor {
    @Override
    public int visit(IEntity entity) {
        switch (entity.getEntityClass(entity).getSimpleName().toLowerCase()){
            case "weakunit":
            case "house":
                return -2;
            case "mediumunit":
            case "tower":
                return -3;
            case "strongunit":
            case "castle":
                return -4;
            default:
                return 0;
        }
    }
}
