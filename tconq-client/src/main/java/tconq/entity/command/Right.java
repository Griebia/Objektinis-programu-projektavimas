package tconq.entity.command;

import org.joml.Vector2f;
import tconq.entity.IEntity;
import tconq.entity.TransformTc;

public class Right implements ICommand {

    //Units info
    private IEntity unit;

    //Constructor
    public Right(IEntity newUnit)
    {
        unit = newUnit;
    }

    @Override
    public void move() {
        TransformTc unitPos = unit.getPos();

    }

    @Override
    public void undo() {

    }
}
