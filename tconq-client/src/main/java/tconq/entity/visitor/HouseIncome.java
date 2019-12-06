package tconq.entity.visitor;

import tconq.entity.IEntity;

public class HouseIncome implements Visitor {

    @Override
    public int visit(IEntity entity) {
        switch (entity.getEntityClass(entity).getSimpleName().toLowerCase()){
            case "house":
                return 1;
            case "tower":
                return 2;
            case "castle":
                return 3;
            default:
                return 0;
        }
    }
}
