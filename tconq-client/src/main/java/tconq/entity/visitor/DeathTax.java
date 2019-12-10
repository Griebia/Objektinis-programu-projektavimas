package tconq.entity.visitor;

import tconq.entity.IEntity;

public class DeathTax implements Visitor {
    @Override
    public int visit(IEntity entity) {
        switch (entity.getEntityClass(entity).getSimpleName().toLowerCase()){
            case "weakunit":
            case "house":
                System.out.println("Tax for losing object 2");
                return -2;
            case "mediumunit":
            case "tower":
                System.out.println("Tax for losing object 3");
                return -3;
            case "strongunit":
            case "castle":
                System.out.println("Tax for losing object 4");
                return -4;
            default:
                return 0;
        }
    }
}
