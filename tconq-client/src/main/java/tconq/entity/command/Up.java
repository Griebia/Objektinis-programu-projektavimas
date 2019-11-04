package tconq.entity.command;

import org.joml.Vector2f;
import tconq.entity.Entity;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;

public class Up implements ICommand {

    //Units info
    private final IEntity unit;

    //Constructor
    public Up(IEntity newUnit)
    {
        unit = newUnit;
    }

    @Override
    public void move() {
        Vector2f movement = new Vector2f(0,2);
        unit.move(movement);
    }

    @Override
    public void undo() {
        Vector2f movement = new Vector2f(0,-2);
        unit.move(movement);
    }
}
