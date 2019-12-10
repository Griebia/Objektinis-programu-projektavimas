package tconq.memento;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {
    private List<Memento> history;
    private int currState = -1;

    public CareTaker(){
        this.history = new ArrayList<>();
    }

    //Adds a memento to the memory
    public void addMemento(Memento m){
        System.out.println("Saving to ..");
        if(currState != (this.history.size() - 1) && currState != 0){
            history = new ArrayList<>(history.subList(0,currState)) ;
        }
        this.history.add(m);
        currState = this.history.size();
    }

    public Memento getMemento(int index){
        return this.history.get(index);
    }

    public Memento undo(){
        System.out.println("Undoing state " + currState + "..");
        if (currState <= 0){
            currState = 0;
            return getMemento(currState);
        }
        currState--;
        return getMemento(currState);
    }

    public Memento redo(){
        System.out.println("Redoing state..");
        if (currState >= history.size()-1){
            currState = history.size() -1;
            return getMemento(currState);
        }
        currState++;
        return getMemento(currState);
    }
}
