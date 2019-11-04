package tconq.entity.command;

import org.joml.Vector2f;
import tconq.entity.Entity;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;

import java.util.Vector;

public class Right implements ICommand {

    //Units info
    private final IEntity unit;

    //Constructor
    public Right(IEntity newUnit)
    {
        unit = newUnit;
    }

    @Override
    public void move() {
//        if (unitPos.pos.x < 0 || unitPos.pos.y > 0) {
//            throw new IllegalStateException("The unit cannot move the " + this.getClass().getSimpleName());
//        }
        Vector2f movement = new Vector2f(2,0);
        unit.move(movement);
    }

    @Override
    public void undo() {
        Vector2f movement = new Vector2f(-2,0);
        unit.move(movement);
    }
}
