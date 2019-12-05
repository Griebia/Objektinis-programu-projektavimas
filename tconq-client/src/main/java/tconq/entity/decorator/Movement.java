package tconq.entity.decorator;

import tconq.entity.IEntity;

public class Movement extends UnitDecorator {
    public Movement(IEntity newUnit) {
        super(newUnit);
    }

    //gets amount of spaces unit can move
    public int getMovement() {

        switch (tempUnit.getClass().getSimpleName().toLowerCase()){  //determines current units type
            case "weakunit":    //if type is WeakUnit
                return 1;
            case "mediumunit":  //if type is mediumUnit
                return 2;
            case "strongunit":  //if type is strongUnit
                return 3;
        }
        return tempUnit.getMovement();
    }


}
