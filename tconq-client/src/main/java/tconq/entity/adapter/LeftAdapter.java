package tconq.entity.adapter;

import org.joml.Vector2f;

import tconq.entity.IEntity;
import tconq.entity.command.ICommand;

public class LeftAdapter implements IMovementAdapter {

    private ICommand command;
    private IEntity entity;
    //Vector2f movement = new Vector2f(0,-2);

    public LeftAdapter(ICommand command,IEntity entity){
        this.command = command;
        this.entity = entity;
    }

    @Override
    public void moveAdapter(){
         move(command.getMovement());
    }

    private void move(Vector2f movement){
        //movement.add(this.movement,movementDest);
        
        entity.move(movement.add(-2,2));
    }
}