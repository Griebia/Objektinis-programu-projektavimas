package tconq.entity.command;

import org.joml.Vector2f;
import tconq.entity.Entity;
import tconq.entity.TransformTc;

public class Left implements ICommand {

    //Units info
    private final Entity unit;

    //Constructor
    public Left(Entity newUnit)
    {
        unit = newUnit;
    }

    @Override
    public void move() {
        Vector2f movement = new Vector2f(-2,0);
        unit.move(movement);
    }

    @Override
    public void undo() {
        Vector2f movement = new Vector2f(2,0);
        unit.move(movement);
    }
}
