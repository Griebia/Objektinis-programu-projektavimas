package tconq.entity.visitor;

import tconq.entity.IEntity;

public class HouseIncome implements Visitor {

    @Override
    public int visit(IEntity entity) {
        switch (entity.getEntityClass(entity).getSimpleName().toLowerCase()){
            case "house":
                System.out.println("House income 1");
                return 1;
            case "tower":
                System.out.println("House income 2");
                return 2;
            case "castle":
                System.out.println("House income 3");
                return 3;
            default:
                return 0;
        }
    }
}
