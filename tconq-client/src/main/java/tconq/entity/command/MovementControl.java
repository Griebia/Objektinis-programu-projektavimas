package tconq.entity.command;

import tconq.entity.Entity;
import tconq.entity.IEntity;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;

public class MovementControl {
    private final LinkedList<ICommand> commandSequence= new LinkedList<ICommand>(); //The list of made commands
    private final HashMap<String,ICommand> commands= new HashMap<String, ICommand>();//The list of possible commands
    private IEntity unit;
    //Constructor that constructs all of the movement commands and adds them to the list
    public MovementControl(IEntity unit){
        this.unit = unit;
        commands.put("Right",new Right(unit));
        commands.put("Left",new Left(unit));
        commands.put("Up",new Up(unit));
        commands.put("Down",new Down(unit));
    }

    //Moves the entity to one of the directions
    public void move(String direction){
        if(commandSequence.size() < unit.getMovement()) {
            ICommand command = commands.get(direction);
            //If there is no such command throws and exception
            if (command == null) {
                throw new IllegalStateException("No command registered for " + direction);
            }
            commandSequence.add(command);
            command.move();
        }
    };

    //Undoes the last made move
    public void undo(){
        if (commandSequence.size() > 0){
            ICommand command = commandSequence.removeLast();
            command.undo();
        }
    }
}
