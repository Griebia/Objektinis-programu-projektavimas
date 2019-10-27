package tconq.entity.command;

import tconq.entity.Entity;
import tconq.entity.TransformTc;

public class Down implements ICommand {

    //Units info
    private final Entity unit;

    //Constructor
    public Down(Entity newUnit)
    {
        unit = newUnit;
    }

    @Override
    public void move() {
        TransformTc unitPos = unit.getPos();
        unitPos.pos.y += 2;
        if (unitPos.pos.x < 0 || unitPos.pos.y < 0) {
            throw new IllegalStateException("The unit cannot move the " + this.getClass().getSimpleName());
        }
        unit.move(unitPos.getVector2f());
    }

    @Override
    public void undo() {
        TransformTc unitPos = unit.getPos();
        unitPos.pos.y -= 2;
        if (unitPos.pos.x < 0 || unitPos.pos.y < 0) {
            throw new IllegalStateException("The unit cannot move the " + this.getClass().getSimpleName());
        }
        unit.move(unitPos.getVector2f());
    }
}