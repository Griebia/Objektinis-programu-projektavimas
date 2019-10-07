package tconq.factories;

import tconq.entity.Building;
import tconq.entity.MediumUnit;
import tconq.entity.StrongUnit;
import tconq.entity.Unit;
import tconq.entity.WeakUnit;

public class UnitFactory implements AbstractFactory<Unit> {

    @Override
    public Unit create(String unitType) {
        if ("WeakUnit".equalsIgnoreCase(unitType))
            return new WeakUnit();
        else if ("MediumUnit".equalsIgnoreCase(unitType))
            return new MediumUnit();
        else if ("StrongUnit".equalsIgnoreCase(unitType))
            return new StrongUnit();
        return null;
    }
}